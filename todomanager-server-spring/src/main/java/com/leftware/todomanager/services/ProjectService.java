package com.leftware.todomanager.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.leftware.todomanager.models.Project;

@Service
public class ProjectService {

    public List<Project> getProjects() {
        List<Project> projects = new ArrayList<>();
        projects.add(new Project("asd1", "home"));
        projects.add(new Project("asd2", "work"));
        return projects;
    }
}
