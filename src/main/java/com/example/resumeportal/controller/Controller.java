package com.example.resumeportal.controller;

import com.example.resumeportal.model.Education;
import com.example.resumeportal.model.Job;
import com.example.resumeportal.model.UserProfile;
import com.example.resumeportal.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/edit")
    public String edit(
            Principal principal,
            Model model,
            @RequestParam(required = false) String add) {
        String userName = principal.getName();
        UserProfile userProfile = getUserProfile(userName);

        if (add != null) {
            switch (add) {
                case "job" -> userProfile.getJobs().add(new Job());
                case "education" -> userProfile.getEducations().add(new Education());
                case "skill" -> userProfile.getSkills().add("");
            }
        }

        model.addAttribute("userProfile", userProfile);
        return "profile-edit";
    }

    @PostMapping("/edit")
    public String postEdit(Principal principal, @ModelAttribute UserProfile userProfile) {
        String userName = principal.getName();
        UserProfile savedUserProfile = getUserProfile(userName);

        userProfile.setId(savedUserProfile.getId());
        userProfile.setUserName(userName);
        userProfileRepository.save(userProfile);
        return "redirect:/edit";
    }

    @GetMapping("/delete")
    public String delete(
            Principal principal,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) int index) {
        String userName = principal.getName();
        UserProfile userProfile = getUserProfile(userName);

        switch (type) {
            case "job" -> userProfile.getJobs().remove(index);
            case "education" -> userProfile.getEducations().remove(index);
            case "skill" -> userProfile.getSkills().remove(index);
        }

        userProfileRepository.save(userProfile);
        return "redirect:/edit";
    }

    @GetMapping("/view/{userName}")
    public String view(
            @PathVariable("userName") String userName,
            Model model,
            Principal principal) {
        if (principal != null && !"".equals(principal.getName())) {
            boolean currentUsersProfile = principal.getName().equals(userName);
            model.addAttribute("currentUsersProfile", currentUsersProfile);
        }
        UserProfile userProfile = getUserProfile(userName);
        model.addAttribute("userProfile", userProfile);
        return "profile-templates/" + userProfile.getTheme() + "/index";
    }

    private UserProfile getUserProfile(String userName) {
        Optional<UserProfile> userProfileOptional = userProfileRepository.findByUserName(userName);
        userProfileOptional.orElseThrow(() -> new RuntimeException("Not found: " + userName));
        return userProfileOptional.get();
    }
}
