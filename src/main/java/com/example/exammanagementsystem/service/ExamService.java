package com.example.exammanagementsystem.service;

import com.example.exammanagementsystem.model.Exam;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExamService {
    private final List<Exam> exams = new ArrayList<>(); // in memory list to store exams (temporary)

    public List<Exam> getAllExams() {
        return exams; // return the whole list
    }

    public void addNewExam(Exam exam) {
        exams.add(exam); // add a new Exam to the list
    }
}
