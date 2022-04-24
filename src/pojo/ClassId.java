package pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ClassId implements Serializable {
    private static final long serialVersionUID = 8154946864160504031L;
    @Column(name = "studentID", nullable = false)
    private Integer studentID;

    @Column(name = "subjectID", nullable = false, length = 45)
    private String subjectID;

    @Column(name = "id", nullable = false, length = 45)
    private String id;

    public Integer getStudentID() {
        return studentID;
    }

    public void setStudentID(Integer studentID) {
        this.studentID = studentID;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ClassId entity = (ClassId) o;
        return Objects.equals(this.studentID, entity.studentID) &&
                Objects.equals(this.id, entity.id) &&
                Objects.equals(this.subjectID, entity.subjectID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentID, id, subjectID);
    }

}