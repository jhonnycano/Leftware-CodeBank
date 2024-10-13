package com.leftware.todomanager.controllers;

import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.leftware.todomanager.common.Constants;
import com.leftware.todomanager.models.CreateProjectRequest;
import com.leftware.todomanager.models.ProjectModel;
import com.leftware.todomanager.services.ProjectService;

@Controller
public class CreateProjectController {

    private final ProjectService projectService;

    //@Autowired
    public CreateProjectController(
            ProjectService projectService
    ) {
        this.projectService = projectService;
    }

    @GetMapping("/web/projects/new")
    public String execute(
            Model model
    ) {
        model.addAttribute("title", "Create project");
        model.addAttribute("content", "project_create");
        return "layout";
    }

    @PostMapping("/web/projects")
    public String execute(
            @ModelAttribute CreateProjectRequest createProjectRequest,
            Model model
    ) {
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString();
        ProjectModel project = new ProjectModel(id, createProjectRequest.getName());
        projectService.addProject(project);

        String projectTitle = String.format("Project %s: %s", id, project.getName());

        model.addAttribute("project", project);
        model.addAttribute("title", projectTitle);
        model.addAttribute("content", Constants.VIEW_PROJECT_VIEW);
        return "layout";
    }
}
