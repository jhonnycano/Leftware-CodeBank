package com.leftware.todomanager.controllers.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.leftware.todomanager.common.Constants;
import com.leftware.todomanager.services.ProjectService;

@Controller
public class WebGetProjectListController {

    private final ProjectService projectService;

    //@Autowired
    public WebGetProjectListController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/web/projects")
    public String execute(Model model) {
        var projects = projectService.getProjects();

        model.addAttribute("projects", projects);
        model.addAttribute("title", "Projects");
        model.addAttribute("content", Constants.VIEW_PROJECT_LIST);
        return "layout";
    }
}
