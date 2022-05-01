package com.tuandoan.dto;

import com.tuandoan.entity.Level5;

import java.util.List;

public class ExamSessionDTO {
    private ExamDTO examDTO = new ExamDTO();
    private boolean hasDefaultInformation;
    private List<Level5> level5s;

    public ExamDTO getExamDTO() {
        return examDTO;
    }

    public void setExamDTO(ExamDTO examDTO) {
        this.examDTO = examDTO;
    }

    public boolean getHasDefaultInformation() {
        return hasDefaultInformation;
    }

    public void setHasDefaultInformation(boolean hasDefaultInformation) {
        this.hasDefaultInformation = hasDefaultInformation;
    }

    public List<Level5> getLevel5s() {
        return level5s;
    }

    public void setLevel5s(List<Level5> level5s) {
        this.level5s = level5s;
    }

    public void clean() {
        examDTO.clean();
        hasDefaultInformation = false;
        level5s = null;
    }
}
