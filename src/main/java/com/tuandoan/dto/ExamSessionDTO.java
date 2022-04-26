package com.tuandoan.dto;

import com.tuandoan.entity.Level5;

import java.time.LocalDate;
import java.util.List;

public class ExamSessionDTO {
    private String title;
    private boolean hasTitleDefault;
    private String description;
    private boolean hasDescriptionDefault;
    private Integer minutes;
    private boolean hasMinutesDefault;
    private String tempType;
    private String typeInformation;
    private LocalDate date;
    private int numberOfSentences;
    private List<Level5> level5s;

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

    public Integer getMinutes() {
        return minutes;
    }

    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }

    public String getTempType() {
        return tempType;
    }

    public void setTempType(String tempType) {
        this.tempType = tempType;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getNumberOfSentences() {
        return numberOfSentences;
    }

    public void setNumberOfSentences(int numberOfSentences) {
        this.numberOfSentences = numberOfSentences;
    }

    public String getTypeInformation() {
        return typeInformation;
    }

    public void setTypeInformation(String typeInformation) {
        this.typeInformation = typeInformation;
    }

    public List<Level5> getLevel5s() {
        return level5s;
    }

    public void setLevel5s(List<Level5> level5s) {
        this.level5s = level5s;
    }

    public boolean hasTitleDefault() {
        return hasTitleDefault;
    }

    public void setHasTitleDefault(boolean hasTitleDefault) {
        this.hasTitleDefault = hasTitleDefault;
    }

    public boolean hasDescriptionDefault() {
        return hasDescriptionDefault;
    }

    public void setHasDescriptionDefault(boolean hasDescriptionDefault) {
        this.hasDescriptionDefault = hasDescriptionDefault;
    }

    public boolean hasMinutesDefault() {
        return hasMinutesDefault;
    }

    public void setHasMinutesDefault(boolean hasMinutesDefault) {
        this.hasMinutesDefault = hasMinutesDefault;
    }

    public void clean() {
        this.title = null;
        this.description = null;
        this.minutes = null;
        tempType = null;
        date = null;
        numberOfSentences = 0;
        typeInformation = null;
        level5s = null;
        hasTitleDefault = false;
        hasDescriptionDefault = false;
        hasMinutesDefault = false;
    }
}
