package com.example.exammanagementsystem.controller;

import com.example.exammanagementsystem.model.ExamRecord;
import com.example.exammanagementsystem.model.ExamRecordRequest;
import com.example.exammanagementsystem.service.ExamRecordService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exam-records")
public class ExamRecordController {

    private final ExamRecordService examRecordService;

    public ExamRecordController(ExamRecordService examRecordService) {
        this.examRecordService = examRecordService;
    }

    @GetMapping
    public List<ExamRecord> readExamRecords() {
        return examRecordService.readExamRecords();
    }

    @GetMapping("/{id}")
    public ExamRecord readExamRecord(@PathVariable Long id) {
        return examRecordService.getRecordById(id);
    }

    @PostMapping
    public ExamRecord createRecord(@Valid @RequestBody ExamRecordRequest request) {
        return examRecordService.createRecord(
                request.getStudentId(),
                request.getExamId(),
                request.getGrade()
        );
    }

    @PutMapping("/{id}")
    public ExamRecord updateRecord(@PathVariable Long id,
                                   @Valid @RequestBody ExamRecordRequest request) {
        return examRecordService.updateRecord(
                id,
                request.getStudentId(),
                request.getExamId(),
                request.getGrade()
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecord(@PathVariable Long id) {
        examRecordService.deleteRecord(id);
        return ResponseEntity.noContent().build();
    }
}