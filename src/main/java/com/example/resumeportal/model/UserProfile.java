package com.example.resumeportal.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "USER_PROFILE")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "THEME")
    private int theme;

    @Column(name = "SUMMARY")
    private String summary;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "DESIGNATION")
    private String designation;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "USER_PROFILE_ID")
    List<Job> jobs = new ArrayList<>();

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "USER_PROFILE_ID")
    List<Education> educations = new ArrayList<>();

    @Column(name = "SKILLS")
    @ElementCollection(targetClass = String.class)
    List<String> skills = new ArrayList<>();

}
