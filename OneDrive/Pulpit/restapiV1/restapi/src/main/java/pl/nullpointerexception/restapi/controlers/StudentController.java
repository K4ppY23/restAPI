package pl.nullpointerexception.restapi.controlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.nullpointerexception.restapi.model.Student;
import pl.nullpointerexception.restapi.model.Subject;
import pl.nullpointerexception.restapi.services.StudentService;

import java.util.List;

@RestController
@RequestMapping("/students")

public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();

        if (students != null) {
            return ResponseEntity.ok(students);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") Long id) {
        Student student = studentService.getStudentById(id);
        if (student != null) {
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<Student>> getAllStudentsByName(@PathVariable("name") String name) {
        List<Student> students = studentService.getAllStudentsByName(name);

        if(students != null)
            return ResponseEntity.ok(students);
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{surname}")
    public ResponseEntity<List<Student>> getAllStudentsBySurname(@PathVariable("surname") String surname) {
        List<Student> students = studentService.getAllStudentsBySurname(surname);

        if(students != null)
            return ResponseEntity.ok(students);
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{age}")
    public ResponseEntity<List<Student>> getAllStudentsByAge(@PathVariable("age") int age) {
        List<Student> students = studentService.getAllStudentsByAge(age);

        if(students != null)
            return ResponseEntity.ok(students);
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{age}")
    public ResponseEntity<List<Student>> getAllStudentsAboveAge(@PathVariable("age") int age) {
        List<Student> students = studentService.getAllStudentsAboveAge(age);

        if(students != null)
            return ResponseEntity.ok(students);
        else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/{age}")
    public ResponseEntity<List<Student>> getAllStudentsBelowAge(@PathVariable("age") int age) {
        List<Student> students = studentService.getAllStudentsBelowAge(age);

        if(students != null)
            return ResponseEntity.ok(students);
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{mainSubject}")
    public ResponseEntity<List<Student>> getAllStudentsByMainSubject(@PathVariable("mainSubject") String mainSubject) {
        List<Student> students = studentService.getAllStudentsByMainSubject(mainSubject);

        if(students != null)
            return ResponseEntity.ok(students);
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student createdStudent = studentService.createStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") Long id, @RequestBody Student student) {
        Student updatedStudent = studentService.updateStudent(id, student);
        if (updatedStudent != null) {
            return ResponseEntity.ok(updatedStudent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable("id") Long id) {
        boolean deleted = studentService.deleteStudent(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
