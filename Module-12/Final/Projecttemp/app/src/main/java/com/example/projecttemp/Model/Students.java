package com.example.projecttemp.Model;

public class Students {

    private String name, RollNumber, Password;

    public Students() {

    }

    public Students(String name, String rollNumber, String password) {
        this.name = name;
        RollNumber = rollNumber;
        Password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRollNumber() {
        return RollNumber;
    }

    public void setRollNumber(String rollNumber) {
        RollNumber = rollNumber;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
