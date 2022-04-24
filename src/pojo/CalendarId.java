package pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CalendarId implements Serializable {
    private static final long serialVersionUID = -2005044404070582196L;
    @Column(name = "roomID", nullable = false)
    private Integer roomID;

    @Column(name = "weekday", nullable = false, length = 20)
    private String weekday;

    @Column(name = "start_time", nullable = false, length = 25)
    private String startTime;

    public CalendarId() {
    }

    public CalendarId(String startTime, String roomID, String weekday) {
        this.roomID = Integer.parseInt(roomID);
        this.weekday = weekday;
        this.startTime = startTime;
    }

    public Integer getRoomID() {
        return roomID;
    }

    public void setRoomID(Integer roomID) {
        this.roomID = roomID;
    }

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CalendarId entity = (CalendarId) o;
        return Objects.equals(this.weekday, entity.weekday) &&
                Objects.equals(this.startTime, entity.startTime) &&
                Objects.equals(this.roomID, entity.roomID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(weekday, startTime, roomID);
    }

}