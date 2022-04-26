package com.tuandoan.dao;

import com.tuandoan.entity.Exam;
import com.tuandoan.entity.Sentence;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExamDAOImpl implements ExamDAO{
    private SessionFactory sessionFactory;

    @Autowired
    public ExamDAOImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addExam(Exam exam) {
        Session session = sessionFactory.getCurrentSession();
        session.save(exam);
    }

    @Override
    public Exam getExam(Integer examId) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Exam.class, examId);
    }

    @Override
    public boolean allowDelete(Integer examId) {
        return true;
    }

    @Override
    public void delete(Integer examId) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(session.get(Exam.class, examId));
    }

    @Override
    public List<Exam> getAllExam() {
        Session session = sessionFactory.getCurrentSession();
        Query<Exam> theQuery = session.createQuery("from Exam", Exam.class);
        List<Exam> exams = theQuery.getResultList();
        return exams;
    }

    @Override
    public List<Sentence> getSentencesOfExam(Integer examId) {
        Session session = sessionFactory.getCurrentSession();
        Exam exam = session.get(Exam.class, examId);
        for(Sentence sentence : exam.getSentences()){
        }
        System.out.println("getSentencesOfExam(Integer examId)");
        return exam.getSentences();
    }
}
