package com.example.resumeportal.controller;

import com.example.resumeportal.model.Education;
import com.example.resumeportal.model.Job;
import com.example.resumeportal.model.UserProfile;
import com.example.resumeportal.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @GetMapping("/")
    public String home() {
        Optional<UserProfile> profileOptional = userProfileRepository.findByUserName("tariel");
        profileOptional.orElseThrow(() -> new RuntimeException("Not found"));

        UserProfile profile = profileOptional.get();

        Job job1 = new Job();
        job1.setId(1);
        job1.setCompany("Google");
        job1.setDesignation("Software Engineer");
        job1.setStartDate(LocalDate.of(2025, 1, 1));
        job1.setEndDate(LocalDate.of(2025, 7, 28));
        job1.getResponsibilities().add("Improve Google search results");
        job1.getResponsibilities().add("Enhance YouTube's recommendation system");
        job1.getResponsibilities().add("Better anti-spam methods of Gmail");
        job1.setCurrentJob(true);
        Job job2 = new Job();
        job2.setId(2);
        job2.setCompany("Jetbrains");
        job2.setDesignation("Software Developer");
        job2.setStartDate(LocalDate.of(2023, 10, 10));
        job2.setEndDate(LocalDate.of(2024, 11, 20));
        job2.getResponsibilities().add("Create a new way of deploying projects");
        job2.getResponsibilities().add("Implement an AI powered auto-completion technique");

        Education education1 = new Education();
        education1.setId(1);
        education1.setCollege("Kyrgyz-Turkish Manas University");
        education1.setQualification("Bachelor's Degree");
        education1.setStartDate(LocalDate.of(2019, 9, 9));
        education1.setEndDate(LocalDate.of(2023, 6, 26));
        education1.setMajor("Computer Engineering");
        Education education2 = new Education();
        education2.setId(2);
        education2.setCollege("High School of Foreign Languages");
        education2.setQualification("Useless Degree");
        education2.setStartDate(LocalDate.of(2018, 9, 10));
        education2.setEndDate(LocalDate.of(2019, 6, 11));
        education2.setMajor("Turkish Language");


        profile.getJobs().clear();
        profile.getJobs().add(job1);
        profile.getJobs().add(job2);

        profile.getEducations().clear();
        profile.getEducations().add(education1);
        profile.getEducations().add(education2);

        profile.getSkills().clear();
        profile.getSkills().add("Java");
        profile.getSkills().add("Spring Boot");
        profile.getSkills().add("C++");
        profile.getSkills().add("Python");
        profile.getSkills().add("PHP");
        profile.getSkills().add("C#");
        profile.getSkills().add("JavaScript");

        userProfileRepository.save(profile);
        return "profile";
    }

    @GetMapping("/edit")
    public String edit(Principal principal, Model model) {
        String userName = principal.getName();

        Optional<UserProfile> userProfileOptional = userProfileRepository.findByUserName(userName);
        userProfileOptional.orElseThrow(() -> new RuntimeException("Not found: " + userName));

        UserProfile userProfile = userProfileOptional.get();
        model.addAttribute("userProfile", userProfile);
        return "profile-edit";
    }

    @PostMapping("/edit")
    public String postEdit(Principal principal, Model model) {
        String userName = principal.getName();
        // Save the updated values in the form
        return "redirect:/view/" + userName;
    }

    @GetMapping("/view/{userName}")
    public String view(@PathVariable("userName") String userName, Model model) {
        Optional<UserProfile> userProfileOptional = userProfileRepository.findByUserName(userName);
        userProfileOptional.orElseThrow(() -> new RuntimeException("Not found: " + userName));

        UserProfile userProfile = userProfileOptional.get();
        model.addAttribute("userProfile", userProfile);

        System.out.println(userProfile.getJobs());
        return "profile-templates/" + userProfile.getTheme() + "/index";
    }
}
