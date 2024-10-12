package com.leftware.todomanager.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.leftware.todomanager.models.Project;

@Service
public class ProjectService {

    public final List<Project> projects;

    public ProjectService() {
        List<Project> projectList = new ArrayList<>();
        projectList.add(new Project("123", "home"));
        projectList.add(new Project(UUID.randomUUID().toString(), "work"));

        this.projects = projectList;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public Project getProjectById(String projectId) {
        Project project = projects.stream()
                .filter(p -> (p.getId().equals(projectId)))
                .findFirst()
                .orElse(null);
        return project;
    }

    public void addProject(Project project) {
        projects.add(project);
    }
}
