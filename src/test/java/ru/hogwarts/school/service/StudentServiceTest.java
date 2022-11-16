package ru.hogwarts.school.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {
    @Mock
    private StudentRepository repository;
    @InjectMocks
    private StudentService out;

    Student testStudent = new Student(1L, "Harry", 10);

    Long id = 1L;

    @Test
    public void returnsAddedStudent() {
        when(repository.save(testStudent)).thenReturn(testStudent);
        assertEquals(testStudent, out.addStudent(testStudent));
    }

    @Test
    public void returnsEditedStudent() {
        when(repository.save(testStudent)).thenReturn(testStudent);
        assertEquals(testStudent, out.editStudent(testStudent));
    }

    @Test
    public void returnsFoundStudent() {
        when(repository.findById(id)).thenReturn(Optional.of(testStudent));
        assertEquals(testStudent, out.findStudent(id));
    }


}
