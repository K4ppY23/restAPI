package pl.nullpointerexception.restapi.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import pl.nullpointerexception.restapi.model.Student;
import pl.nullpointerexception.restapi.repositories.StudentRepository;

import java.util.List;

@DataJpaTest
public class StudentServiceTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private StudentRepository studentRepository;

    private StudentService studentService;

    @BeforeEach
    public void setup() {
        studentService = new StudentService(studentRepository);

        // Given
        Student student1 = new Student("John","Martinez", 20, "IT");
        Student student2 = new Student("Jane","Jordan", 22, "Physics");
        entityManager.persist(student1);
        entityManager.persist(student2);
        entityManager.flush();
    }

    @Test
    public void testGetAllStudents() {


        // When
        List<Student> result = studentService.getAllStudents();

        // Then
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
    public void testGetStudentById() {

        Long studentID = 1L;

        // When
        Student result = studentService.getStudentById(studentID);

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
        List<Student> result = studentService.getAllStudentsByName("John");


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
        List<Student> result = studentService.getAllStudentsByAge(20);

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
        List<Student> result = studentService.getAllStudentsBelowAge(21);

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
        List<Student> result = studentService.getAllStudentsAboveAge(21);

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
        List<Student> result = studentService.getAllStudentsByMainSubject("IT");

        Assertions.assertEquals(1,result.size());

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals("John", result.get(0).getName());
        Assertions.assertEquals("Martinez", result.get(0).getSurname());
        Assertions.assertEquals(20, result.get(0).getAge());
        Assertions.assertEquals("IT", result.get(0).getMainSubject());

    }

    @Test
    public void testCreateStudent() {
        // Given
        Student student = new Student("John","Martinez", 20, "IT");

        // When
        Student result = studentService.createStudent(student);

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getId());
        Assertions.assertEquals("John", result.getName());
        Assertions.assertEquals("Martinez", result.getSurname());
        Assertions.assertEquals(20, result.getAge());
        Assertions.assertEquals("IT", result.getMainSubject());
    }


    @Test
    public void testUpdateStudent() {
        // Given
        Student student = new Student("John","Martinez", 20, "IT");
        entityManager.persist(student);
        entityManager.flush();

        // When
        student.setName("Jane");
        Student result = studentService.updateStudent(student.getId(), student);

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals("Jane", result.getName());
    }

    @Test
    public void testDeleteStudent() {
        // Given
        Student student = new Student("John","Martinez", 20, "IT");
        entityManager.persist(student);
        entityManager.flush();

        // When
        boolean result = studentService.deleteStudent(student.getId());

        // Then
        Assertions.assertTrue(result);
        Assertions.assertNull(studentService.getStudentById(student.getId()));
    }
}
