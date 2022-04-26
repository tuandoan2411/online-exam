package com.tuandoan.dto;

import java.time.LocalDate;

public class ExamDTO {
    private Integer id;
    private String title;
    private String description;
    private LocalDate date;
    private int numberOfExams;
    private String typeInformation;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getNumberOfExams() {
        return numberOfExams;
    }

    public void setNumberOfExams(int numberOfExams) {
        this.numberOfExams = numberOfExams;
    }

    public String getTypeInformation() {
        return typeInformation;
    }

    public void setTypeInformation(String typeInformation) {
        this.typeInformation = typeInformation;
    }
}
