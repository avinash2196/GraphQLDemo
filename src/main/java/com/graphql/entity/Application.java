package com.graphql.entity;

import java.util.Arrays;
import java.util.List;


public class Application {

    private static List<Application> applications = Arrays.asList(
            new Application("app-1", "app1", "owner1", "app-1"),
            new Application("app-2", "app2", "owner2", "app-2"),
            new Application("app-3", "app3", "owner3", "app-3")
    );
    private String id;
    private String name;
    private String description;
    private String owner;

    public Application(String id, String name, String owner, String description) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.description = description;
    }

    public static Application getById(String id) {
        return applications.stream().filter(application -> application.getId().equals(id)).findFirst().orElse(applications.get(0));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
