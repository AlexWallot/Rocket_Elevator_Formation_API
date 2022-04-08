package com.example.rocket_elevator_formation_api.model;

public class Employee {
    private int ID;
    private String firstname;
    private String lastname;
    private int userID;

    public Employee(int ID, String firstname, String lastname, int userID) {
        this.ID = ID;
        this.firstname = firstname;
        this.lastname = lastname;
        this.userID = userID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
