package com.example.exammanagementsystem.controller;

import com.example.exammanagementsystem.model.Exam;
import com.example.exammanagementsystem.service.ExamService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exams")
public class ExamController {
    // Use Dependency Injection to bring your ExamService into the controller via the constructor.
    // Write a @GetMapping method to fetch the exams, and a @PostMapping method to create one.
    private final ExamService examService;

    public ExamController(ExamService examService) {
        this.examService = examService;
    }

    @GetMapping
    public Iterable<Exam> getAllExams() {
        return examService.getAllExams();
    }

    @PostMapping
    public String addNewExam(@RequestBody Exam exam) {
        examService.addNewExam(exam);
        return "Exam added successfully!";
    }
}
