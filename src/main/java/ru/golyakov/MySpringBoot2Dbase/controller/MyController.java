package ru.golyakov.MySpringBoot2Dbase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.golyakov.MySpringBoot2Dbase.entity.Student;
import ru.golyakov.MySpringBoot2Dbase.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public List<Student> allStudents() {
        List<Student> allStudents = studentService.getAllStudents();
        return allStudents;
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<?> getStudent(@PathVariable("id") int id) {
        Student student = studentService.getStudent(id);
        if (student == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Студент с заданным id не найден");
        }
        return ResponseEntity.ok(student);
    }

    @PostMapping("/students")
    public ResponseEntity<?> saveStudent(@RequestBody Student student) {
        if (student.getId() != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("При создании студента не указывайте id");
        }
        Student savingStudent = studentService.saveStudent(student);

        return ResponseEntity.status(HttpStatus.CREATED).body(savingStudent);
    }

    @PutMapping("/students")
    public ResponseEntity<?> updateStudent(@RequestBody Student student) {
        Integer id = student.getId();
        if (id == null) {
            return ResponseEntity.badRequest().body("Для обновления студента укажите id");
        }
        if (studentService.getStudent(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Студент с данным id не найден");
        }
        Student updatingStudent = studentService.saveStudent(student);
        return ResponseEntity.ok(updatingStudent);
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable("id") int id) {
        Student student = studentService.getStudent(id);
        if (student == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Студент с данным id не найден");
        }
        studentService.deleteStudent(id);

        return ResponseEntity.noContent().build();
    }

}
