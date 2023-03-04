package com.example.resumeportal.controller;

import com.example.resumeportal.model.UserProfile;
import com.example.resumeportal.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    private UserProfileRepository userProfileRepository;

//    @GetMapping("/")
//    public String home() {
//        return "Hello World";
//    }
//
//    @GetMapping("/edit")
//    public String edit() {
//        return "Edit Page";
//    }

    @GetMapping("/view/{userName}")
    public String view(@PathVariable("userName") String userName, Model model) {
        Optional<UserProfile> userProfileOptional = userProfileRepository.findByUserName(userName);
        userProfileOptional.orElseThrow(() -> new RuntimeException("Not found: " + userName));

        UserProfile userProfile = userProfileOptional.get();
        model.addAttribute("userProfile", userProfile);
        return "profile-templates/" + userProfile.getTheme() + "/index";
    }
}
