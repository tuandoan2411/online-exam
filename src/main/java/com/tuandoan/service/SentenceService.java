package com.tuandoan.service;

import com.tuandoan.dto.SentenceDTO;
import com.tuandoan.entity.Sentence;

public interface SentenceService {
    void delete(Integer id);

    void saveOrUpdate(SentenceDTO sentence, int examId);
}
