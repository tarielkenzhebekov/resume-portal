package com.example.resumeportal.controller;

import com.example.resumeportal.model.Job;
import com.example.resumeportal.model.UserProfile;
import com.example.resumeportal.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
        job1.setCompany("Company 1");
        job1.setDesignation("Flutter Developer");
        job1.setStartDate(LocalDate.of(2018, 1, 1));
        job1.setEndDate(LocalDate.of(2019, 2, 28));
        Job job2 = new Job();
        job2.setId(2);
        job2.setCompany("Company 2");
        job2.setDesignation("iOS Developer");
        job2.setStartDate(LocalDate.of(2020, 10, 10));
        job2.setEndDate(LocalDate.of(2020, 11, 20));
        job2.setCurrentJob(true);

        profile.getJobs().clear();
        profile.getJobs().add(job1);
        profile.getJobs().add(job2);

        userProfileRepository.save(profile);
        return "profile";
    }

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

        System.out.println(userProfile.getJobs());
        return "profile-templates/" + userProfile.getTheme() + "/index";
    }
}
