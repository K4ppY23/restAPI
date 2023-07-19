package pl.nullpointerexception.restapi.services;

import org.springframework.stereotype.Service;
import pl.nullpointerexception.restapi.model.Student;
import pl.nullpointerexception.restapi.repositories.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public List<Student> getAllStudentsByName(String name) { return studentRepository.findAllByName(name); }

    public List<Student> getAllStudentsBySurname(String surname) { return studentRepository.findAllBySurname(surname); }

    public List<Student> getAllStudentsByAge(int age) { return studentRepository.findAllByAge(age); }

    public List<Student> getAllStudentsAboveAge(int age) { return studentRepository.findAllAboveAge(age); }

    public List<Student> getAllStudentsBelowAge(int age) { return studentRepository.findAllBelowAge(age); }

    public List<Student> getAllStudentsByMainSubject(String mainSubject) { return studentRepository.findAllByMainSubject(mainSubject); }
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(Long id, Student student) {
        Optional<Student> existingStudent = studentRepository.findById(id);
        if (existingStudent.isPresent()) {
            student.setId(id);
            return studentRepository.save(student);
        }
        return null;
    }

    public boolean deleteStudent(Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
