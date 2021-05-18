package com.easyjob.jobmanager.entity.resume;

import org.springframework.data.domain.Sort;

import static com.easyjob.jobmanager.util.JobManagerConstants.*;

public class ResumePage {

    private int pageNumber = DEFAULT_PAGE_NUMBER;
    private int pageSize = DEFAULT_PAGE_SIZE;
    private Sort.Direction sortDirection = DEFAULT_SORT_DIRECTION;
    private String sortByParameter = "publishDate";

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Sort.Direction getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(Sort.Direction sortDirection) {
        this.sortDirection = sortDirection;
    }

    public String getSortByParameter() {
        return sortByParameter;
    }

    public void setSortByParameter(String sortByParameter) {
        this.sortByParameter = sortByParameter;
    }
}
