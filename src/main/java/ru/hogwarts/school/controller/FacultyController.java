package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

@RestController
@RequestMapping("faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public ResponseEntity<Faculty> createFaculty(@RequestBody Faculty faculty) {
        Faculty createdFaculty = facultyService.addFaculty(faculty);
        return ResponseEntity.ok(createdFaculty);
    }

    @GetMapping("{id}")
    public ResponseEntity<Faculty> getFaculty(@PathVariable Long id) {
        Faculty faculty = facultyService.findFaculty(id);
        return ResponseEntity.ok(faculty);
    }

    @PutMapping()
    public ResponseEntity<Faculty> updateFaculty(@RequestBody Faculty faculty) {
        Faculty updatedFaculty = facultyService.editFaculty(faculty);
        if (updatedFaculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedFaculty);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity getFaculty (@RequestParam(required = false) String color,
                                      @RequestParam(required = false) String name) {
        if (color != null) {
            return ResponseEntity.ok(facultyService.facultyByColor(color));
        }
        if (name != null) {
            return ResponseEntity.ok(facultyService.facultyByName(name));
        }
        return ResponseEntity.badRequest().build();
    }
    @GetMapping("getStudents")
    public ResponseEntity studentsByFaculty(@RequestParam Long id) {
        return ResponseEntity.ok(facultyService.studentsByFaculty(id));
    }
}
