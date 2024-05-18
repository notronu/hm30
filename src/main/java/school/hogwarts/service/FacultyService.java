package school.hogwarts.service;

import school.hogwarts.model.Faculty;
import school.hogwarts.model.Student;

import java.util.Collection;

public interface FacultyService {

    Faculty add(Faculty  faculty );

    Faculty get(Long id);

    Faculty  remove(Long id);

    Faculty  update(Faculty  faculty);

    Collection<Faculty > getByColorOrName(String Color, String name);

    Collection<Faculty> getAll();

    Collection<Student> getStudents(Long facultyId);


}
