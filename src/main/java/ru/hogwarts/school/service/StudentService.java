package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exceptions.StudentNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student findStudent(long id) {
        return studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
    }

    public Student editStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        studentRepository.deleteById(id);
    }

    public List<Student> allStudentByAge(int ageToSort) {
        return studentRepository.findByAge(ageToSort);
    }

    public List<Student> allStudentBetweenAge(int low, int high) {
        return studentRepository.findByAgeBetween(low, high);
    }

    public List<Student> allStudentByFaculty(Long facultyID) {
        return studentRepository.findAll().stream().
                filter(e -> Objects.equals(e.getFaculty().getId(), facultyID)).collect(Collectors.toList());
    }

    public Faculty getFacultyByStudent(Long id) {
        return studentRepository.findById(id).orElseThrow(StudentNotFoundException::new).getFaculty();
    }
}
