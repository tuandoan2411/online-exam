package com.tuandoan.dto;

import java.util.ArrayList;
import java.util.List;

public class SentenceDTOWrapper {
    private List<SentenceSessionDTO> sentenceSessionDTOList;

    public SentenceDTOWrapper(){
        sentenceSessionDTOList = new ArrayList<>();
    }

    public List<SentenceSessionDTO> getSentenceSessionDTOList() {
        return sentenceSessionDTOList;
    }

    public void setSentenceSessionDTOList(List<SentenceSessionDTO> sentenceSessionDTOList) {
        this.sentenceSessionDTOList = sentenceSessionDTOList;
    }
}
