package com.leftware.todomanager.controllers.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.leftware.todomanager.models.ProjectModel;
import com.leftware.todomanager.models.TaskModel;
import com.leftware.todomanager.services.ProjectService;
import com.leftware.todomanager.services.TaskService;

@RestController
public class ApiGetProjectController {

    private final ProjectService projectService;
    private final TaskService taskService;

    //@Autowired
    public ApiGetProjectController(
            ProjectService projectService,
            TaskService taskService
    ) {
        this.projectService = projectService;
        this.taskService = taskService;
    }

    @GetMapping("/api/projects/{projectId}")
    public ResponseEntity<ProjectModel> execute(
        @PathVariable String projectId
        ) {
        ProjectModel project = projectService.getProjectById(projectId);
        if (project == null) {
            return ResponseEntity.notFound().build();
        }

        List<TaskModel> tasks = taskService.getTasksByProjectId(projectId);
        project.setTasks(tasks);
        
        return ResponseEntity.ok(project);
    }
}
