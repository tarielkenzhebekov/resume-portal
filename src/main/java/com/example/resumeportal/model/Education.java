package com.example.resumeportal.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "EDUCATION")
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name = "COLLEGE")
    private String college;

    @Column(name = "QUALIFICATION")
    private String qualification;

    @Column(name = "MAJOR")
    private String major;

}