package com.tuandoan.service;

import com.tuandoan.entity.Exam;
import com.tuandoan.entity.Sentence;

import java.util.List;

public interface ExamService {
    void addExam(Exam exam);

    Exam getExam(Integer examId);

    boolean allowDelete(Integer examId);

    void delete(Integer examId);

    List<Exam> getAllExam();

    List<Sentence> getSentencesOfExam(Integer examId);
}
