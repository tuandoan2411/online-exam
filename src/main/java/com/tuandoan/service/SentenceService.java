package com.tuandoan.service;

import com.tuandoan.entity.Sentence;

public interface SentenceService {
    void delete(Integer id);

    void saveOrUpdate(Sentence sentence);

    void save(Sentence sentence, int examId);
}
