package com.springusertrack.model;

import java.util.List;

public class User {
    private Integer id;
    private String name;
    private String email;
    private List<Track> tracks;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public List<Track> getTracks() {
        return tracks;
    }
}