package com.example.xclientsSpringTests.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Timestamp;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Company {
    private int id;
    private boolean isActive;
    private String name;
    private String description;
    private Timestamp deletedAt;

    public int getId() {
        return id;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Timestamp getDeletedAt() {
        return deletedAt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDeletedAt(Timestamp deletedAt) {
        this.deletedAt = deletedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Company company)) return false;
        return getId() == company.getId() && isActive == company.isActive && Objects.equals(getName(), company.getName()) && Objects.equals(getDescription(), company.getDescription()) && Objects.equals(getDeletedAt(), company.getDeletedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), isActive, getName(), getDescription(), getDeletedAt());
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", isActive=" + isActive +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", deletedAt=" + deletedAt +
                '}';
    }
}