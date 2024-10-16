package com.leftware.todomanager.controllers.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leftware.todomanager.models.ProjectModel;
import com.leftware.todomanager.services.ProjectService;

@RestController
public class ApiGetProjectListController {

    private final ProjectService projectService;

    public ApiGetProjectListController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/api/projects")
    public ResponseEntity<List<ProjectModel>> execute() {
        List<ProjectModel> projects = projectService.getProjects();
        return ResponseEntity.ok(projects);
    }
}
