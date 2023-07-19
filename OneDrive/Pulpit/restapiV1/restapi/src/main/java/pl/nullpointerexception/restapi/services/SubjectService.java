package pl.nullpointerexception.restapi.services;

import org.springframework.stereotype.Service;
import pl.nullpointerexception.restapi.model.Subject;
import pl.nullpointerexception.restapi.repositories.SubjectRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public Subject getSubjectById(Long id) {
        return subjectRepository.findById(id).orElse(null);
    }

    public List<Subject> getAllSubjectsByName(String name) { return subjectRepository.findAllByName(name);}


    public Subject createSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    public Subject updateSubject(Long id, Subject subject) {
        Optional<Subject> existingSubject = subjectRepository.findById(id);
        if (existingSubject.isPresent()) {
            subject.setId(id);
            return subjectRepository.save(subject);
        }
        return null;
    }

    public boolean deleteSubject(Long id) {
        if (subjectRepository.existsById(id)) {
            subjectRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
