package pl.nullpointerexception.restapi.controlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.nullpointerexception.restapi.model.Learner;
import pl.nullpointerexception.restapi.model.Student;
import pl.nullpointerexception.restapi.services.LearnerService;

import java.util.List;

@RestController
@RequestMapping("/learners")
public class LearnerController {

    private final LearnerService learnerService;

    public LearnerController(LearnerService learnerService) {
        this.learnerService = learnerService;
    }

    @GetMapping
    public ResponseEntity<List<Learner>> getAllLearners()  {
        List<Learner> learners = learnerService.getAllLearners();

        if (learners != null) {
            return ResponseEntity.ok(learners);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Learner> getLearnerById(@PathVariable("id") Long id) {
        Learner learner = learnerService.getLearnerById(id);
        if (learner != null) {
            return ResponseEntity.ok(learner);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<Learner>> getAllLearnersByName(@PathVariable("name") String name) {
        List<Learner> learners = learnerService.getAllLearnersByName(name);
        if (learners != null) {
            return ResponseEntity.ok(learners);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{surname}")
    public ResponseEntity<List<Learner>> getAllLearnersBySurname(@PathVariable("surname") String surname) {
        List<Learner> learners = learnerService.getAllLearnersBySurname(surname);
        if (learners != null) {
            return ResponseEntity.ok(learners);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{age}")
    public ResponseEntity<List<Learner>> getAllLearnersByAge(@PathVariable("age") int age) {
        List<Learner> learners = learnerService.getAllLearnersByAge(age);
        if (learners != null) {
            return ResponseEntity.ok(learners);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{age}")
    public ResponseEntity<List<Learner>> getAllLearnersBelowAge(@PathVariable("age") int age) {
        List<Learner> learners = learnerService.getAllLearnersBelowAge(age);
        if (learners != null) {
            return ResponseEntity.ok(learners);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{age}")
    public ResponseEntity<List<Learner>> getAllLearnersAboveAge(@PathVariable("age") int age) {
        List<Learner> learners = learnerService.getAllLearnersAboveAge(age);
        if (learners != null) {
            return ResponseEntity.ok(learners);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{department}")
    public ResponseEntity<List<Learner>> getAllLearnersByDepartment(@PathVariable("department") String department) {
        List<Learner> learners = learnerService.getAllLearnersByDepartment(department);
        if (learners != null) {
            return ResponseEntity.ok(learners);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Learner> createLearner(@RequestBody Learner learner) {
        Learner createdLearner = learnerService.createLearner(learner);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLearner);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Learner> updateLearner(@PathVariable("id") Long id, @RequestBody Learner learner) {
        Learner updatedLearner = learnerService.updateLearner(id, learner);
        if (updatedLearner != null) {
            return ResponseEntity.ok(updatedLearner);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLearner(@PathVariable("id") Long id) {
        boolean deleted = learnerService.deleteLearner(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
