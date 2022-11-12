package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final HashMap<Long, Student> students = new HashMap<>();
    private long lastID = 0L;

    public Student addStudent(Student student) {
        student.setId(++lastID);
        students.put(lastID, student);
        return student;
    }

    public Student findStudent(long id) {
        return students.get(id);
    }

    public Student editStudent(Long id, Student student) {
        if (students.containsKey(student.getId())) {
            students.put(student.getId(), student);
            return student;
        }
        return null;
    }

    public Student deleteStudent(long id) {
        return students.remove(id);
    }

    public List<Student> allStudentByAge(int ageToSort) {
        return students.values().stream().
                filter(e -> e.getAge() == ageToSort).
                collect(Collectors.toList());
    }
}
