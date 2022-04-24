package pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Embeddable
public class AttendantId implements Serializable {
    private static final long serialVersionUID = -5492470774512607600L;
    @Column(name = "date", nullable = false)
    private Instant date;

    @Column(name = "subjectID", nullable = false, length = 45)
    private String subjectID;

    @Column(name = "studentID", nullable = false)
    private Integer studentID;

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public Integer getStudentID() {
        return studentID;
    }

    public void setStudentID(Integer studentID) {
        this.studentID = studentID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AttendantId entity = (AttendantId) o;
        return Objects.equals(this.date, entity.date) &&
                Objects.equals(this.studentID, entity.studentID) &&
                Objects.equals(this.subjectID, entity.subjectID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, studentID, subjectID);
    }

}