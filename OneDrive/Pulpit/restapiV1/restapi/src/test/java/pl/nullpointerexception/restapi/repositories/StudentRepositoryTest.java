package pl.nullpointerexception.restapi.repositories;

import pl.nullpointerexception.restapi.repositories.StudentRepository;
import pl.nullpointerexception.restapi.model.Student;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class StudentRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private StudentRepository studentRepository;

    @BeforeEach
    public void setup() {
        Student student1 = new Student("John","Martinez", 20, "IT");
        Student student2 = new Student("Jane","Jordan", 22, "Physics");

        entityManager.persist(student1);
        entityManager.persist(student2);
        entityManager.flush();

    }

    @Test
    public void testFindAllStudents() {

        List<Student> result = studentRepository.findAll();

        Assertions.assertNotNull(result);

        Assertions.assertEquals(2, result.size());

        Assertions.assertEquals("John", result.get(0).getName());
        Assertions.assertEquals("Martinez", result.get(0).getSurname());
        Assertions.assertEquals(20, result.get(0).getAge());
        Assertions.assertEquals("IT", result.get(0).getMainSubject());

        Assertions.assertEquals("Jane", result.get(1).getName());
        Assertions.assertEquals("Jordan", result.get(1).getSurname());
        Assertions.assertEquals(22, result.get(1).getAge());
        Assertions.assertEquals("Physics", result.get(1).getMainSubject());

    }

    @Test
    public void testFindById() {

        Long studentID = 1L;

        // When
        Student result = studentRepository.findById(studentID).orElse(null);

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals("John", result.getName());
        Assertions.assertEquals("Martinez", result.getSurname());
        Assertions.assertEquals(20, result.getAge());
        Assertions.assertEquals("IT", result.getMainSubject());
    }

    @Test
    public void testFindAllByName() {

        // When
        List<Student> result = studentRepository.findAllByName("John");


        Assertions.assertEquals(1,result.size());
        // Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals("John", result.get(0).getName());
        Assertions.assertEquals("Martinez", result.get(0).getSurname());
        Assertions.assertEquals(20, result.get(0).getAge());
        Assertions.assertEquals("IT", result.get(0).getMainSubject());
    }

    @Test
    public void testFindAllByAge() {

        // When
        List<Student> result = studentRepository.findAllByAge(20);

        Assertions.assertEquals(1,result.size());

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals("John", result.get(0).getName());
        Assertions.assertEquals("Martinez", result.get(0).getSurname());
        Assertions.assertEquals(20, result.get(0).getAge());
        Assertions.assertEquals("IT", result.get(0).getMainSubject());
    }

    @Test
    public void testFindAllBelowAge() {

        // When
        List<Student> result = studentRepository.findAllBelowAge(21);

        Assertions.assertEquals(1,result.size());

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals("John", result.get(0).getName());
        Assertions.assertEquals("Martinez", result.get(0).getSurname());
        Assertions.assertEquals(20, result.get(0).getAge());
        Assertions.assertEquals("IT", result.get(0).getMainSubject());
    }

    @Test
    public void testFindAllAboveAge() {

        // When
        List<Student> result = studentRepository.findAllAboveAge(21);

        Assertions.assertEquals(1,result.size());

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals("Jane", result.get(0).getName());
        Assertions.assertEquals("Jordan", result.get(0).getSurname());
        Assertions.assertEquals(22, result.get(0).getAge());
        Assertions.assertEquals("Physics", result.get(0).getMainSubject());
    }

    @Test
    public void testFindAllByMainSubject() {

        // When
        List<Student> result = studentRepository.findAllByMainSubject("IT");

        Assertions.assertEquals(1,result.size());

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals("John", result.get(0).getName());
        Assertions.assertEquals("Martinez", result.get(0).getSurname());
        Assertions.assertEquals(20, result.get(0).getAge());
        Assertions.assertEquals("IT", result.get(0).getMainSubject());

    }

    @Test
    public void testSave() {
        // Given
        Student student = new Student("Nathan","McLarry", 31, "Science");

        entityManager.persist(student);
        entityManager.flush();

        // When
        Student result = studentRepository.save(student);

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getId());
        Assertions.assertEquals("Nathan", result.getName());
        Assertions.assertEquals("McLarry", result.getSurname());
        Assertions.assertEquals(31, result.getAge());
        Assertions.assertEquals("Science", result.getMainSubject());
    }


    @Test
    public void testDelete() {
        // Given
        Student student = studentRepository.findAllByName("Nathan").get(0);

        // When
        studentRepository.delete(student);

        // Then
        Student result = studentRepository.findById(student.getId()).orElse(null);
        Assertions.assertNull(result);
    }
}

