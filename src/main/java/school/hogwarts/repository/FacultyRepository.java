package school.hogwarts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.hogwarts.model.Faculty;

import java.util.Collection;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    Collection<Faculty> findByNameIgnoreCaseOrColorIgnoreCase(String name, String color);
}
