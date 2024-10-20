package com.leftware.todomanager.controllers.api;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leftware.todomanager.models.CreateTaskRequest;
import com.leftware.todomanager.models.TaskModel;
import com.leftware.todomanager.services.ProjectService;
import com.leftware.todomanager.services.TaskService;

@RestController
public class ApiCreateTaskController {

    private final TaskService taskService;
    private final ProjectService projectService;

    //@Autowired
    public ApiCreateTaskController(
            ProjectService projectService,
            TaskService taskService
    ) {
        this.projectService = projectService;
        this.taskService = taskService;
    }

    @PostMapping("/api/projects/{projectId}/tasks")
    public ResponseEntity<TaskModel> execute(
            @PathVariable String projectId,
            @ModelAttribute CreateTaskRequest createTaskRequest
    ) {
        var project = projectService.getProjectById(projectId);
        if (project == null) {
            return ResponseEntity.notFound().build();
        }

        UUID uuid = UUID.randomUUID();
        String id = uuid.toString();
        TaskModel task = new TaskModel(id, projectId, createTaskRequest.getText(), "Pending", null);
        taskService.addTask(task);

        return ResponseEntity.ok(task);
    }
}
