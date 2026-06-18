package com.example.exammanagementsystem.service;

import com.example.exammanagementsystem.model.Exam;
import com.example.exammanagementsystem.repository.ExamRecordRepository;
import com.example.exammanagementsystem.repository.ExamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamService {

    private final ExamRepository examRepository;
    private final ExamRecordRepository examRecordRepository;

    public ExamService(ExamRepository examRepository,
                       ExamRecordRepository examRecordRepository) {
        this.examRepository = examRepository;
        this.examRecordRepository = examRecordRepository;
    }

    public Exam createExam(Exam exam) {
        return examRepository.save(exam);
    }

    public List<Exam> readExams() {
        return examRepository.findAllByOrderByIdAsc();
    }

    public Exam getExamById(Long id) {
        return examRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exam with ID " + id + " not found."));
    }

    public Exam updateExam(Long id, Exam updatedExamData) {
        Exam existingExam = getExamById(id);

        existingExam.setTitle(updatedExamData.getTitle());
        existingExam.setDate(updatedExamData.getDate());
        existingExam.setProfessor(updatedExamData.getProfessor());

        return examRepository.save(existingExam);
    }

    public void deleteExam(Long id) {
        if (examRecordRepository.existsByExamId(id)) {
            throw new RuntimeException("Cannot delete exam because it is used in exam records.");
        }

        examRepository.deleteById(id);
    }
}