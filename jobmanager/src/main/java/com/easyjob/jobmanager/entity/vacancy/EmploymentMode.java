package com.easyjob.jobmanager.entity.vacancy;

public enum EmploymentMode {
    FULL_TIME("Полная занятость, полный день"),
    PART_TIME("Неполный рабочий день"),
    INTERSHIP("Неполный рабочий день, стажировка");

    private final String description;

    EmploymentMode(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
