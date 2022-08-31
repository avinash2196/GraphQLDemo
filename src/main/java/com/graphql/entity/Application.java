package com.graphql.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import javax.persistence.*;

@Entity
@Data
@RequiredArgsConstructor
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "application_id")
    private Long id;
    @Column(name = "app_name", nullable = false)
    private String name;
    @Column(length = 2000)
    private String description;
    private String owner;

    public Application(String name, String owner, String description) {
        this.name = name;
        this.owner = owner;
        this.description = description;
    }
}
