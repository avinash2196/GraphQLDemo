package com.graphql.entity;

import java.util.Arrays;
import java.util.List;


public class Application {

    private String id;
    private String name;
    private String description;
    private String owner;

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

    public Application(String id, String name, String owner, String description) {
        this.id=id;
        this.name = name;
        this.owner = owner;
        this.description = description;
    }
    private static List<Application> applications = Arrays.asList(
            new Application("book-1", "Harry Potter and the Philosopher's Stone", "223", "author-1"),
            new Application("book-2", "Moby Dick", "635", "author-2"),
            new Application("book-3", "Interview with the vampire", "371", "author-3")
    );

    public static Application getById(String id) {
        return applications.stream().filter(application -> application.getId().equals(id)).findFirst().orElse(applications.get(0));
    }
}
