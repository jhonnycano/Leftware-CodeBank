package com.leftware.todomanager.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.leftware.todomanager.entities.Project;
import com.leftware.todomanager.models.ProjectModel;
import com.leftware.todomanager.repositories.ProjectRepository;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<ProjectModel> getProjects() {
        return projectRepository
                .findAll()
                .stream()
                .map(this::convertToProjectModel)
                .collect(Collectors.toList());
    }

    public ProjectModel getProjectById(String projectId) {
        ProjectModel project = convertToProjectModel(projectRepository.findById(projectId).orElse(null));
        return project;
    }

    public void addProject(ProjectModel projectModel) {
        Project project = convertToProject(projectModel);
        projectRepository.save(project);
    }

    private Project convertToProject(ProjectModel projectModel) {
        if (projectModel == null) {
            return null;
        }
        Project project = new Project();
        project.setId(projectModel.getId());
        project.setName(projectModel.getName());

        return project;
    }

    private ProjectModel convertToProjectModel(Project project) {
        if (project == null) {
            return null;
        }
        ProjectModel projectModel = new ProjectModel();
        projectModel.setId(project.getId());
        projectModel.setName(project.getName());

        return projectModel;
    }
}
