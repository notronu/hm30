package school.hogwarts.service;

import org.springframework.stereotype.Service;
import school.hogwarts.exception.EntityNotFoundException;
import school.hogwarts.model.Student;
import school.hogwarts.repository.StudentRepository;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student add(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student get(Long id) {
        return studentRepository.findById(id).orElseThrow(EntityNotFoundException::new);

    }

    @Override
    public Student remove(Long id) {
        Student student = get(id);
        studentRepository.deleteById(id);
        return student;
    }

    @Override
    public Student update(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Collection<Student> getByAge(Integer age) {
        return getAll().stream()
                .filter(s -> s.getAge().equals(age))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Student> getAll() {
        return studentRepository.findAll();
    }
}
