package pl.nullpointerexception.restapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.nullpointerexception.restapi.model.Learner;
import pl.nullpointerexception.restapi.model.Student;

import java.util.List;
import java.util.Optional;

@Repository
public interface LearnerRepository extends JpaRepository<Learner, Long> {
    @Query(value = "SELECT * FROM Learners l WHERE l.name = :name ORDER BY l.id")
    List<Learner> findAllByName(@Param("name") String name);

    @Query(value = "SELECT * FROM Learners l WHERE l.surname = :surname ORDER BY l.id")
    List<Learner> findAllBySurname(@Param("surname") String surname);
    @Query(value = "SELECT * FROM Learners l WHERE l.age = :age ORDER BY l.id")
    List<Learner> findAllByAge(@Param("age") int age);
    @Query(value = "SELECT * FROM Learners l WHERE l.age > :age ORDER BY l.id")
    List<Learner> findAllAboveAge(@Param("age") int age);
    @Query(value = "SELECT * FROM Learners l WHERE l.age < :age ORDER BY l.id")
    List<Learner> findAllBelowAge(@Param("age") int age);
    @Query(value = "SELECT * FROM Learners l WHERE l.department > :department ORDER BY l.id")
    List<Learner> findAllByDepartment(@Param("department") String department);




}
