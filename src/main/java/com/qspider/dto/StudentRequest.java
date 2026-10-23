package com.qspider.dto;

public class StudentRequest {

    private String name;
    private Long departmentId;
    private String profileBio;

    public StudentRequest() {
    }

    public String getName() {
        return name;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public String getProfileBio() {
        return profileBio;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public void setProfileBio(String profileBio) {
        this.profileBio = profileBio;
    }
}

