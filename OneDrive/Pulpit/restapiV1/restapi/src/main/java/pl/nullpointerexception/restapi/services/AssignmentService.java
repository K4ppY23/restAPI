package pl.nullpointerexception.restapi.services;


import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.springframework.stereotype.Service;
import pl.nullpointerexception.restapi.model.Assignment;
import pl.nullpointerexception.restapi.repositories.AssignmentRepository;

import java.util.List;
import java.util.Optional;

@Service

public class AssignmentService {
    private final AssignmentRepository assignmentRepository;

    public AssignmentService(AssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }

    public List<Assignment> getAllAssignments() { return assignmentRepository.findAll(); }

    public Assignment getAssignmentById(Long id) {
        return assignmentRepository.findById(id).orElse(null);
    }

    public List<Assignment> getAllAssignmentsByStudentID(Long studentID) { return assignmentRepository.findByStudentID(studentID); }

    public List<Assignment> getAllAssignmentsByLearnerID(Long learnerID) { return assignmentRepository.findByLearnerID(learnerID); }

    public List<Assignment> getAllAssignmentsBySubjectID(Long subjectID) { return assignmentRepository.findBySubjectID(subjectID); }

    public List<Assignment> getAllAssignmentsByStudentIDAndLearnerID(Long studentID, Long learnerID) {
        return assignmentRepository.findByStudentIDAndLearnerID(studentID,learnerID);
    }

    public List<Assignment> getAllAssignmentsByStudentIDAndSubjectID(Long studentID, Long subjectID) {
        return assignmentRepository.findByStudentIDAndSubjectID(studentID,subjectID);
    }

    public List<Assignment> getAllAssignmentsByLearnerIDAndSubjectID(Long learnerID, Long subjectID) {
        return assignmentRepository.findByLearnerIDAndSubjectID(learnerID,subjectID);
    }

    public List<Assignment> getAllAssignmentsByStudentIDAndLearnerIDAndSubjectID(Long studentID,Long learnerID, Long subjectID) {
        return assignmentRepository.findByStudentIDAndLearnerIDAndSubjectID(studentID,learnerID,subjectID);
    }

    public Assignment createAssignment(Assignment assignment) {
        return assignmentRepository.save(assignment);
    }

    public Assignment updateAssignment(Long id, Assignment assignment) {
        Optional<Assignment> existingAssignment = assignmentRepository.findById(id);
        if (existingAssignment.isPresent()) {
            assignment.setId(id);
            return assignmentRepository.save(assignment);
        }
        return null;
    }

    public boolean deleteAssignment(Long id) {
        if (assignmentRepository.existsById(id)) {
            assignmentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
