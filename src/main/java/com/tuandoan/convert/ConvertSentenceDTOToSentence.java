package com.tuandoan.convert;

import com.tuandoan.dto.SentenceDTO;
import com.tuandoan.entity.Sentence;

public class ConvertSentenceDTOToSentence {

    public static Sentence convert(SentenceDTO sentenceDTO){
        Sentence sentence = new Sentence();
        sentence.setId(sentenceDTO.getId());
        sentence.setQuestion(sentenceDTO.getQuestion());
        sentence.setA(sentenceDTO.getA());
        sentence.setB(sentenceDTO.getB());
        sentence.setC(sentenceDTO.getC());
        sentence.setD(sentenceDTO.getD());
        sentence.setAnswer(sentenceDTO.getAnswer());
        sentence.setSolution(sentenceDTO.getSolution());
        sentence.setType(sentenceDTO.getType());
        return sentence;
    }
}
