package com.example.exammanagementsystem.controller;

import com.example.exammanagementsystem.model.ExamRecord;
import com.example.exammanagementsystem.service.ExamRecordService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final ExamRecordService examRecordService;

    public HomeController(ExamRecordService examRecordService) {
        this.examRecordService = examRecordService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<ExamRecord> records = examRecordService.readExamRecords();
        model.addAttribute("records", records);
        return "home";
    }
}