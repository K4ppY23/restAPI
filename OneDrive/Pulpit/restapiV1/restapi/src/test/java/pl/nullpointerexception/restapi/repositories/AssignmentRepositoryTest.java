package pl.nullpointerexception.restapi.repositories;


import com.fasterxml.jackson.annotation.JsonTypeInfo;
import pl.nullpointerexception.restapi.model.Learner;
import pl.nullpointerexception.restapi.model.Subject;
import pl.nullpointerexception.restapi.model.Student;
import pl.nullpointerexception.restapi.model.Assignment;

import pl.nullpointerexception.restapi.repositories.AssignmentRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

@DataJpaTest
public class AssignmentRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AssignmentRepository assignmentRepository;

    @BeforeEach
    public void setup() {
        // Dodajemy przykładowe przypisania do bazy danych przed każdym testem
        Student student1 = new Student(1L,"John","Martinez", 20, "IT");
        Learner learner1 = new Learner(1L,"Phil","Maddy", 26, "IT");
        Subject subject1 = new Subject(1L,"Software Engineering");

        Student student2 = new Student(2L,"Jane","Jordan", 22, "Physics");
        Learner learner2 = new Learner(2L,"Phil","Maddy", 26, "Maths");
        Subject subject2 = new Subject(2L,"Linear Algebra");

        Assignment assignment1 = new Assignment(student1.getId(), learner1.getId(), subject1.getId());
        Assignment assignment2 = new Assignment(student2.getId(), learner2.getId(), subject2.getId());

        entityManager.persist(student1);
        entityManager.persist(learner1);
        entityManager.persist(subject1);

        entityManager.persist(student2);
        entityManager.persist(learner2);
        entityManager.persist(subject2);


        entityManager.persist(assignment1);
        entityManager.persist(assignment2);

        entityManager.flush();
    }

    @Test
    public void testFindAll() {
        List<Assignment> result = assignmentRepository.findAll();

        Assertions.assertNotNull(result);

        Assertions.assertEquals(2, result.size());

        Assertions.assertEquals(1L, result.get(0).getId());
        Assertions.assertEquals(1L, result.get(0).getStudentID());
        Assertions.assertEquals(1L, result.get(0).getLearnerID());
        Assertions.assertEquals(1L, result.get(0).getSubjectID());

        Assertions.assertEquals(2L, result.get(1).getId());
        Assertions.assertEquals(2L, result.get(1).getStudentID());
        Assertions.assertEquals(2L, result.get(1).getLearnerID());
        Assertions.assertEquals(2L, result.get(1).getSubjectID());

    }


    @Test
    public void testFindById() {
        Long assignmentID = 2L;

        // When
        Assignment result = assignmentRepository.findById(assignmentID).orElse(null);

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(2L,result.getId());
        Assertions.assertEquals(2L,result.getStudentID());
        Assertions.assertEquals(2L,result.getLearnerID());
        Assertions.assertEquals(2L,result.getSubjectID());
    }

    @Test
    public void testFindAllByStudentID() {

        // When
        List<Assignment> result = assignmentRepository.findByStudentID(2L);

        Assertions.assertEquals(1, result.size());

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(2L,result.get(0).getId());
        Assertions.assertEquals(2L,result.get(0).getStudentID());
        Assertions.assertEquals(2L,result.get(0).getLearnerID());
        Assertions.assertEquals(2L,result.get(0).getSubjectID());
    }

    @Test
    public void testFindAllByLearnerID() {

        // When

        List<Assignment> result = assignmentRepository.findByLearnerID(2L);

        Assertions.assertEquals(1, result.size());

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1L,result.get(0).getId());
        Assertions.assertEquals(1L,result.get(0).getStudentID());
        Assertions.assertEquals(1L,result.get(0).getLearnerID());
        Assertions.assertEquals(1L,result.get(0).getSubjectID());
    }

    @Test
    public void testFindAllBySubjectID() {

        // When
        List<Assignment> result = assignmentRepository.findBySubjectID(1L);

        Assertions.assertEquals(1, result.size());

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1L,result.get(0).getId());
        Assertions.assertEquals(1L,result.get(0).getStudentID());
        Assertions.assertEquals(1L,result.get(0).getLearnerID());
        Assertions.assertEquals(1L,result.get(0).getSubjectID());
    }





    @Test
    public void testFindAllByStudentIdAndSubjectId() {
        // Given
        Long studentID = 1L;
        Long subjectID = 1L;

        // When
        List<Assignment> result = assignmentRepository.findByStudentIDAndSubjectID(studentID,subjectID);

        Assertions.assertEquals(1, result.size());

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(studentID, result.get(0).getStudentID());
        Assertions.assertEquals(subjectID, result.get(0).getSubjectID());
    }

    @Test
    public void testFindAllByStudentIDAndLearnerID() {
        // Given
        Long studentID = 1L;
        Long learnerID = 1L;

        // When
        List<Assignment> result = assignmentRepository.findByStudentIDAndLearnerID(studentID,learnerID);

        Assertions.assertEquals(1, result.size());

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(studentID, result.get(0).getStudentID());
        Assertions.assertEquals(learnerID, result.get(0).getLearnerID());

    }

    @Test
    public void testFindAllByLearnerIDAndSubjectID() {
        // Given
        Long learnerID = 2L;
        Long subjectID = 2L;

        // When
        List<Assignment> result = assignmentRepository.findByLearnerIDAndSubjectID(learnerID,subjectID);

        Assertions.assertEquals(1, result.size());

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(learnerID, result.get(0).getLearnerID());
        Assertions.assertEquals(subjectID, result.get(0).getSubjectID());
    }

    @Test
    public void testFindAllByStudentIDAndLearnerIDAndSubjectID() {
        // Given
        Long learnerID = 2L;
        Long subjectID = 2L;
        Long studentID = 2L;

        // When
        List<Assignment> result = assignmentRepository.findByStudentIDAndLearnerIDAndSubjectID(studentID,learnerID,subjectID);

        Assertions.assertEquals(1, result.size());

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(studentID,result.get(0).getStudentID());
        Assertions.assertEquals(learnerID, result.get(0).getLearnerID());
        Assertions.assertEquals(subjectID, result.get(0).getSubjectID());
    }

    @Test
    public void testSave() {
        // Given
        Student student = new Student("Mark","Rendy", 22, "Maths");
        Learner learner = new Learner("William","Jackson", 47,"Physics");
        Subject subject = new Subject("Physics");
        Assignment assignment = new Assignment(student.getId(), learner.getId(), subject.getId());

        entityManager.persist(student);
        entityManager.persist(learner);
        entityManager.persist(subject);
        entityManager.persist(assignment);
        entityManager.flush();

        // When
        Assignment result = assignmentRepository.save(assignment);

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getId());
        Assertions.assertEquals(student.getId(), result.getStudentID());
        Assertions.assertEquals(learner.getId(), result.getLearnerID());
        Assertions.assertEquals(subject.getId(), result.getSubjectID());
    }

    @Test
    public void testDelete() {
        // Given
        Assignment assignment = assignmentRepository.findById(3L).orElse(null);

        // When
        assignmentRepository.delete(assignment);

        // Then
        Assignment result = assignmentRepository.findById(3L).orElse(null);
        Assertions.assertNull(result);
    }
}
