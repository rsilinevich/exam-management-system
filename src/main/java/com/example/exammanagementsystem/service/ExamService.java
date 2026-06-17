package com.example.exammanagementsystem.service;

import com.example.exammanagementsystem.model.Exam;
import com.example.exammanagementsystem.repository.ExamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamService {
    private final ExamRepository examRepository;

    public ExamService(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    public void createExam(Exam exam) {
        examRepository.save(exam);
    }

    public List<Exam> readExams() {
        return examRepository.findAll(); // return the whole list
    }

    public void updateExam(Long id, Exam updatedExamData) {
        // 1. Find the existing exam in the database (or throw an error if it doesn't exist)
        Exam existingExam = examRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exam not found!"));

        // 2. Update the fields
        existingExam.setTitle(updatedExamData.getTitle());
        existingExam.setDate(updatedExamData.getDate());
        existingExam.setProfessor(updatedExamData.getProfessor());

        // 3. Save it back to the database (Hibernate knows to update, not insert, because the ID already exists)
        examRepository.save(existingExam);
    }

    public void deleteExam(Long id) {
        examRepository.deleteById(id);
    }
}
