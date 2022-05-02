package com.tuandoan.convert;

import com.tuandoan.dto.SentenceDTO;
import com.tuandoan.entity.Sentence;

public class ConvertSentenceToSentenceDTO {

    public static SentenceDTO convert(Sentence sentence){
        SentenceDTO sentenceDTO = new SentenceDTO();
        sentenceDTO.setId(sentence.getId());
        sentenceDTO.setQuestion(sentence.getQuestion());
        sentenceDTO.setA(sentence.getA());
        sentenceDTO.setB(sentence.getB());
        sentenceDTO.setC(sentence.getC());
        sentenceDTO.setD(sentence.getD());
        sentenceDTO.setAnswer(sentence.getAnswer());
        sentenceDTO.setSolution(sentence.getSolution());
        sentenceDTO.setType(sentence.getType());
        return sentenceDTO;
    }
}
