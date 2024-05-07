package school.hogwarts.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.hogwarts.model.Faculty;
import school.hogwarts.model.Student;
import school.hogwarts.service.FacultyService;

import java.util.Collection;

@RequestMapping("faculty")
@RestController
@Tag(name = "API для работы со факультетами")
public class FacultyController {

    private final FacultyService service;

    public FacultyController(FacultyService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Создание факультета")
    public ResponseEntity<Faculty> create(@RequestBody Faculty faculty) {
        Faculty addedFaculty= service.add(faculty);
        return ResponseEntity.ok(addedFaculty);
    }


    @PutMapping
    @Operation(summary = "Обновление факультета")
    public ResponseEntity<Faculty> update(@RequestBody Faculty faculty) {
        Faculty updatedFaculty = service.update(faculty);
        return ResponseEntity.ok(updatedFaculty);
    }


    @DeleteMapping("{id}")
    @Operation(summary = "Удаление факультета")
    public ResponseEntity<Faculty> remove(@PathVariable Long id) {
        Faculty deletedFaculty = service.remove(id);
        return ResponseEntity.ok(deletedFaculty);
    }


    @GetMapping("{id}")
    @Operation(summary = "Получение факультета по id")
    public ResponseEntity<Faculty> get(@PathVariable Long id) {
        Faculty faculty = service.get(id);
        return ResponseEntity.ok(faculty);
    }


    @GetMapping("by-color")
    @Operation(summary = "Получение факультетов по цвету или имени")
    public ResponseEntity<Collection<Faculty>> getByColorOrName(@RequestParam String color, @RequestParam String name) {
        Collection<Faculty> faculties = service.getByColorOrName(color, name);
        return ResponseEntity.ok(faculties);
    }


    @GetMapping("all")
    @Operation(summary = "Получение всех факультетов")
    public ResponseEntity<Collection<Faculty>> getAll() {
        Collection<Faculty> faculties = service.getAll();
        return ResponseEntity.ok(faculties);
    }

    @GetMapping("students/{facultyId}")
    @Operation(summary = "Получение студентов факультета")
    public ResponseEntity<Collection<Student>> getStudentsOfFaculty(@PathVariable Long facultyId) {
        Collection<Student> students = service.getStudents(facultyId);
        return ResponseEntity.ok(students);


    }


}
