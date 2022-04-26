package com.tuandoan.entity;

import javax.persistence.*;

@Entity
public class Sentence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "question")
    @Lob
    private String question;

    @Column(name = "a")
    @Lob
    private String a;

    @Column(name = "b")
    @Lob
    private String b;

    @Column(name = "c")
    @Lob
    private String c;

    @Column(name = "d")
    @Lob
    private String d;

    @Column(name = "answer")
    @Convert(converter = AnswerConverter.class)
    private Answer answer;

    @Column(name = "solution")
    @Lob
    private String solution;

    @Column(name = "type")
    private String type;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;

    public Sentence() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    @Override
    public String toString() {
        return "Sentence{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", a='" + a + '\'' +
                ", b='" + b + '\'' +
                ", c='" + c + '\'' +
                ", d='" + d + '\'' +
                ", answer=" + answer +
                ", solution='" + solution + '\'' +
                ", type='" + type + '\'' +
//                ", exam=" + exam +
                '}';
    }

    @Converter
    public static class AnswerConverter
            implements AttributeConverter<Answer, Character> {

        public Character convertToDatabaseColumn(Answer value) {
            if (value == null) {
                return null;
            }

            return value.getCode();
        }

        public Answer convertToEntityAttribute(Character value) {
            if (value == null) {
                return null;
            }

            return Answer.fromCode(value);
        }
    }
}


