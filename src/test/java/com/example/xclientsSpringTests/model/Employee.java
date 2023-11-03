package com.example.xclientsSpringTests.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private int companyId;
    private String phone;
    private boolean isActive;

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getCompanyId() {
        return companyId;
    }

    public String getPhone() {
        return phone;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
}