package com.graphql.controller;

import com.graphql.entity.Application;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import javax.validation.constraints.NotNull;

@Controller
@Slf4j
@RequiredArgsConstructor
public class GraphqlController {

    @QueryMapping
    public Application findById(@Argument @NotNull String id) {
        System.out.println(id);
        return Application.getById(id);
    }

}
