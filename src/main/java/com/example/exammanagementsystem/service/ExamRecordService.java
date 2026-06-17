package com.example.exammanagementsystem.service;

import com.example.exammanagementsystem.model.Exam;
import com.example.exammanagementsystem.model.ExamRecord;
import com.example.exammanagementsystem.model.Student;
import com.example.exammanagementsystem.repository.ExamRecordRepository;
import com.example.exammanagementsystem.repository.ExamRepository;
import com.example.exammanagementsystem.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamRecordService {
    private final ExamRecordRepository examRecordRepository;
    private final StudentRepository studentRepository;
    private final ExamRepository examRepository;

    public ExamRecordService(ExamRecordRepository examRecordRepository, StudentRepository studentRepository, ExamRepository examRepository) {
        this.examRecordRepository = examRecordRepository;
        this.studentRepository = studentRepository;
        this.examRepository = examRepository;
    }

    public List<ExamRecord> readExamRecords() {
        return examRecordRepository.findAll();
    }

    public ExamRecord createRecord(Long studentId, Long examId, Integer grade) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student with ID " + studentId + " not found!"));

        Exam exam = examRepository.findById(examId).orElseThrow(() -> new RuntimeException("Exam with ID " + examId + " not found!"));

        ExamRecord record = new ExamRecord();
        record.setStudent(student);
        record.setExam(exam);
        record.setGrade(grade);

        return examRecordRepository.save(record);
    }
}
