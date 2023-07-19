package pl.nullpointerexception.restapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "age")
    private int age;

    @Column(name = "mainSubject")
    private String mainSubject;

    // Konstruktory, gettery i settery

    public Student() {
    }

    public Student(String name,String surname, int age, String mainSubject) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.mainSubject = mainSubject;
    }

    public Student(Long id,String name,String surname, int age, String mainSubject) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.mainSubject = mainSubject;
    }

    // Gettery i settery

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMainSubject() {
        return mainSubject;
    }

    public void setMainSubject(String mainSubject) {
        this.mainSubject = mainSubject;
    }
}