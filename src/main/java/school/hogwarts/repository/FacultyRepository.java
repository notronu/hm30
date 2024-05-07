package school.hogwarts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.hogwarts.model.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
}
