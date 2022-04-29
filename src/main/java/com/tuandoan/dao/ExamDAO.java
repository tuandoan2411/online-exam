package com.tuandoan.dao;

import com.tuandoan.entity.Exam;
import com.tuandoan.entity.Sentence;

import java.util.List;

public interface ExamDAO {
    void addExam(Exam exam);

    void updateExam(Exam exam);

    Exam getExam(Integer examId);

    boolean allowDelete(Integer examId);

    void delete(Integer examId);

    List<Exam> getAllExam();

    List<Sentence> getSentencesOfExam(Integer examId);
}
