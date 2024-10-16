package com.leftware.todomanager.controllers.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.leftware.todomanager.common.Constants;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "redirect:/web";
    }

    @GetMapping("/web")
    public String web(Model model) {
        model.addAttribute("title", "Home");
        model.addAttribute("content", Constants.VIEW_HOME);
        return "layout";
    }
}
