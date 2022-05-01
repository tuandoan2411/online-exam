package com.tuandoan.convert;

import com.tuandoan.dto.ExamDTO;
import com.tuandoan.dto.SentenceDTO;
import com.tuandoan.entity.Exam;
import com.tuandoan.entity.Sentence;

import java.util.ArrayList;
import java.util.List;

public class ConvertExamDTOToExam {
    
    public static Exam convert(ExamDTO examDTO){
        Exam exam = new Exam();
        exam.setId(examDTO.getId());
        exam.setTitle(examDTO.getTitle());
        exam.setDescription(examDTO.getDescription());
        exam.setMinutes(examDTO.getMinutes());
        exam.setDate(examDTO.getDate());
        exam.setType(examDTO.getType());
        exam.setNumberOfSentences(examDTO.getNumberOfSentences());
        exam.setNumberOfExams(examDTO.getNumberOfExams());
        List<Sentence> sentences = new ArrayList<>();
        for(SentenceDTO sentenceDTO : examDTO.getSentenceDTOs()){
            Sentence sentence = ConvertSentenceDTOToSentence.convert(sentenceDTO);
            sentence.setExam(exam);
            sentences.add(sentence);
        }
        exam.setSentences(sentences);
        return exam;
    }
}
