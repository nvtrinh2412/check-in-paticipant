package pojo;

import jakarta.persistence.*;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "attendants")
public class Attendant {
    @EmbeddedId
    private AttendantId id;

    @MapsId("subjectID")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "subjectID", nullable = false)
    private Subject subjectID;

    @MapsId("studentID")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "studentID", nullable = false)
    private Student studentID;

    public Attendant() {
    }

    public Attendant(Student studentID, Subject subjectID, LocalDate day) {
        this.studentID = studentID;
        this.subjectID = subjectID;
        this.id = new AttendantId(studentID.getId(), subjectID.getId(), day);
    }

    public AttendantId getId() {
        return id;
    }

    public void setId(AttendantId id) {
        this.id = id;
    }

    public Subject getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(Subject subjectID) {
        this.subjectID = subjectID;
    }

    public Student getStudentID() {
        return studentID;
    }

    public void setStudentID(Student studentID) {
        this.studentID = studentID;
    }

}