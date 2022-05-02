package com.tuandoan.service;

import com.tuandoan.dto.ExamDTO;
import com.tuandoan.dto.SentenceDTO;
import com.tuandoan.entity.Exam;
import com.tuandoan.entity.Sentence;

import java.util.List;

public interface ExamService {
    void addExam(ExamDTO examDTO);

    void updateExam(ExamDTO exam);

    ExamDTO getExam(Integer examId);

    boolean allowDelete(Integer examId);

    void delete(Integer examId);

    List<ExamDTO> getAllExam();

    List<SentenceDTO> getSentencesOfExam(Integer examId);
}
