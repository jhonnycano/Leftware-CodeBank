package com.leftware.todomanager.controllers.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leftware.todomanager.models.TaskModel;
import com.leftware.todomanager.services.ProjectService;
import com.leftware.todomanager.services.TaskService;

@RestController
public class ApiChangeTaskStatusController {

    private final TaskService taskService;
    private final ProjectService projectService;

    //@Autowired
    public ApiChangeTaskStatusController(
            ProjectService projectService,
            TaskService taskService
    ) {
        this.projectService = projectService;
        this.taskService = taskService;
    }

    @PutMapping("/api/projects/{projectId}/tasks/{taskId}/status")
    public ResponseEntity<String> execute(
            @PathVariable String projectId,
            @PathVariable String taskId,
            @RequestParam(defaultValue = "") String status
    ) {
        var project = projectService.getProjectById(projectId);
        if (project == null) {
            return ResponseEntity.status(404).body("Project not found");
        }
        TaskModel task = taskService.getTaskById(projectId, taskId);
        if (task == null) {
            return ResponseEntity.status(404).body("Task not found");
        }

        String changeStatusResult = taskService.changeStatus(projectId, taskId, status);
        if (changeStatusResult != null) {
            return ResponseEntity.status(500).body(changeStatusResult);
        }

        return ResponseEntity.ok("OK");
    }
}
