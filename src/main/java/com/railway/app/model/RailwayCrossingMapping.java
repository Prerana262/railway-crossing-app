package com.railway.app.model;

public class RailwayCrossingMapping {
    private int id;
    private String email;
    private int railwayCrossingId;

    public RailwayCrossingMapping() {
        // Default constructor
    }

    public RailwayCrossingMapping(String email, int railwayCrossingId) {
        this.email = email;
        this.railwayCrossingId = railwayCrossingId;
    }

    // Getters and setters for the fields

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRailwayCrossingId() {
        return railwayCrossingId;
    }

    public void setRailwayCrossingId(int railwayCrossingId) {
        this.railwayCrossingId = railwayCrossingId;
    }
}
