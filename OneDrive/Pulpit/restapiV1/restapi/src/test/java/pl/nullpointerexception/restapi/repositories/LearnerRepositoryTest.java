package pl.nullpointerexception.restapi.repositories;

import pl.nullpointerexception.restapi.model.Student;
import pl.nullpointerexception.restapi.repositories.LearnerRepository;
import pl.nullpointerexception.restapi.model.Learner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

@DataJpaTest
public class LearnerRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private LearnerRepository learnerRepository;

    @BeforeEach
    public void setup() {
        // Dodajemy przykładowych uczniów do bazy danych przed każdym testem
        Learner learner1 = new Learner("Phil","Maddy", 26, "IT");
        Learner learner2 = new Learner("Damian","Brake", 41, "Maths");
        entityManager.persist(learner1);
        entityManager.persist(learner2);
        entityManager.flush();
    }

    @Test
    public void testFindAllByName() {
        // When
        List<Learner> result = learnerRepository.findAllByName("Phil");


        Assertions.assertEquals(1,result.size());
        // Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals("Phil", result.get(0).getName());
        Assertions.assertEquals(26, result.get(0).getAge());
        Assertions.assertEquals("IT", result.get(0).getDepartment());
    }

    @Test
    public void testFindById() {

        Long learnerID = 1L;

        // When
        Learner result = learnerRepository.findById(learnerID).orElse(null);

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals("Phil", result.getName());
        Assertions.assertEquals(26, result.getAge());
        Assertions.assertEquals("IT",result.getDepartment());
    }


    @Test
    public void testFindAllByAge() {

        // When
        List<Learner> result = learnerRepository.findAllByAge(26);


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
        List<Learner> result = learnerRepository.findAllBelowAge(27);


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
        List<Learner> result = learnerRepository.findAllAboveAge(30);


        Assertions.assertEquals(1,result.size());
        // Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals("Phil", result.get(0).getName());
        Assertions.assertEquals(26, result.get(0).getAge());
        Assertions.assertEquals("IT", result.get(0).getDepartment());
    }

    @Test
    public void testFindAllByDepartment() {

        // When
        List<Learner> result = learnerRepository.findAllByDepartment("IT");


        Assertions.assertEquals(1,result.size());
        // Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals("Phil", result.get(0).getName());
        Assertions.assertEquals(26, result.get(0).getAge());
        Assertions.assertEquals("IT", result.get(0).getDepartment());

    }

    @Test
    public void testSave() {
        // Given
        Learner learner = new Learner("Barry","Collins",37,"Economics");

        entityManager.persist(learner);
        entityManager.flush();

        // When
        Learner result = learnerRepository.save(learner);

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getId());
        Assertions.assertEquals("Barry", result.getName());
        Assertions.assertEquals(37, result.getAge());
        Assertions.assertEquals("Economics",result.getDepartment());
    }

    @Test
    public void testDelete() {
        // Given
        Learner learner = learnerRepository.findAllByName("Barry").get(0);

        // When
        learnerRepository.delete(learner);

        // Then
        Learner result = learnerRepository.findById(learner.getId()).orElse(null);
        Assertions.assertNull(result);
    }
}

