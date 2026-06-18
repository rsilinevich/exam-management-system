package com.example.exammanagementsystem.controller;

import com.example.exammanagementsystem.model.Exam;
import com.example.exammanagementsystem.service.ExamService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/exams")
public class ExamWebController {

    private final ExamService examService;

    public ExamWebController(ExamService examService) {
        this.examService = examService;
    }

    @GetMapping
    public String listExams(Model model) {
        model.addAttribute("exams", examService.readExams());
        return "exams/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("exam", new Exam());
        return "exams/form";
    }

    @PostMapping
    public String createExam(@Valid @ModelAttribute("exam") Exam exam,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "exams/form";
        }

        examService.createExam(exam);
        return "redirect:/exams";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id,
                               Model model) {
        model.addAttribute("exam", examService.getExamById(id));
        return "exams/form";
    }

    @PostMapping("/update/{id}")
    public String updateExam(@PathVariable Long id,
                             @Valid @ModelAttribute("exam") Exam exam,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "exams/form";
        }

        examService.updateExam(id, exam);
        return "redirect:/exams";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/delete/{id}")
    public String deleteExam(@PathVariable Long id) {
        examService.deleteExam(id);
        return "redirect:/exams";
    }
}