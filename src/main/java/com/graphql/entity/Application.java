package com.graphql.entity;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.GeneratedValue;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

@Container(containerName = "GrpahQL")
@Data
@RequiredArgsConstructor
public class Application {
    @Id
    @GeneratedValue
    private String id;
    private String name;
    private String description;
    private String owner;

    public Application(String name, String owner, String description) {
        this.name = name;
        this.owner = owner;
        this.description = description;
    }
}
