package com.leftware.todomanager.controllers.api;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.leftware.todomanager.models.CreateProjectRequest;
import com.leftware.todomanager.models.ProjectModel;
import com.leftware.todomanager.services.ProjectService;

@RestController
public class ApiCreateProjectController {

    private final ProjectService projectService;

    //@Autowired
    public ApiCreateProjectController(
            ProjectService projectService
    ) {
        this.projectService = projectService;
    }

    @PostMapping("/api/projects")
    public ResponseEntity<ProjectModel> execute(
            @RequestBody CreateProjectRequest createProjectRequest
    ) {
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString();
        ProjectModel project = new ProjectModel(id, createProjectRequest.getName());
        projectService.addProject(project);

        return ResponseEntity.ok(project);
    }
}
