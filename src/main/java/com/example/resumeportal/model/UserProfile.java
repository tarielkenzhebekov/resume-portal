package com.example.resumeportal.model;

import jakarta.persistence.*;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getTheme() {
        return theme;
    }

    public void setTheme(int theme) {
        this.theme = theme;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
