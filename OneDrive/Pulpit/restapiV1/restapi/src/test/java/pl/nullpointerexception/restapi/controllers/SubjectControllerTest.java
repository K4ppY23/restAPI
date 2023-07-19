package pl.nullpointerexception.restapi.controllers;


import pl.nullpointerexception.restapi.model.Student;
import pl.nullpointerexception.restapi.model.Subject;
import pl.nullpointerexception.restapi.controlers.SubjectController;
import pl.nullpointerexception.restapi.services.SubjectService;
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

public class SubjectControllerTest {

    @Mock
    private SubjectService subjectService;

    private SubjectController subjectController;

    private Subject subject1,subject2;

    private List<Subject> subjects;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        subjectController = new SubjectController(subjectService);

        subject1 = new Subject(1L,"Maths");
        subject2 = new Subject(2L,"Science");
        subjects= Arrays.asList(subject1, subject2);
    }

    @Test
    public void testGetAllSubjects() {


        when(subjectService.getAllSubjects()).thenReturn(subjects);

        // When
        ResponseEntity<List<Subject>> response = subjectController.getAllSubjects();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(subjects, response.getBody());
        verify(subjectService, times(1)).getAllSubjects();
    }

    @Test
    public void testGetSubjectById() {

        when(subjectService.getSubjectById(1L)).thenReturn(subject1);

        // When
        ResponseEntity<Subject> response = subjectController.getSubjectById(1L);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(subject1, response.getBody());
        verify(subjectService, times(1)).getSubjectById(1L);
    }

    @Test
    public void testGetAllSubjectsByName() {


        when(subjectService.getAllSubjectsByName("Science")).thenReturn(subjects);

        // When
        ResponseEntity<List<Subject>> response = subjectController.getAllSubjectsByName("Science");

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(subjects, response.getBody());
        verify(subjectService, times(1)).getAllSubjectsByName("Science");
    }

    @Test
    public void testCreateSubject() {


        Subject createdSubject = new Subject(3L ,"Maths");

        when(subjectService.createSubject(createdSubject)).thenReturn(createdSubject);

        // When
        ResponseEntity<Subject> response = subjectController.createSubject(createdSubject);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(createdSubject, response.getBody());
        verify(subjectService, times(1)).createSubject(createdSubject);
    }

    @Test
    public void testUpdateSubject() {


        // Given
        Subject updatedSubject = new Subject(4L,"IT");

        when(subjectService.updateSubject(3L,updatedSubject)).thenReturn(updatedSubject);

        // When
        ResponseEntity<Subject> response = subjectController.updateSubject(3L,updatedSubject);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(updatedSubject, response.getBody());
        verify(subjectService, times(1)).updateSubject(3L,updatedSubject);
    }

    @Test
    public void testDeleteSubject() {


        when(subjectService.deleteSubject(3L)).thenReturn(true);

        // When
        ResponseEntity<Void> response = subjectController.deleteSubject(3L);

        // Then
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(subjectService, times(1)).deleteSubject(3L);
    }


}
