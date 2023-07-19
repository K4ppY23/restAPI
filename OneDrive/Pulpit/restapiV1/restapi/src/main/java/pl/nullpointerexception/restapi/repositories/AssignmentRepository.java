package pl.nullpointerexception.restapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.nullpointerexception.restapi.model.Assignment;
import pl.nullpointerexception.restapi.model.Learner;
import pl.nullpointerexception.restapi.model.Student;
import pl.nullpointerexception.restapi.model.Subject;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

    @Query(value = "SELECT * FROM Assignments a WHERE a.studentID = :studentID ORDER BY a.id")
    List<Assignment> findByStudentID(@Param("studentID")Long studentID);
    @Query(value = "SELECT * FROM Assignments a WHERE a.learnerID = :learnerID ORDER BY a.id")
    List<Assignment> findByLearnerID(@Param("learnerID") Long learnerID);

    @Query(value = "SELECT * FROM Assignments a WHERE a.subjectID = :subjectID ORDER BY a.id")
    List<Assignment> findBySubjectID(@Param("subjectID")Long subjectID);

    @Query(value = "SELECT * FROM Assignments a WHERE a.studentID = :studentID AND a.subjectID = :subjectID ORDER BY a.id")
    List<Assignment> findByStudentIDAndSubjectID(@Param("studentID") Long studentID,
                                                 @Param("subjectID") Long subjectID);
    @Query(value = "SELECT * FROM Assignments a WHERE a.studentID = :studentID AND a.learnerID = :learnerID ORDER BY a.id")
    List<Assignment> findByStudentIDAndLearnerID(@Param("studentID")Long studentID,
                                                 @Param("learnerID")Long learnerID);
    @Query(value = "SELECT * FROM Assignments a WHERE a.learnerID = :learnerID AND a.subjectID = :subjectID ORDER BY a.id")
    List<Assignment> findByLearnerIDAndSubjectID(@Param("learnerID") Long learnerID,
                                                 @Param("subjectID") Long subjectID);
    @Query(value = "SELECT * FROM Assignments a WHERE a.studentID = :studentID AND a.learnerID = :learnerID AND a.subjectID = :subjectID ORDER BY a.id")
    List<Assignment> findByStudentIDAndLearnerIDAndSubjectID(@Param("studentID") Long studentID,
                                                             @Param("learnerID") Long learnerID,
                                                             @Param("subjectID") Long subjectID);




}

