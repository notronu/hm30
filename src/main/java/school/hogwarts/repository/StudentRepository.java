package school.hogwarts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.hogwarts.model.Student;

import java.util.Collection;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Collection<Student> findByAgeBetween(Integer startAge, Integer endAge);


}