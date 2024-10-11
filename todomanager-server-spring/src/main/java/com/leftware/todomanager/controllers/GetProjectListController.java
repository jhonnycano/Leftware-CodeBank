package com.leftware.todomanager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.leftware.todomanager.services.ProjectService;

@Controller
public class GetProjectListController {

    private final ProjectService projectService;

    //@Autowired
    public GetProjectListController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/web/projects")
    public String execute(Model model) {
        // List<Project> projects = new ArrayList<>();
        // projects.add(new Project("asd1", "home"));
        // projects.add(new Project("asd2", "work"));
        var projects = projectService.getProjects();

        model.addAttribute("projects", projects);
        model.addAttribute("title", "Projects");
        model.addAttribute("content", "projects");
        return "layout";
    }
}
