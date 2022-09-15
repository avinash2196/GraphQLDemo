package com.graphql;

import com.azure.spring.data.cosmos.repository.config.EnableCosmosRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableCosmosRepositories
public class GraphQLApplication {
    public static void main(String[] args) {
        SpringApplication.run(GraphQLApplication.class, args);
        System.out.println("GraphQl Basics Demo");
    }
}
