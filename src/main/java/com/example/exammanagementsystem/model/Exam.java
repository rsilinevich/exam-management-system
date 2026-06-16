package com.example.exammanagementsystem.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Exam {
    Long id;
    String title;
    LocalDate date;
    String professor;
}
