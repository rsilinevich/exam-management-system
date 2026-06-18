package com.example.exammanagementsystem.config;

import com.example.exammanagementsystem.model.Exam;
import com.example.exammanagementsystem.model.ExamRecord;
import com.example.exammanagementsystem.model.Student;
import com.example.exammanagementsystem.model.User;
import com.example.exammanagementsystem.repository.ExamRecordRepository;
import com.example.exammanagementsystem.repository.ExamRepository;
import com.example.exammanagementsystem.repository.StudentRepository;
import com.example.exammanagementsystem.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {

    private final StudentRepository studentRepository;
    private final ExamRepository examRepository;
    private final ExamRecordRepository examRecordRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(StudentRepository studentRepository,
                           ExamRepository examRepository,
                           ExamRecordRepository examRecordRepository,
                           UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.examRepository = examRepository;
        this.examRecordRepository = examRecordRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        upsertUser("admin", "admin123", "admin@example.com", "ROLE_ADMIN");
        upsertUser("user", "user123", "user@example.com", "ROLE_USER");

        Student student1 = findOrCreateStudent("Raimonds", "raimonds@example.com");
        Student student2 = findOrCreateStudent("Max", "max@example.com");
        Student student3 = findOrCreateStudent("Anna", "anna@example.com");
        Student student4 = findOrCreateStudent("Laura", "laura@example.com");
        Student student5 = findOrCreateStudent("David", "david@example.com");

        Exam exam1 = findOrCreateExam(
                "Advanced Java Development",
                "Dr. Brown",
                LocalDate.now().plusDays(14)
        );

        Exam exam2 = findOrCreateExam(
                "Relational Databases",
                "Prof. Doe",
                LocalDate.now().plusDays(30)
        );

        Exam exam3 = findOrCreateExam(
                "Spring Boot MVC",
                "Prof. Johnson",
                LocalDate.now().plusDays(21)
        );

        Exam exam4 = findOrCreateExam(
                "Software Testing",
                "Dr. Smith",
                LocalDate.now().plusDays(10)
        );

        Exam exam5 = findOrCreateExam(
                "Web Application Security",
                "Prof. Wilson",
                LocalDate.now().plusDays(40)
        );

        if (examRecordRepository.count() == 0) {
            createRecord(student1, exam1, 10);
            createRecord(student2, exam2, 8);
            createRecord(student3, exam3, 1);
            createRecord(student4, exam4, 3);
            createRecord(student5, exam5, 4);
        }

        System.out.println("Dummy users created:");
        System.out.println("admin / admin123");
        System.out.println("user / user123");
    }

    private Student findOrCreateStudent(String name, String email) {
        return studentRepository.findByEmail(email)
                .orElseGet(() -> {
                    Student student = new Student();
                    student.setName(name);
                    student.setEmail(email);
                    return studentRepository.save(student);
                });
    }

    private Exam findOrCreateExam(String title, String professor, LocalDate date) {
        return examRepository.findFirstByTitle(title)
                .orElseGet(() -> {
                    Exam exam = new Exam();
                    exam.setTitle(title);
                    exam.setProfessor(professor);
                    exam.setDate(date);
                    return examRepository.save(exam);
                });
    }

    private ExamRecord createRecord(Student student, Exam exam, Integer grade) {
        ExamRecord record = new ExamRecord();
        record.setStudent(student);
        record.setExam(exam);
        record.setGrade(grade);
        return examRecordRepository.save(record);
    }

    private User upsertUser(String username, String rawPassword, String email, String role) {
        User user = userRepository.findByUsername(username).orElseGet(User::new);

        user.setUsername(username);
        user.setEmail(email);
        user.setRole(role);

        if (user.getPassword() == null || user.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(rawPassword));
        }

        return userRepository.save(user);
    }
}