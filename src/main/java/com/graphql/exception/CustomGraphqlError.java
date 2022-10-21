package com.graphql.exception;

import com.graphql.exception.CustomErrorHandlers.BadRequestException;
import graphql.ErrorClassification;
import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.validation.ValidationErrorType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.graphql.ResponseError;
import org.springframework.graphql.server.WebGraphQlInterceptor;
import org.springframework.graphql.server.WebGraphQlRequest;
import org.springframework.graphql.server.WebGraphQlResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class CustomGraphqlError implements WebGraphQlInterceptor {

    @Override
    public Mono<WebGraphQlResponse> intercept(WebGraphQlRequest webGraphQlRequest, Chain chain) {
        return chain.next(webGraphQlRequest)
                .map(graphQlResponse -> {
                    List<GraphQLError> graphQLError = graphQlResponse.getErrors().stream()
                            .filter(error -> ErrorType.InvalidSyntax.equals(error.getErrorType())
                                    || ErrorType.ValidationError.equals(error.getErrorType()))
                            .map(this::findErrorMessage)
                            .collect(Collectors.toList());

                    if (!graphQLError.isEmpty()) {
                        log.info("Found Invalid graphql error");
                        return graphQlResponse.transform(builder -> builder.errors(graphQLError));
                    }

                    return graphQlResponse;
                });
    }

    private GraphQLError findErrorMessage(ResponseError error) {

        ErrorClassification errorType = error.getErrorType();

        if (ErrorType.InvalidSyntax.equals(error.getErrorType())) {
            return new BadRequestException("Invalid Syntax error");
        }

        if (ErrorType.ValidationError.equals(errorType)) {

            if (ValidationErrorType.WrongType.equals(
                    extractValidationMessageType(error.getMessage()))) {
                return new BadRequestException("Invalid Field Error");
            }

            if (ValidationErrorType.UnknownArgument.equals(
                    extractValidationMessageType(error.getMessage()))) {
                return new BadRequestException("Unknown Argument");
            }
        }

        return new BadRequestException("Unknown query validation error");
    }

    private ValidationErrorType extractValidationMessageType(String messageString) {
        return ValidationErrorType.valueOf(StringUtils.substringBetween(messageString, "type ", ":"));
    }

}
