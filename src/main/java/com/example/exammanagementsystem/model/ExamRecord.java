package com.example.exammanagementsystem.model;

import lombok.Data;

@Data
public class ExamRecord {
    Long id;
    Student student;
    Exam exam;
    Integer grade;
}
