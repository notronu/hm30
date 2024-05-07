package school.hogwarts.service;

import school.hogwarts.model.Faculty;
import school.hogwarts.model.Student;

import java.util.Collection;

public interface StudentService {

    Student add(Student student);

    Student get(Long id);

    Student remove(Long id);

    Student update(Student student);

    Collection<Student> getByAge(Integer startAge, Integer endAge);

    Collection<Student> getAll();

    Faculty getFacultyOfStudent(Long studentId);
}
