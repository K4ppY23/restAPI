package pl.nullpointerexception.restapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "assignments")
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "studentID")
    private Long studentID;

    @Column(name = "learnerID")
    private Long learnerID;

    @Column(name = "subjectID")
    private Long subjectID;

    // Konstruktory, gettery i settery

    public Assignment() {
    }

    public Assignment(Long studentID, Long learnerID, Long subjectID) {
        this.studentID = studentID;
        this.learnerID = learnerID;
        this.subjectID = subjectID;

    }

    public Assignment(Long id,Long studentID, Long learnerID, Long subjectID) {
        this.id = id;
        this.studentID = studentID;
        this.learnerID = learnerID;
        this.subjectID = subjectID;

    }

    // Gettery i settery



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentID() {
        return studentID;
    }

    public void setStudentID(Long studentID) {
        this.studentID = studentID;
    }

    public Long getLearnerID() {
        return learnerID;
    }

    public void setLearnerID(Long learnerID) {
        this.learnerID = learnerID;
    }

    public Long getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(Long studentID) {
        this.subjectID = subjectID;
    }





}
