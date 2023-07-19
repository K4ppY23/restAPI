package pl.nullpointerexception.restapi.controlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.nullpointerexception.restapi.model.Assignment;
import pl.nullpointerexception.restapi.model.Learner;
import pl.nullpointerexception.restapi.services.AssignmentService;


import java.util.List;
@RestController
@RequestMapping("/assignments")
public class AssignmentController {
    private final AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @GetMapping
    public ResponseEntity<List<Assignment>> getAllAssignments() {
        List<Assignment> assignments = assignmentService.getAllAssignments();

        if (assignments != null) {
            return ResponseEntity.ok(assignments);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Assignment> getAssignmentById(@PathVariable("id") Long id) {
        Assignment assignment = assignmentService.getAssignmentById(id);
        if (assignment != null) {
            return ResponseEntity.ok(assignment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{studentID}")
    public ResponseEntity<List<Assignment>> getAllAssignmentsByStudentID(@PathVariable("studentID") Long studentID) {
        List<Assignment> assignments = assignmentService.getAllAssignmentsByStudentID(studentID);

        if (assignments != null) {
            return ResponseEntity.ok(assignments);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{learnerID}")
    public ResponseEntity<List<Assignment>> getAllAssignmentsByLearnerID(@PathVariable("learnerID") Long learnerID) {
        List<Assignment> assignments = assignmentService.getAllAssignmentsByLearnerID(learnerID);

        if (assignments != null) {
            return ResponseEntity.ok(assignments);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{subjectID}")
    public ResponseEntity<List<Assignment>> getAllAssignmentsBySubjectID(@PathVariable("subjectID") Long subjectID) {
        List<Assignment> assignments = assignmentService.getAllAssignmentsBySubjectID(subjectID);

        if (assignments != null) {
            return ResponseEntity.ok(assignments);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{studentID,learnerID}")
    public ResponseEntity<List<Assignment>> getAllAssignmentsByStudentIDAndLearnerID(@PathVariable("studentID")Long studentID,
                                                                                     @PathVariable("learnerID")Long learnerID) {
        List<Assignment> assignments = assignmentService.getAllAssignmentsByStudentIDAndLearnerID(studentID, learnerID);

        if (assignments != null) {
            return ResponseEntity.ok(assignments);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{studentID,subjectID}")
    public ResponseEntity<List<Assignment>> getAllAssignmentsByStudentIDAndSubjectID(@PathVariable("studentID")Long studentID,
                                                                                     @PathVariable("subjectID")Long subjectID) {
        List<Assignment> assignments = assignmentService.getAllAssignmentsByStudentIDAndSubjectID(studentID, subjectID);

        if (assignments != null) {
            return ResponseEntity.ok(assignments);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{learnerID,subjectID}")
    public ResponseEntity<List<Assignment>> getAllAssignmentsByLearnerIDAndSubjectID(@PathVariable("learnerID")Long learnerID,
                                                                                     @PathVariable("subjectID")Long subjectID) {
        List<Assignment> assignments = assignmentService.getAllAssignmentsByLearnerIDAndSubjectID(learnerID, subjectID);

        if (assignments != null) {
            return ResponseEntity.ok(assignments);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{studentID,learnerID,subjectID}")
    public ResponseEntity<List<Assignment>> getAllAssignmentsByStudentIDAndLearnerIDAndSubjectID(@PathVariable("studentID")Long studentID,
                                                                                                 @PathVariable("learnerID")Long learnerID,
                                                                                                 @PathVariable("subjectID")Long subjectID) {
        List<Assignment> assignments = assignmentService.getAllAssignmentsByStudentIDAndLearnerIDAndSubjectID(studentID,learnerID, subjectID);

        if (assignments != null) {
            return ResponseEntity.ok(assignments);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @PostMapping
    public ResponseEntity<Assignment> createAssignment(@RequestBody Assignment assignment) {
        Assignment createdAssignment = assignmentService.createAssignment(assignment);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAssignment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Assignment> updateAssignment(@PathVariable("id") Long id, @RequestBody Assignment assignment) {
        Assignment updatedAssignment = assignmentService.updateAssignment(id, assignment);
        if (updatedAssignment != null) {
            return ResponseEntity.ok(updatedAssignment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssignment(@PathVariable("id") Long id) {
        boolean deleted = assignmentService.deleteAssignment(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
