package com.easyjob.jobmanager.entity.vacancy;

import com.easyjob.jobmanager.entity.JobCategory;

import java.util.List;

public class VacancySearchCriteria {

    private String search;
    private List<JobCategory> jobCategories;
    private List<EmploymentMode> employmentModes;

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

    public List<EmploymentMode> getEmploymentModes() {
        return employmentModes;
    }

    public void setEmploymentModes(List<EmploymentMode> employmentModes) {
        this.employmentModes = employmentModes;
    }
}
