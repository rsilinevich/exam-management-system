package com.example.exammanagementsystem.controller;

import com.example.exammanagementsystem.model.ExamRecord;
import com.example.exammanagementsystem.service.ExamRecordService;
import com.example.exammanagementsystem.service.ExamService;
import com.example.exammanagementsystem.service.StudentService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/records")
public class RecordWebController {

    private final ExamRecordService examRecordService;
    private final StudentService studentService;
    private final ExamService examService;

    public RecordWebController(ExamRecordService examRecordService,
                               StudentService studentService,
                               ExamService examService) {
        this.examRecordService = examRecordService;
        this.studentService = studentService;
        this.examService = examService;
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("students", studentService.readStudents());
        model.addAttribute("exams", examService.readExams());
        return "records/form";
    }

    @PostMapping("/save")
    public String saveRecord(@RequestParam Long studentId,
                             @RequestParam Long examId,
                             @RequestParam(required = false) Integer grade) {
        examRecordService.createRecord(studentId, examId, grade);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id,
                               Model model) {
        ExamRecord record = examRecordService.getRecordById(id);

        model.addAttribute("record", record);
        model.addAttribute("students", studentService.readStudents());
        model.addAttribute("exams", examService.readExams());

        return "records/edit";
    }

    @PostMapping("/update/{id}")
    public String updateRecord(@PathVariable Long id,
                               @RequestParam Long studentId,
                               @RequestParam Long examId,
                               @RequestParam(required = false) Integer grade) {
        examRecordService.updateRecord(id, studentId, examId, grade);
        return "redirect:/";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/delete/{id}")
    public String deleteRecord(@PathVariable Long id) {
        examRecordService.deleteRecord(id);
        return "redirect:/";
    }
}