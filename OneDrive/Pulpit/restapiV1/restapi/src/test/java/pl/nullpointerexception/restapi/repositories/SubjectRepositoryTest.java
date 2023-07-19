package pl.nullpointerexception.restapi.repositories;

import pl.nullpointerexception.restapi.repositories.SubjectRepository;
import pl.nullpointerexception.restapi.model.Subject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

@DataJpaTest
public class SubjectRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SubjectRepository subjectRepository;

    @BeforeEach
    public void setup() {
        // Dodajemy przykładowe przedmioty do bazy danych przed każdym testem
        Subject subject1 = new Subject("Math");
        Subject subject2 = new Subject("Science");
        entityManager.persist(subject1);
        entityManager.persist(subject2);
        entityManager.flush();
    }

    @Test
    public void testFindById() {

        Long subjectID = 2L;

        // When
        Subject result = subjectRepository.findById(subjectID).orElse(null);

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(2L,result.getId());
        Assertions.assertEquals("Science", result.getName());
    }

    @Test
    public void testFindAllByName() {
        // When
        List<Subject> result = subjectRepository.findAllByName("Math");

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1L,result.get(0).getId());
        Assertions.assertEquals("Math", result.get(0).getName());
    }

    @Test
    public void testSave() {
        // Given
        Subject subject = new Subject("Economics");

        entityManager.persist(subject);
        entityManager.flush();

        // When
        Subject result = subjectRepository.save(subject);

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getId());
        Assertions.assertEquals(3L,result.getId());
        Assertions.assertEquals("Economics", result.getName());
    }

    @Test
    public void testDelete() {
        // Given
        Subject subject = subjectRepository.findAllByName("Economics").get(0);

        // When
        subjectRepository.delete(subject);

        // Then
        Subject result = subjectRepository.findById(subject.getId()).orElse(null);
        Assertions.assertNull(result);
    }
}

