package com.leftware.todomanager.models;

public class CreateProjectRequest {

    // @JsonProperty("name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
