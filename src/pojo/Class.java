package pojo;

import jakarta.persistence.*;

@Entity
@Table(name = "classes")
public class Class {
    @EmbeddedId
    private ClassId id;

    @MapsId("studentID")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "studentID", nullable = false)
    private Student studentID;

    @MapsId("subjectID")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "subjectID", nullable = false)
    private Subject subjectID;

    public ClassId getId() {
        return id;
    }

    public void setId(ClassId id) {
        this.id = id;
    }

    public Student getStudentID() {
        return studentID;
    }

    public void setStudentID(Student studentID) {
        this.studentID = studentID;
    }

    public Subject getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(Subject subjectID) {
        this.subjectID = subjectID;
    }

}