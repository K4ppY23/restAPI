package pl.nullpointerexception.restapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "subjects")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    // Konstruktory, gettery i settery

    public Subject() {
    }

    public Subject(String name) {
        this.name = name;
    }

    public Subject(Long id,String name) {
        this.id = id;
        this.name = name;
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
}
