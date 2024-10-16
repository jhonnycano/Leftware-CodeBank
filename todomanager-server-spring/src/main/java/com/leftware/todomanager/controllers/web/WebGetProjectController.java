package com.leftware.todomanager.controllers.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.leftware.todomanager.common.Constants;
import com.leftware.todomanager.models.ProjectModel;
import com.leftware.todomanager.models.TaskModel;
import com.leftware.todomanager.services.ProjectService;
import com.leftware.todomanager.services.TaskService;

@Controller
public class WebGetProjectController {

    private final ProjectService projectService;
    private final TaskService taskService;

    //@Autowired
    public WebGetProjectController(
            ProjectService projectService,
            TaskService taskService
    ) {
        this.projectService = projectService;
        this.taskService = taskService;
    }

    @GetMapping("/web/projects/{projectId}")
    public String execute(
            @PathVariable String projectId,
            @RequestParam(name = "mode", defaultValue = "board")  String mode,
            Model model
    ) {
        ProjectModel project = projectService.getProjectById(projectId);
        if (project == null) {
            model.addAttribute("content", Constants.VIEW_HOME);
            model.addAttribute("message", "Project not found");
            return "layout";
        }

        List<TaskModel> tasks = taskService.getTasksByProjectId(projectId);
        String projectTitle = String.format("Project %s: %s", projectId, project.getName());

        model.addAttribute("project", project);
        model.addAttribute("tasks", tasks);
        model.addAttribute("title", projectTitle);
        model.addAttribute("mode", mode);
        model.addAttribute("content", Constants.VIEW_PROJECT_VIEW);
        return "layout";
    }
}
