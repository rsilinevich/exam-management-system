package com.example.exammanagementsystem.controller;

import com.example.exammanagementsystem.model.Student;
import com.example.exammanagementsystem.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/students")
public class StudentWebController {

    private final StudentService studentService;

    public StudentWebController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.readStudents());
        return "students/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("student", new Student());
        return "students/form";
    }

    @PostMapping
    public String createStudent(@Valid @ModelAttribute("student") Student student,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "students/form";
        }

        try {
            studentService.createStudent(student);
        } catch (DataIntegrityViolationException ex) {
            bindingResult.rejectValue("email", "duplicate", "Email already exists.");
            return "students/form";
        }

        return "redirect:/students";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id,
                               Model model) {
        model.addAttribute("student", studentService.getStudentById(id));
        return "students/form";
    }

    @PostMapping("/update/{id}")
    public String updateStudent(@PathVariable Long id,
                                @Valid @ModelAttribute("student") Student student,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "students/form";
        }

        try {
            studentService.updateStudent(id, student);
        } catch (DataIntegrityViolationException ex) {
            bindingResult.rejectValue("email", "duplicate", "Email already exists.");
            return "students/form";
        }

        return "redirect:/students";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }
}