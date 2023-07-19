package pl.nullpointerexception.restapi.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import pl.nullpointerexception.restapi.repositories.SubjectRepository;
import pl.nullpointerexception.restapi.services.SubjectService;
import pl.nullpointerexception.restapi.model.Subject;

import static org.mockito.Mockito.*;

public class SubjectServiceTest {

    @Mock
    private SubjectRepository subjectRepository;

    private SubjectService subjectService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        subjectService = new SubjectService(subjectRepository);
    }

    @Test
    public void testGetAllSubjects() {
        // Given
        List<Subject> subjects = new ArrayList<>();
        subjects.add(new Subject("Math"));
        subjects.add(new Subject("Science"));
        when(subjectRepository.findAll()).thenReturn(subjects);

        // When
        List<Subject> result = subjectService.getAllSubjects();

        // Then
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("Math", result.get(0).getName());
        Assertions.assertEquals("Science", result.get(1).getName());
        verify(subjectRepository, times(1)).findAll();
    }

    @Test
    public void testGetSubjectById() {
        // Given
        Long subjectId = 1L;
        Subject subject = new Subject("Math");
        when(subjectRepository.findById(subjectId)).thenReturn(Optional.of(subject));

        // When
        Subject result = subjectService.getSubjectById(subjectId);

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(subjectId, result.getId());
        Assertions.assertEquals("Math", result.getName());
        verify(subjectRepository, times(1)).findById(subjectId);
    }

    @Test
    public void testFindAllByName() {
        // When
        List<Subject> result = subjectService.getAllSubjectsByName("Math");

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1L,result.get(0).getId());
        Assertions.assertEquals("Math", result.get(0).getName());


    }

    @Test
    public void testCreateSubject() {
        // Given
        Subject subject = new Subject("Math");
        when(subjectRepository.save(subject)).thenReturn(subject);

        // When
        Subject result = subjectService.createSubject(subject);

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getId());
        Assertions.assertEquals("Math", result.getName());
        verify(subjectRepository, times(1)).save(subject);
    }

    @Test
    public void testUpdateSubject() {
        // Given
        Long subjectId = 1L;
        Subject existingSubject = new Subject("Math");
        Subject updatedSubject = new Subject("Science");
        when(subjectRepository.findById(subjectId)).thenReturn(Optional.of(existingSubject));
        when(subjectRepository.save(updatedSubject)).thenReturn(updatedSubject);

        // When
        Subject result = subjectService.updateSubject(subjectId, updatedSubject);

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(subjectId, result.getId());
        Assertions.assertEquals("Science", result.getName());
        verify(subjectRepository, times(1)).findById(subjectId);
        verify(subjectRepository, times(1)).save(updatedSubject);
    }

    @Test
    public void testDeleteSubject() {
        // Given
        Long subjectId = 1L;
        when(subjectRepository.existsById(subjectId)).thenReturn(true);

        // When
        boolean result = subjectService.deleteSubject(subjectId);

        // Then
        Assertions.assertTrue(result);
        verify(subjectRepository, times(1)).existsById(subjectId);
        verify(subjectRepository, times(1)).deleteById(subjectId);
    }
}

