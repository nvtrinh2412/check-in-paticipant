package pojo;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "subjects")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creatorID")
    private Manager creatorID;

    @ManyToMany
    @JoinTable(name = "student_subject",
            joinColumns = @JoinColumn(name = "subjectID"),
            inverseJoinColumns = @JoinColumn(name = "studentID"))
    private Set<Student> students = new LinkedHashSet<>();

    @OneToMany(mappedBy = "subject")
    private Set<Attendant> attendants = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Manager getCreatorID() {
        return creatorID;
    }

    public void setCreatorID(Manager creatorID) {
        this.creatorID = creatorID;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Set<Attendant> getAttendants() {
        return attendants;
    }

    public void setAttendants(Set<Attendant> attendants) {
        this.attendants = attendants;
    }

}