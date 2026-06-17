package com.example.exammanagementsystem.controller;

import com.example.exammanagementsystem.model.ExamRecord;
import com.example.exammanagementsystem.model.ExamRecordRequest;
import com.example.exammanagementsystem.service.ExamRecordService;
import jakarta.validation.Valid;
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

    @PostMapping
    public ExamRecord createRecord(@Valid @RequestBody ExamRecordRequest request){
        return examRecordService.createRecord(request.getStudentId(), request.getExamId(), request.getGrade());
    }
}
