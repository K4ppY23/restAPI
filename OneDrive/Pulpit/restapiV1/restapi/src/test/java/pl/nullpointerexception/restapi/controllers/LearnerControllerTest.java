package pl.nullpointerexception.restapi.controllers;


import pl.nullpointerexception.restapi.model.Learner;
import pl.nullpointerexception.restapi.controlers.LearnerController;
import pl.nullpointerexception.restapi.model.Student;
import pl.nullpointerexception.restapi.services.LearnerService;
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

public class LearnerControllerTest {

    @Mock
    private LearnerService learnerService;

    private LearnerController learnerController;

    private Learner learner1,learner2;
    List<Learner> learners;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        learnerController = new LearnerController(learnerService);

        learner1 = new Learner(1L,"Phil","Maddy", 26, "IT");
        learner2 = new Learner(2L,"Damian","Brake", 41, "Maths");
        learners= Arrays.asList(learner1, learner2);
    }

    @Test
    public void testGetAllLearners() {

        when(learnerService.getAllLearners()).thenReturn(learners);

        // When
        ResponseEntity<List<Learner>> response = learnerController.getAllLearners();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(learners, response.getBody());
        verify(learnerService, times(1)).getAllLearners();
    }

    @Test
    public void testGetLearnerById() {


        when(learnerService.getLearnerById(1L)).thenReturn(learner1);

        // When
        ResponseEntity<Learner> response = learnerController.getLearnerById(1L);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(learner1, response.getBody());
        verify(learnerService, times(1)).getLearnerById(1L);
    }

    @Test
    public void testGetAllLearnersByName() {


        when(learnerService.getAllLearnersByName("Damian")).thenReturn(learners);

        // When
        ResponseEntity<List<Learner>> response = learnerController.getAllLearnersByName("Damian");

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(learners, response.getBody());
        verify(learnerService, times(1)).getAllLearnersByName("Damian");
    }

    @Test
    public void testGetAllLearnersBySurname() {

        when(learnerService.getAllLearnersBySurname("Brake")).thenReturn(learners);

        // When
        ResponseEntity<List<Learner>> response = learnerController.getAllLearnersBySurname("Brake");

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(learners, response.getBody());
        verify(learnerService, times(1)).getAllLearnersBySurname("Brake");
    }

    @Test
    public void testGetAllLearnersByAge() {

        when(learnerService.getAllLearnersByAge(26)).thenReturn(learners);

        // When
        ResponseEntity<List<Learner>> response = learnerController.getAllLearnersByAge(26);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(learners, response.getBody());
        verify(learnerService, times(1)).getAllLearnersByAge(26);
    }

    @Test
    public void testGetAllLearnersBelowAge() {

        when(learnerService.getAllLearnersBelowAge(30)).thenReturn(learners);

        // When
        ResponseEntity<List<Learner>> response = learnerController.getAllLearnersBelowAge(30);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(learners, response.getBody());
        verify(learnerService, times(1)).getAllLearnersBelowAge(30);
    }
    @Test
    public void testGetAllLearnersAboveAge() {

        when(learnerService.getAllLearnersAboveAge(20)).thenReturn(learners);

        // When
        ResponseEntity<List<Learner>> response = learnerController.getAllLearnersAboveAge(20);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(learners, response.getBody());
        verify(learnerService, times(1)).getAllLearnersAboveAge(20);
    }

    @Test
    public void testGetAllLearnersByDepartment() {

        when(learnerService.getAllLearnersByDepartment("Maths")).thenReturn(learners);

        // When
        ResponseEntity<List<Learner>> response = learnerController.getAllLearnersByDepartment("Maths");

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(learners, response.getBody());
        verify(learnerService, times(1)).getAllLearnersByDepartment("Maths");
    }

    @Test
    public void testCreateLearner() {
        // Given
        Learner createdLearner = new Learner(3L,"Phil","Maddy", 26, "IT");

        when(learnerService.createLearner(createdLearner)).thenReturn(createdLearner);

        // When
        ResponseEntity<Learner> response = learnerController.createLearner(createdLearner);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(createdLearner, response.getBody());
        verify(learnerService, times(1)).createLearner(createdLearner);
    }

    @Test
    public void testUpdateLearner() {

        Learner updatedLearner = new Learner(4L,"Damian","Brake", 41, "Maths");

        when(learnerService.updateLearner(3L,updatedLearner)).thenReturn(updatedLearner);

        // When
        ResponseEntity<Learner> response = learnerController.updateLearner(1L,updatedLearner);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(updatedLearner, response.getBody());
        verify(learnerService, times(1)).updateLearner(1L,updatedLearner);
    }

    @Test
    public void testDeleteLearner() {

        when(learnerService.deleteLearner(3L)).thenReturn(true);

        // When
        ResponseEntity<Void> response = learnerController.deleteLearner(3L);

        // Then
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(learnerService, times(1)).deleteLearner(3L);
    }
}
