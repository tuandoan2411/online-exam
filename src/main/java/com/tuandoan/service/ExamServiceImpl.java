package com.tuandoan.service;

import com.tuandoan.convert.ConvertExamDTOToExam;
import com.tuandoan.convert.ConvertExamToExamDTO;
import com.tuandoan.convert.ConvertSentenceToSentenceDTO;
import com.tuandoan.dao.ExamDAO;
import com.tuandoan.dto.ExamDTO;
import com.tuandoan.dto.SentenceDTO;
import com.tuandoan.entity.Exam;
import com.tuandoan.entity.Sentence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    public void addExam(ExamDTO examDTO) {
        examDAO.addExam(ConvertExamDTOToExam.convert(examDTO));
    }

    @Override
    @Transactional
    public void updateExam(ExamDTO examDTO) {
        examDAO.updateExam(ConvertExamDTOToExam.convert(examDTO));
    }

    @Override
    @Transactional
    public ExamDTO getExam(Integer examId) {
        return ConvertExamToExamDTO.convert(examDAO.getExam(examId));
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
    public List<ExamDTO> getAllExam() {
        List<Exam> exams = examDAO.getAllExam();
        List<ExamDTO> examDTOs = new ArrayList<>();
        for(Exam exam : exams){
            examDTOs.add(ConvertExamToExamDTO.convert(exam));
        }
        return examDTOs;
    }

    @Override
    @Transactional
    public List<SentenceDTO> getSentencesOfExam(Integer examId) {
        List<Sentence> sentences = examDAO.getSentencesOfExam(examId);
        List<SentenceDTO> sentenceDTOs = new ArrayList<>();
        for(Sentence sentence : sentences){
            sentenceDTOs.add(ConvertSentenceToSentenceDTO.convert(sentence));
        }
        return sentenceDTOs;
    }
}
