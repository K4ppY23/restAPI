package pl.nullpointerexception.restapi.services;

import org.springframework.stereotype.Service;
import pl.nullpointerexception.restapi.model.Learner;
import pl.nullpointerexception.restapi.repositories.LearnerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LearnerService {
    private final LearnerRepository learnerRepository;

    public LearnerService(LearnerRepository learnerRepository) {
        this.learnerRepository = learnerRepository;
    }

    public List<Learner> getAllLearners() {
        return learnerRepository.findAll();
    }

    public Learner getLearnerById(Long id) {
        return learnerRepository.findById(id).orElse(null);
    }

    public List<Learner> getAllLearnersByName(String name) { return learnerRepository.findAllByName(name); }

    public List<Learner> getAllLearnersBySurname(String surname) { return learnerRepository.findAllBySurname(surname); }

    public List<Learner> getAllLearnersByAge(int age) { return learnerRepository.findAllByAge(age); }

    public List<Learner> getAllLearnersAboveAge(int age) { return learnerRepository.findAllAboveAge(age); }

    public List<Learner> getAllLearnersBelowAge(int age) { return learnerRepository.findAllBelowAge(age); }

    public List<Learner> getAllLearnersByDepartment(String department) { return learnerRepository.findAllByDepartment(department);}

    public Learner createLearner(Learner learner) {
        return learnerRepository.save(learner);
    }

    public Learner updateLearner(Long id, Learner learner) {
        Optional<Learner> existingLearner = learnerRepository.findById(id);
        if (existingLearner.isPresent()) {
            learner.setId(id);
            return learnerRepository.save(learner);
        }
        return null;
    }

    public boolean deleteLearner(Long id) {
        if (learnerRepository.existsById(id)) {
            learnerRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
