package com.leftware.todomanager.models;

import java.util.List;

// @JsonInclude(JsonInclude.Include.NON_NULL)
public class ProjectModel {

    private String id;
    private String name;
    private List<TaskModel> tasks;

    public ProjectModel() {
    }

    public ProjectModel(String id, String name) {
        this.id = id;
        this.name = name;
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

    public List<TaskModel> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskModel> tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return "Project{"
                + "id='" + id + '\''
                + ", name='" + name
                + '\''
                + '}';
    }
}
