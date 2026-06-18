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

    public ExamRecordService(ExamRecordRepository examRecordRepository,
                             StudentRepository studentRepository,
                             ExamRepository examRepository) {
        this.examRecordRepository = examRecordRepository;
        this.studentRepository = studentRepository;
        this.examRepository = examRepository;
    }

    public List<ExamRecord> readExamRecords() {
        return examRecordRepository.findAllByOrderByIdAsc();
    }

    public ExamRecord getRecordById(Long id) {
        return examRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exam record with ID " + id + " not found."));
    }

    public ExamRecord createRecord(Long studentId, Long examId, Integer grade) {
        validateGrade(grade);

        Student student = getStudent(studentId);
        Exam exam = getExam(examId);

        ExamRecord record = new ExamRecord();
        record.setStudent(student);
        record.setExam(exam);
        record.setGrade(grade);

        return examRecordRepository.save(record);
    }

    public ExamRecord updateRecord(Long recordId, Long studentId, Long examId, Integer grade) {
        validateGrade(grade);

        ExamRecord record = getRecordById(recordId);
        Student student = getStudent(studentId);
        Exam exam = getExam(examId);

        record.setStudent(student);
        record.setExam(exam);
        record.setGrade(grade);

        return examRecordRepository.save(record);
    }

    public ExamRecord updateRecordGrade(Long id, Integer newGrade) {
        validateGrade(newGrade);

        ExamRecord record = getRecordById(id);
        record.setGrade(newGrade);

        return examRecordRepository.save(record);
    }

    public void deleteRecord(Long id) {
        ExamRecord record = getRecordById(id);
        examRecordRepository.delete(record);
    }

    private Student getStudent(Long studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student with ID " + studentId + " not found."));
    }

    private Exam getExam(Long examId) {
        return examRepository.findById(examId)
                .orElseThrow(() -> new RuntimeException("Exam with ID " + examId + " not found."));
    }

    private void validateGrade(Integer grade) {
        if (grade == null) {
            return;
        }

        if (grade < 1 || grade > 10) {
            throw new RuntimeException("Grade must be between 1 and 10.");
        }
    }
}