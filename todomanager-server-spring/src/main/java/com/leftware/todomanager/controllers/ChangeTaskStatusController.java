package com.leftware.todomanager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.leftware.todomanager.common.Constants;
import com.leftware.todomanager.models.TaskModel;
import com.leftware.todomanager.services.ProjectService;
import com.leftware.todomanager.services.TaskService;

@Controller
public class ChangeTaskStatusController {

    private final TaskService taskService;
    private final ProjectService projectService;

    //@Autowired
    public ChangeTaskStatusController(
            ProjectService projectService,
            TaskService taskService
    ) {
        this.projectService = projectService;
        this.taskService = taskService;
    }

    @GetMapping("/web/projects/{projectId}/tasks/{taskId}/changestatus")
    public String execute(
            @PathVariable String projectId,
            @PathVariable String taskId,
            @RequestParam(defaultValue="") String status,
            Model model
    ) {
        var project = projectService.getProjectById(projectId);
        if (project == null) {
            model.addAttribute("content", Constants.VIEW_HOME);
            model.addAttribute("message", "Project not found");
            return "layout";
        }
        TaskModel task = taskService.getTaskById(projectId, taskId);
        if (task == null) {
            model.addAttribute("content", Constants.VIEW_HOME);
            model.addAttribute("message", "Task not found");
            return "layout";
        }

        String changeStatusResult = taskService.changeStatus(projectId, taskId, status);
        if (changeStatusResult != null) {
            model.addAttribute("content", Constants.VIEW_HOME);
            model.addAttribute("message", changeStatusResult);
            return "layout";
        }

        return String.format("redirect:/web/projects/%s", projectId);
    }
}
