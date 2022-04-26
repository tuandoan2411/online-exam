package com.tuandoan.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "minutes")
    private Integer minutes;

    @Column(name = "type")
    private String type;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "number_of_sentences")
    private Integer numberOfSentences;

    @Column(name = "number_of_exams")
    private Integer numberOfExams = 0;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "exam",
            cascade = CascadeType.ALL)
    private List<Sentence> sentences;

    public Exam(){}

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

    public Integer getMinutes() {
        return minutes;
    }

    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getNumberOfSentences() {
        return numberOfSentences;
    }

    public void setNumberOfSentences(Integer numberOfSentences) {
        this.numberOfSentences = numberOfSentences;
    }

    public Integer getNumberOfExams() {
        return numberOfExams;
    }

    public void setNumberOfExams(Integer numberOfExams) {
        this.numberOfExams = numberOfExams;
    }

    public List<Sentence> getSentences() {
        return sentences;
    }

    public void setSentences(List<Sentence> sentences) {
        this.sentences = sentences;
    }

    public void addSentence(Sentence sentence){
        if(sentences == null) sentences = new ArrayList<>();
        sentences.add(sentence);
        sentence.setExam(this);
    }

    @Override
    public String toString() {
        return "Exam{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", minutes=" + minutes +
                ", type='" + type + '\'' +
                ", date=" + date +
                ", numberOfSentences=" + numberOfSentences +
                ", numberOfExams=" + numberOfExams +
                '}';
    }
}
