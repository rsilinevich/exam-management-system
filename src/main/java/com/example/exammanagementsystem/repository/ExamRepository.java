package com.example.exammanagementsystem.repository;

import com.example.exammanagementsystem.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExamRepository extends JpaRepository<Exam, Long> {

    Optional<Exam> findFirstByTitle(String title);

    List<Exam> findAllByOrderByIdAsc();
}