package com.tuandoan.dto;

import com.tuandoan.entity.Sentence;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExamDTO {
    private int id;
    private String title;
    private int minutes;
    private String description;
    private LocalDate date;
    private String type;
    private int numberOfSentences;
    private int numberOfExams;
    private String typeInformation;

    private List<SentenceDTO> sentenceDTOs;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNumberOfSentences() {
        return numberOfSentences;
    }

    public void setNumberOfSentences(int numberOfSentences) {
        this.numberOfSentences = numberOfSentences;
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

    public List<SentenceDTO> getSentenceDTOs() {
        return sentenceDTOs;
    }

    public void setSentenceDTOs(List<SentenceDTO> sentenceDTOs) {
        this.sentenceDTOs = sentenceDTOs;
    }

    public void addSentenceDTO(SentenceDTO sentenceDTO){
        if(sentenceDTOs == null) sentenceDTOs = new ArrayList<>();
        sentenceDTOs.add(sentenceDTO);
    }

    public void clean(){
        title = null;
        description = null;
        minutes = 1;
        type = null;
        date = null;
        numberOfSentences = 0;
        numberOfExams = 0;
        typeInformation = null;
    }

    @Override
    public String toString() {
        for(SentenceDTO sentenceDTO : sentenceDTOs){
            System.out.println(sentenceDTO);
        }
        return "ExamDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", minutes=" + minutes +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", type='" + type + '\'' +
                ", numberOfSentences=" + numberOfSentences +
                ", numberOfExams=" + numberOfExams +
                ", typeInformation='" + typeInformation + '\'' +
                '}';
    }
}
