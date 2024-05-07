package school.hogwarts.service;

import org.springframework.stereotype.Service;
import school.hogwarts.exception.EntityNotFoundException;
import school.hogwarts.model.Faculty;
import school.hogwarts.model.Student;
import school.hogwarts.repository.FacultyRepository;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }


    @Override
    public Faculty add(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty get(Long id) {
        return facultyRepository.findById(id).orElseThrow(EntityNotFoundException::new);

    }

    @Override
    public Faculty remove(Long id) {
        Faculty faculty = get(id);
        facultyRepository.deleteById(id);
        return faculty;
    }


    @Override
    public Faculty update(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public Collection<Faculty> getByColorOrName(String color, String name) {
        return facultyRepository.findByNameIgnoreCaseOrColorIgnoreCase(color, name);
    }


    @Override
    public Collection<Faculty> getAll() {
        return facultyRepository.findAll();
    }


    @Override
    public Collection<Student> getStudents(Long facultyId) {
        return get(facultyId).getStudents();

    }
}