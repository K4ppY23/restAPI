package pl.nullpointerexception.restapi.services;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import pl.nullpointerexception.restapi.model.Learner;
import pl.nullpointerexception.restapi.repositories.LearnerRepository;
import pl.nullpointerexception.restapi.services.LearnerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class LearnerServiceTest {

    @Mock
    private LearnerRepository learnerRepository;

    private LearnerService learnerService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        learnerService = new LearnerService(learnerRepository);
    }

    @Test
    public void testGetAllLearners() {
        // Given
        List<Learner> learners = new ArrayList<>();
        learners.add(new Learner("Phil","Maddy", 26, "IT"));
        learners.add(new Learner("Damian","Brake", 41, "Maths"));
        when(learnerRepository.findAll()).thenReturn(learners);

        // When
        List<Learner> result = learnerService.getAllLearners();

        // Then
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("Phil", result.get(0).getName());
        Assertions.assertEquals("Damian", result.get(1).getName());
        verify(learnerRepository, times(1)).findAll();
    }

    @Test
    public void testGetLearnerById() {
        // Given
        Long learnerId = 1L;
        Learner learner = new Learner("Phil","Maddy", 26, "IT");
        when(learnerRepository.findById(learnerId)).thenReturn(Optional.of(learner));

        // When
        Learner result = learnerService.getLearnerById(learnerId);

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(learnerId, result.getId());
        Assertions.assertEquals("Phil", result.getName());
        verify(learnerRepository, times(1)).findById(learnerId);
    }
    @Test
    public void testFindAllByAge() {

        // When
        List<Learner> result = learnerService.getAllLearnersByAge(26);


        Assertions.assertEquals(1,result.size());
        // Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals("Phil", result.get(0).getName());
        Assertions.assertEquals(26, result.get(0).getAge());
        Assertions.assertEquals("IT", result.get(0).getDepartment());
    }

    @Test
    public void testFindAllBelowAge() {

        // When
        List<Learner> result = learnerService.getAllLearnersBelowAge(30);


        Assertions.assertEquals(1,result.size());
        // Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals("Phil", result.get(0).getName());
        Assertions.assertEquals(26, result.get(0).getAge());
        Assertions.assertEquals("IT", result.get(0).getDepartment());
    }

    @Test
    public void testFindAllAboveAge() {

        // When
        List<Learner> result = learnerService.getAllLearnersAboveAge(30);


        Assertions.assertEquals(1,result.size());
        // Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals("Damian", result.get(0).getName());
        Assertions.assertEquals(41, result.get(0).getAge());
        Assertions.assertEquals("Maths", result.get(0).getDepartment());
    }

    @Test
    public void testFindAllByDepartment() {

        // When
        List<Learner> result = learnerService.getAllLearnersByDepartment("IT");


        Assertions.assertEquals(1,result.size());
        // Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals("Phil", result.get(0).getName());
        Assertions.assertEquals(26, result.get(0).getAge());
        Assertions.assertEquals("IT", result.get(0).getDepartment());

    }

    @Test
    public void testCreateLearner() {
        // Given
        Learner learner = new Learner("Phil","Maddy", 26, "IT");
        when(learnerRepository.save(learner)).thenReturn(learner);

        // When
        Learner result = learnerService.createLearner(learner);

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getId());
        Assertions.assertEquals("Phil", result.getName());
        verify(learnerRepository, times(1)).save(learner);
    }

    @Test
    public void testUpdateLearner() {
        // Given
        Long learnerId = 1L;
        Learner existingLearner = new Learner("Phil","Maddy", 26, "IT");
        Learner updatedLearner = new Learner("Damian","Brake", 41, "Maths");
        when(learnerRepository.findById(learnerId)).thenReturn(Optional.of(existingLearner));
        when(learnerRepository.save(updatedLearner)).thenReturn(updatedLearner);

        // When
        Learner result = learnerService.updateLearner(learnerId, updatedLearner);

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(learnerId, result.getId());
        Assertions.assertEquals("Damian", result.getName());
        Assertions.assertEquals("Brake", result.getSurname());
        Assertions.assertEquals(41, result.getAge());
        Assertions.assertEquals("Maths", result.getDepartment());

        verify(learnerRepository, times(1)).findById(learnerId);
        verify(learnerRepository, times(1)).save(updatedLearner);
    }

    @Test
    public void testDeleteLearner() {
        // Given
        Long learnerId = 1L;
        when(learnerRepository.existsById(learnerId)).thenReturn(true);

        // When
        boolean result = learnerService.deleteLearner(learnerId);

        // Then
        Assertions.assertTrue(result);
        verify(learnerRepository, times(1)).existsById(learnerId);
        verify(learnerRepository, times(1)).deleteById(learnerId);
    }
}

