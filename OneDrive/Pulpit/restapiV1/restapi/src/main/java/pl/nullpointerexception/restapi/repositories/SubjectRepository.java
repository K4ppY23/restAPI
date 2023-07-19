package pl.nullpointerexception.restapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.nullpointerexception.restapi.model.Learner;
import pl.nullpointerexception.restapi.model.Subject;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    @Query(value = "SELECT * FROM Subjects sub WHERE sub.name = :name ORDER BY sub.id")
    List<Subject> findAllByName(String name);
}
