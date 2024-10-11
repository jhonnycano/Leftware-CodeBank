package com.leftware.todomanager.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.leftware.todomanager.models.Project;

@Service
public class ProjectService {

    public final List<Project> projects;

    public ProjectService() {
        List<Project> projectList = new ArrayList<>();
        projectList.add(new Project("asd1", "home"));
        projectList.add(new Project("asd2", "work"));

        this.projects = projectList;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public Project getProjectById(String projectId) {
        Project project = new Project("asd1", "home");
        return project;
    }

}
