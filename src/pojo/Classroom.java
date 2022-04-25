package pojo;

import jakarta.persistence.*;

@Entity
@Table(name = "classes")
public class Classroom {
    @EmbeddedId
    private ClassId id;

    @MapsId("studentID")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "studentID", nullable = false)
    private Student studentID;

    @MapsId("subjectID")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "subjectID", nullable = false)
    private Subject subject;

    public Classroom() {
        studentID = new Student();
    }

    public Classroom(Student student, Subject subject, String classId) {
        this.studentID = student;
        this.subject = subject;
        this.id = new ClassId(student.getId(), subject.getId(),classId);


    }

    public ClassId getId() {
        return id;
    }

    public void setId(ClassId id) {
        this.id = id;
    }

    public Student getStudent() {
        return studentID;
    }

    public void setStudent(Student student) {
        this.studentID = student;
    }

    public Subject getSubjectID() {
        return subject;
    }

    public void setSubjectID(Subject subjectID) {
        this.subject = subjectID;
    }

    public  String getClassId() {
        return id.getId();
    }

}