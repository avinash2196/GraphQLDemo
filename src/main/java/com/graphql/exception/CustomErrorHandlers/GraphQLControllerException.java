package com.graphql.exception.CustomErrorHandlers;

import graphql.GraphQLError;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.execution.DataFetcherExceptionResolver;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class GraphQLControllerException implements DataFetcherExceptionResolver {

    @Override
    public Mono<List<GraphQLError>> resolveException(Throwable exception, DataFetchingEnvironment environment) {


        if (exception instanceof ConstraintViolationException) {
            return Mono.just(generateConstraintViolationMessage((ConstraintViolationException) exception
                    ));
        }


        return Mono.just(List.of());
    }

    private List<GraphQLError> generateConstraintViolationMessage(ConstraintViolationException exception) {
        return exception.getConstraintViolations().stream()
                .map(constraints -> new BadRequestException(constraints.getMessageTemplate()))
                .map(constraintViolationException -> (GraphQLError) constraintViolationException)
                .collect(Collectors.toList());
    }
}