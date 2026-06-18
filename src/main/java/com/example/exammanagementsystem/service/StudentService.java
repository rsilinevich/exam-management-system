package com.example.exammanagementsystem.service;

import com.example.exammanagementsystem.model.Student;
import com.example.exammanagementsystem.repository.ExamRecordRepository;
import com.example.exammanagementsystem.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final ExamRecordRepository examRecordRepository;

    public StudentService(StudentRepository studentRepository,
                          ExamRecordRepository examRecordRepository) {
        this.studentRepository = studentRepository;
        this.examRecordRepository = examRecordRepository;
    }

    public List<Student> readStudents() {
        return studentRepository.findAllByOrderByIdAsc();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student with ID " + id + " not found."));
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(Long id, Student updatedStudentData) {
        Student existingStudent = getStudentById(id);

        existingStudent.setName(updatedStudentData.getName());
        existingStudent.setEmail(updatedStudentData.getEmail());

        return studentRepository.save(existingStudent);
    }

    public void deleteStudent(Long id) {
        if (examRecordRepository.existsByStudentId(id)) {
            throw new RuntimeException("Cannot delete student because they are used in exam records.");
        }

        studentRepository.deleteById(id);
    }
}