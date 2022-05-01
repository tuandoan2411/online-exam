package com.tuandoan.convert;

import com.tuandoan.dto.ExamDTO;
import com.tuandoan.dto.SentenceDTO;
import com.tuandoan.entity.Exam;
import com.tuandoan.entity.Sentence;
import com.tuandoan.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConvertExamToExamDTO {
    private static TypeService typeService;

    @Autowired
    public ConvertExamToExamDTO(TypeService typeService){
        ConvertExamToExamDTO.typeService = typeService;
    }

    public static ExamDTO convert(Exam exam){
        ExamDTO examDTO = new ExamDTO();
        examDTO.setId(exam.getId());
        examDTO.setTitle(exam.getTitle());
        examDTO.setDescription(exam.getDescription());
        examDTO.setMinutes(exam.getMinutes());
        examDTO.setDate(exam.getDate());
        examDTO.setType(exam.getType());
        examDTO.setNumberOfSentences(exam.getNumberOfSentences());
        examDTO.setNumberOfExams(exam.getNumberOfExams());
        examDTO.setTypeInformation(typeService.getTypeInformationForExam(exam.getType()));
        List<SentenceDTO> sentenceDTOs = new ArrayList<>();
        for(Sentence sentence : exam.getSentences()){
            sentenceDTOs.add(ConvertSentenceToSentenceDTO.convert(sentence));
        }
        examDTO.setSentenceDTOs(sentenceDTOs);

        return examDTO;
    }
}
