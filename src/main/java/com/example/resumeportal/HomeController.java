package com.example.resumeportal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Hello World";
    }

    @GetMapping("/edit")
    public String edit() {
        return "Edit Page";
    }

    @GetMapping("/view/{userId}")
    public String view(@PathVariable("userId") String userId, Model model) {
        model.addAttribute("userId", userId);
        return "profile-templates/3/index";
    }
}
