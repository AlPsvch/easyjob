package com.easyjob.jobmanager.entity.resume;

import com.easyjob.jobmanager.entity.JobCategory;

import java.util.List;

public class ResumeSearchCriteria {

    private String search;
    private List<JobCategory> jobCategories;
    private Gender gender;

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public List<JobCategory> getJobCategories() {
        return jobCategories;
    }

    public void setJobCategories(List<JobCategory> jobCategories) {
        this.jobCategories = jobCategories;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
