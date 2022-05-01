package com.tuandoan.service;

import com.tuandoan.convert.ConvertSentenceDTOToSentence;
import com.tuandoan.dao.SentenceDAO;
import com.tuandoan.dto.SentenceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SentenceServiceImpl implements SentenceService{
    private SentenceDAO sentenceDAO;

    @Autowired
    public SentenceServiceImpl(SentenceDAO sentenceDAO){
        this.sentenceDAO = sentenceDAO;
    }
    
    @Override
    @Transactional
    public void delete(Integer id) {
        sentenceDAO.delete(id);
    }

    @Override
    @Transactional
    public void saveOrUpdate(SentenceDTO sentence, int examId) {
        sentenceDAO.saveOrUpdate(ConvertSentenceDTOToSentence.convert(sentence), examId);
    }
}
