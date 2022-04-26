package com.tuandoan.service;

import com.tuandoan.dao.ExamDAO;
import com.tuandoan.entity.Exam;
import com.tuandoan.entity.Sentence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExamServiceImpl implements ExamService{
    private ExamDAO examDAO;

    @Autowired
    public ExamServiceImpl(ExamDAO examDAO){
        this.examDAO = examDAO;
    }

    @Override
    @Transactional
    public void addExam(Exam exam) {
        examDAO.addExam(exam);
    }

    @Override
    @Transactional
    public Exam getExam(Integer examId) {
        return examDAO.getExam(examId);
    }

    @Override
    @Transactional
    public boolean allowDelete(Integer examId) {
        return examDAO.allowDelete(examId);
    }

    @Override
    @Transactional
    public void delete(Integer examId) {
        examDAO.delete(examId);
    }

    @Override
    @Transactional
    public List<Exam> getAllExam() {
        return examDAO.getAllExam();
    }

    @Override
    @Transactional
    public List<Sentence> getSentencesOfExam(Integer examId) {
        return examDAO.getSentencesOfExam(examId);
    }
}
