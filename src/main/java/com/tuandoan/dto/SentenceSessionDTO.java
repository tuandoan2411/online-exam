package com.tuandoan.dto;

public class SentenceSessionDTO {
    private SentenceDTO sentenceDTO = new SentenceDTO();
    private String typeOfEndLevel;

    public SentenceDTO getSentenceDTO() {
        return sentenceDTO;
    }

    public void setSentenceDTO(SentenceDTO sentenceDTO) {
        this.sentenceDTO = sentenceDTO;
    }

    public String getTypeOfEndLevel() {
        return typeOfEndLevel;
    }

    public void setTypeOfEndLevel(String typeOfEndLevel) {
        this.typeOfEndLevel = typeOfEndLevel;
    }
}
