package pl.nullpointerexception.restapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.nullpointerexception.restapi.model.Student;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query(value = "SELECT * FROM Students s WHERE s.name = :name ORDER BY s.id")
    List<Student> findAllByName(@Param("name") String name);
    @Query(value = "SELECT * FROM Students s WHERE s.surname = :surname ORDER BY s.id")
    List<Student> findAllBySurname(@Param("surname") String surname);
    @Query(value = "SELECT * FROM Students s WHERE s.age = :age ORDER BY s.id")
    List<Student> findAllByAge(@Param("age") int age);
    @Query(value = "SELECT * FROM Students s WHERE s.age > :age ORDER BY s.id")
    List<Student> findAllAboveAge(@Param("age") int age);
    @Query(value = "SELECT * FROM Students s WHERE s.age < :age ORDER BY s.id")
    List<Student> findAllBelowAge(@Param("age") int age);
    @Query(value = "SELECT * FROM Students s WHERE s.mainSubject = :mainSubject ORDER BY s.id")
    List<Student> findAllByMainSubject(@Param("mainSubject") String mainSubject);



}
