package pojo;

import jakarta.persistence.*;

@Entity
@Table(name = "subjects")
public class Subject {
    @Id
    @Column(name = "id", nullable = false, length = 45)
    private String id;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creatorID")
    private Manager creatorID;

    public Subject() {
    }

    public Subject(String subjectID, String subjectName, Manager creatorID) {
        this.id = subjectID;
        this.name = subjectName;
        this.creatorID = new Manager(creatorID.getId());
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
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

}