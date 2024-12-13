package com.embarkx.jobms.job.dto;

import com.embarkx.jobms.job.external.Company;
import com.embarkx.jobms.job.external.Review;

import java.util.List;

public class JobDTO {

    // 1 job is associated with 1 company and then 1 company can have a List of Reviews
    private  Long id;
    private String title;
    private String description;
    private String minSalary;
    private String maxSalary;
    private String location;
    private Company company; //creating object of Company class since these two objects will only be shown to the user
    private List<Review> review;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(String minSalary) {
        this.minSalary = minSalary;
    }

    public String getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(String maxSalary) {
        this.maxSalary = maxSalary;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Review> getReview() {
        return review;
    }

    public void setReviews(List<Review> review) {
        this.review = review;
    }
}