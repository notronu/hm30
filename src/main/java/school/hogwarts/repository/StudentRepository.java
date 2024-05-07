package school.hogwarts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.hogwarts.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}