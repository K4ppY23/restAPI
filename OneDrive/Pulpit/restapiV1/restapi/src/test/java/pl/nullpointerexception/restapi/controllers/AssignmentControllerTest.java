package pl.nullpointerexception.restapi.controllers;


import pl.nullpointerexception.restapi.model.Assignment;
import pl.nullpointerexception.restapi.controlers.AssignmentController;
import pl.nullpointerexception.restapi.model.Subject;
import pl.nullpointerexception.restapi.services.AssignmentService;
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

public class AssignmentControllerTest {

    @Mock
    private AssignmentService assignmentService;

    private AssignmentController assignmentController;

    private Assignment assignment,assignment1,assignment2;

    private List<Assignment> assignments;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        assignmentController = new AssignmentController(assignmentService);

        assignment1 = new Assignment(1L,1L,2L,3L);
        assignment2 = new Assignment(2L,3L,1L,2L);
       assignments = Arrays.asList(assignment1, assignment2);
    }

    @Test
    public void testGetAllAssignments() {

        when(assignmentService.getAllAssignments()).thenReturn(assignments);

        // When
        ResponseEntity<List<Assignment>> response = assignmentController.getAllAssignments();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(assignments, response.getBody());
        verify(assignmentService, times(1)).getAllAssignments();
    }

    @Test
    public void testGetAssignmentById() {


        when(assignmentService.getAssignmentById(1L)).thenReturn(assignment);

        // When
        ResponseEntity<Assignment> response = assignmentController.getAssignmentById(1L);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(assignment, response.getBody());
        verify(assignmentService, times(1)).getAssignmentById(1L);
    }

    @Test
    public void testGetAllAssignmentsByStudentID() {


        when(assignmentService.getAllAssignmentsByStudentID(1L)).thenReturn(assignments);

        // When
        ResponseEntity<List<Assignment>> response = assignmentController.getAllAssignmentsByStudentID(1L);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(assignments, response.getBody());
        verify(assignmentService, times(1)).getAllAssignmentsByStudentID(1L);
    }

    @Test
    public void testGetAllAssignmentsByLearnerID() {

     when(assignmentService.getAllAssignmentsByLearnerID(1L)).thenReturn(assignments);

        // When
        ResponseEntity<List<Assignment>> response = assignmentController.getAllAssignmentsByLearnerID(1L);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(assignments, response.getBody());
        verify(assignmentService, times(1)).getAllAssignmentsByLearnerID(1L);
    }

    @Test
    public void testGetAllAssignmentsBySubjectID() {

        when(assignmentService.getAllAssignmentsBySubjectID(3L)).thenReturn(assignments);

        // When
        ResponseEntity<List<Assignment>> response = assignmentController.getAllAssignmentsBySubjectID(3L);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(assignments, response.getBody());
        verify(assignmentService, times(1)).getAllAssignmentsBySubjectID(3L);
    }

    @Test
    public void testGetAllAssignmentsByStudentIDAndLearnerID() {

        when(assignmentService.getAllAssignmentsByStudentIDAndLearnerID(1L,2L)).thenReturn(assignments);

        // When
        ResponseEntity<List<Assignment>> response = assignmentController.getAllAssignmentsByStudentIDAndLearnerID(1L,2L);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(assignments, response.getBody());
        verify(assignmentService, times(1)).getAllAssignmentsByStudentIDAndLearnerID(1L,2L);
    }

    @Test
    public void testGetAllAssignmentsByStudentIDAndSubjectID() {

        when(assignmentService.getAllAssignmentsByStudentIDAndSubjectID( 1L,3L)).thenReturn(assignments);

        // When
        ResponseEntity<List<Assignment>> response = assignmentController.getAllAssignmentsByStudentIDAndSubjectID( 1L,3L);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(assignments, response.getBody());
        verify(assignmentService, times(1)).getAllAssignmentsByStudentIDAndSubjectID( 1L,3L);
    }

    @Test
    public void testGetAllAssignmentsByLearnerIDAndSubjectID() {

        when(assignmentService.getAllAssignmentsByLearnerIDAndSubjectID( 2L,3L)).thenReturn(assignments);

        // When
        ResponseEntity<List<Assignment>> response = assignmentController.getAllAssignmentsByLearnerIDAndSubjectID( 2L,3L);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(assignments, response.getBody());
        verify(assignmentService, times(1)).getAllAssignmentsByLearnerIDAndSubjectID( 2L,3L);
    }

    @Test
    public void testGetAllAssignmentsByStudentIDAndLearnerIDAndSubjectID() {


        when(assignmentService.getAllAssignmentsByStudentIDAndLearnerIDAndSubjectID( 1L,2L,3L)).thenReturn(assignments);

        // When
        ResponseEntity<List<Assignment>> response = assignmentController.getAllAssignmentsByStudentIDAndLearnerIDAndSubjectID( 1L,2L,3L);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(assignments, response.getBody());
        verify(assignmentService, times(1)).getAllAssignmentsByStudentIDAndLearnerIDAndSubjectID( 1L,2L,3L);
    }

    @Test
    public void testCreateAssignment() {
        // Given

        Assignment createdAssignment = new Assignment(3L,1L,3L,3L);

        when(assignmentService.createAssignment(createdAssignment)).thenReturn(createdAssignment);

        // When
        ResponseEntity<Assignment> response = assignmentController.createAssignment(createdAssignment);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(createdAssignment, response.getBody());
        verify(assignmentService, times(1)).createAssignment(createdAssignment);
    }

    @Test
    public void testUpdateAssignment() {

        Assignment updatedAssignment= new Assignment(4L,2L,1L,3L);

        when(assignmentService.updateAssignment(3L,updatedAssignment)).thenReturn(updatedAssignment);

        // When
        ResponseEntity<Assignment> response = assignmentController.updateAssignment(1L,updatedAssignment);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(updatedAssignment, response.getBody());
        verify(assignmentService, times(1)).updateAssignment(1L,updatedAssignment);
    }

    @Test
    public void testDeleteAssignment() {



        when(assignmentService.deleteAssignment(3L)).thenReturn(true);

        // When
        ResponseEntity<Void> response = assignmentController.deleteAssignment(3L);

        // Then
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(assignmentService, times(1)).deleteAssignment(3L);
    }


}

