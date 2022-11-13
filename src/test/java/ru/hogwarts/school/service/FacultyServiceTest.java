package ru.hogwarts.school.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FacultyServiceTest {
    @Mock
    private FacultyRepository repository;
    @InjectMocks
    private FacultyService out;

    Faculty testFaculty = new Faculty(1L, "Slytherin", "green");

    Long id = 1L;

    @Test
    public void returnsAddedStudent() {
        when(repository.save(testFaculty)).thenReturn(testFaculty);
        assertEquals(testFaculty, out.addFaculty(testFaculty));
    }

    @Test
    public void returnsEditedStudent() {
        when(repository.save(testFaculty)).thenReturn(testFaculty);
        assertEquals(testFaculty, out.editFaculty(testFaculty));
    }

    @Test
    public void returnsFoundStudent() {
        when(repository.findById(id)).thenReturn(Optional.of(testFaculty));
        assertEquals(testFaculty, out.findFaculty(id));
    }

}
