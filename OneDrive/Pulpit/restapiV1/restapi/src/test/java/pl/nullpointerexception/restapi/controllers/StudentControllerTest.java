package pl.nullpointerexception.restapi.controllers;


import pl.nullpointerexception.restapi.model.Student;
import pl.nullpointerexception.restapi.controlers.StudentController;
import pl.nullpointerexception.restapi.services.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class StudentControllerTest {

    @Mock
    private StudentService studentService;

    private StudentController studentController;

    private Student student1,student2;

    private List<Student> students;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        studentController = new StudentController(studentService);

       student1 = new Student(1L,"John","Martinez", 20, "IT");
        student2 = new Student(2L,"Jane","Jordan", 22, "Physics");
        students = Arrays.asList(student1, student2);
    }

    @Test
    public void testGetAllStudents() {

        when(studentService.getAllStudents()).thenReturn(students);

        // When
        ResponseEntity<List<Student>> response = studentController.getAllStudents();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(students, response.getBody());
        verify(studentService, times(1)).getAllStudents();
    }

    @Test
    public void testGetStudentById() {

        when(studentService.getStudentById(1L)).thenReturn(student1);

        // When
        ResponseEntity<Student> response = studentController.getStudentById(1L);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(student1, response.getBody());
        verify(studentService, times(1)).getStudentById(1L);
    }

    @Test
    public void testGetAllStudentsByName() {

        when(studentService.getAllStudentsByName("John")).thenReturn(students);

        // When
        ResponseEntity<List<Student>> response = studentController.getAllStudentsByName("John");

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(students, response.getBody());
        verify(studentService, times(1)).getAllStudentsByName("John");
    }

    @Test
    public void testGetAllStudentsBySurname() {

        when(studentService.getAllStudentsByName("John")).thenReturn(students);

        // When
        ResponseEntity<List<Student>> response = studentController.getAllStudentsByName("John");

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(students, response.getBody());
        verify(studentService, times(1)).getAllStudentsByName("John");
    }

    @Test
    public void testGetAllStudentsByAge() {

        when(studentService.getAllStudentsByAge(20)).thenReturn(students);

        // When
        ResponseEntity<List<Student>> response = studentController.getAllStudentsByAge(20);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(students, response.getBody());
        verify(studentService, times(1)).getAllStudentsByAge(20);
    }

    @Test
    public void testGetAllStudentsBelowAge() {

        when(studentService.getAllStudentsBelowAge(21)).thenReturn(students);

        // When
        ResponseEntity<List<Student>> response = studentController.getAllStudentsBelowAge(21);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(students, response.getBody());
        verify(studentService, times(1)).getAllStudentsBelowAge(21);
    }
    @Test
    public void testGetAllStudentsAboveAge() {


        when(studentService.getAllStudentsAboveAge(21)).thenReturn(students);

        // When
        ResponseEntity<List<Student>> response = studentController.getAllStudentsAboveAge(21);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(students, response.getBody());
        verify(studentService, times(1)).getAllStudentsAboveAge(21);
    }

    @Test
    public void testGetAllStudentsByMainSubject() {

        when(studentService.getAllStudentsByMainSubject("IT")).thenReturn(students);

        // When
        ResponseEntity<List<Student>> response = studentController.getAllStudentsByMainSubject("IT");

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(students, response.getBody());
        verify(studentService, times(1)).getAllStudentsByMainSubject("IT");
    }

    @Test
    public void testCreateStudent() {


        // Given
        Student createdStudent = new Student(3L,"Jane","Jordan", 22, "Physics");

        when(studentService.createStudent(createdStudent)).thenReturn(createdStudent);

        // When
        ResponseEntity<Student> response = studentController.createStudent(createdStudent);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(createdStudent, response.getBody());
        verify(studentService, times(1)).createStudent(createdStudent);
    }

    @Test
    public void testUpdateStudent() {


        // Given
        Student updatedStudent = new Student(4L,"Jane","Jordan", 22, "Physics");

        when(studentService.updateStudent(3L,updatedStudent)).thenReturn(updatedStudent);

        // When
        ResponseEntity<Student> response = studentController.updateStudent(3L,updatedStudent);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(updatedStudent, response.getBody());
        verify(studentService, times(1)).updateStudent(3L,updatedStudent);
    }

    @Test
    public void testDeleteStudent() {



        Student createdStudent = new Student(4L,"Jane","Jordan", 22, "Physics");
        // Given

        when(studentService.deleteStudent(4L)).thenReturn(true);

        // When
        ResponseEntity<Void> response = studentController.deleteStudent(4L);

        // Then
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(studentService, times(1)).deleteStudent(4L);
    }


}
