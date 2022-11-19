package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;


@RestController
@RequestMapping("student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student createdStudent = studentService.addStudent(student);
        return ResponseEntity.ok(createdStudent);
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
        Student student = studentService.findStudent(id);
        return ResponseEntity.ok(student);
    }

    @PutMapping()
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        Student updatedStudent = studentService.editStudent(student);
        if (updatedStudent == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/sort")
    public ResponseEntity getStudentsSorted(@RequestParam(required = false) Long facultyID,
                                                 @RequestParam(required = false) Integer age,
                                                 @RequestParam(required = false) Integer low,
                                                 @RequestParam(required = false) Integer high) {
        if (facultyID != null) {
            return ResponseEntity.ok(studentService.allStudentByFaculty(facultyID));
        }
        if (age != null) {
            return ResponseEntity.ok(studentService.allStudentByAge(age));
        }
        if (high != null && low != null) {
            return ResponseEntity.ok(studentService.allStudentBetweenAge(low, high));
        }
        return ResponseEntity.badRequest().build();
    }
}
