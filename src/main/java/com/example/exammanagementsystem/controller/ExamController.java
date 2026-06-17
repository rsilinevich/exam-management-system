package com.example.exammanagementsystem.controller;

import com.example.exammanagementsystem.model.Exam;
import com.example.exammanagementsystem.service.ExamService;
import jakarta.validation.Valid;
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

    @PostMapping
    public String createExam(@Valid @RequestBody Exam exam) {
        examService.createExam(exam);
        return "Exam created successfully!";
    }

    @GetMapping
    public Iterable<Exam> readExams() {
        return examService.readExams();
    }

    @PutMapping("/{id}")
    public String updateExam(@PathVariable Long id, @Valid @RequestBody Exam updatedExamData) {
        examService.updateExam(id, updatedExamData);
        return "Exam updated successfully!";
    }

    @DeleteMapping("/{id}")
    public String deleteExam(@PathVariable Long id) {
        examService.deleteExam(id);
        return "Exam deleted successfully!";
    }
}
