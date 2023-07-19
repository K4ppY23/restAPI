package pl.nullpointerexception.restapi.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.nullpointerexception.restapi.model.Assignment;
import pl.nullpointerexception.restapi.model.Subject;
import pl.nullpointerexception.restapi.repositories.AssignmentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;


public class AssigmentServiceTest {
    @Mock
    private AssignmentRepository assignmentRepository;

    private AssignmentService assignmentService;

    private List<Assignment> assignments;

    private Assignment assignment1,assignment2;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        assignmentService = new AssignmentService(assignmentRepository);

       assignments = new ArrayList<>();

       assignment1 = new Assignment(1L,1L,2L,3L);
        assignment2 = new Assignment(2L,3L,1L,2L);
        assignments.add(assignment1);
        assignments.add(assignment2);
    }

    @Test
    public void testGetAllAssignments() {
        // Given

        when(assignmentRepository.findAll()).thenReturn(assignments);

        // When
        List<Assignment> result = assignmentService.getAllAssignments();

        // Then
        Assertions.assertEquals(2, result.size());

        Assertions.assertEquals(1L, result.get(0).getStudentID());
        Assertions.assertEquals(2L, result.get(0).getLearnerID());
        Assertions.assertEquals(3L, result.get(0).getSubjectID());

        Assertions.assertEquals(3L, result.get(1).getStudentID());
        Assertions.assertEquals(1L, result.get(1).getLearnerID());
        Assertions.assertEquals(2L, result.get(1).getSubjectID());



        verify(assignmentRepository, times(1)).findAll();

    }
    @Test
    public void testGetAssignmentById() {

        when(assignmentRepository.findById(1L)).thenReturn(Optional.of(assignment1));

        // When
        Assignment result = assignmentService.getAssignmentById(1L);

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1L, result.getId());
        Assertions.assertEquals(1L, result.getStudentID());
        Assertions.assertEquals(2L, result.getLearnerID());
        Assertions.assertEquals(3L, result.getSubjectID());

        verify(assignmentRepository, times(1)).findById(1L);
    }

    @Test
    public void testFindAllByStudentID() {
        Long studentID = 2L;

        // When
        List<Assignment> result = assignmentService.getAllAssignmentsByStudentID(studentID);

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
        Long learnerID = 1L;

        // When

        List<Assignment> result = assignmentService.getAllAssignmentsByLearnerID(learnerID);

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
        Long subjectID = 1L;

        // When
        List<Assignment> result = assignmentService.getAllAssignmentsBySubjectID(subjectID);

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
        List<Assignment> result = assignmentService.getAllAssignmentsByStudentIDAndSubjectID(studentID,subjectID);

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
        List<Assignment> result = assignmentService.getAllAssignmentsByStudentIDAndLearnerID(studentID,learnerID);

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
        List<Assignment> result = assignmentService.getAllAssignmentsByLearnerIDAndSubjectID(learnerID,subjectID);

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
        List<Assignment> result = assignmentService.getAllAssignmentsByStudentIDAndLearnerIDAndSubjectID(studentID,learnerID,subjectID);

        Assertions.assertEquals(1, result.size());

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(studentID,result.get(0).getStudentID());
        Assertions.assertEquals(learnerID, result.get(0).getLearnerID());
        Assertions.assertEquals(subjectID, result.get(0).getSubjectID());
    }

    @Test
    public void testCreateAssignment() {
        // Given
        Assignment assignment = new Assignment(3L,1L,2L,3L);
        when(assignmentRepository.save(assignment)).thenReturn(assignment);

        // When
        Assignment result = assignmentService.createAssignment(assignment);

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getId());
        Assertions.assertEquals(1L, result.getStudentID());
        Assertions.assertEquals(2L, result.getLearnerID());
        Assertions.assertEquals(3L, result.getSubjectID());
        verify(assignmentRepository, times(1)).save(assignment);
    }
    @Test
    public void testUpdateAssignment() {

        Assignment updatedAssignment  = new Assignment (4L,3L,1L,2L);

        when(assignmentRepository.save(updatedAssignment)).thenReturn(updatedAssignment);

        // When
        Assignment result = assignmentService.updateAssignment(3L, updatedAssignment);

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(3L, result.getId());
        Assertions.assertEquals(3L, result.getStudentID());
        Assertions.assertEquals(1L, result.getLearnerID());
        Assertions.assertEquals(2L, result.getSubjectID());

        verify(assignmentRepository, times(1)).findById(3L);
        verify(assignmentRepository, times(1)).save(updatedAssignment);
    }
    @Test
    public void testDeleteAssignment() {
        // Given
        when(assignmentRepository.existsById(3L)).thenReturn(true);

        // When
        boolean result = assignmentService.deleteAssignment(3L);

        // Then
        Assertions.assertTrue(result);
        verify(assignmentRepository, times(1)).existsById(3L);
        verify(assignmentRepository, times(1)).deleteById(3L);
    }



}
