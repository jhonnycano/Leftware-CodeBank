package com.leftware.todomanager.controllers;

import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.leftware.todomanager.common.Constants;
import com.leftware.todomanager.models.CreateTaskRequest;
import com.leftware.todomanager.models.Task;
import com.leftware.todomanager.services.ProjectService;
import com.leftware.todomanager.services.TaskService;

@Controller
public class CreateTaskController {

    private final TaskService taskService;
    private final ProjectService projectService;

    //@Autowired
    public CreateTaskController(
            ProjectService projectService,
            TaskService taskService
    ) {
        this.projectService = projectService;
        this.taskService = taskService;
    }

    @GetMapping("/web/projects/{projectId}/tasks/new")
    public String execute(
            @PathVariable String projectId,
            Model model
    ) {
        var project = projectService.getProjectById(projectId);
        if (project == null) {
            model.addAttribute("content", Constants.VIEW_HOME);
            model.addAttribute("message", "Project not found");
            return "layout";
        }

        model.addAttribute("title", "Create task");
        model.addAttribute("projectId", projectId);
        model.addAttribute("content", "task_create");
        return "layout";
    }

    @PostMapping("/web/projects/{projectId}/tasks")
    public String execute(
            @PathVariable String projectId,
            @ModelAttribute CreateTaskRequest createTaskRequest,
            Model model
    ) {
        var project = projectService.getProjectById(projectId);
        if (project == null) {
            model.addAttribute("content", Constants.VIEW_HOME);
            model.addAttribute("message", "Project not found");
            return "layout";
        }

        UUID uuid = UUID.randomUUID();
        String id = uuid.toString();
        Task task = new Task(id, projectId, createTaskRequest.getText(), "Pending", null);
        taskService.addTask(task);

        return String.format("redirect:/web/projects/%s", projectId);
    }
}
