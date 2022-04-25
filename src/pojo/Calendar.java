package pojo;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "calendar")
public class Calendar {
    @EmbeddedId
    private CalendarId id;

    @Column(name = "subjectID", nullable = false, length = 45)
    private String subjectID;

    @Column(name = "start_day", nullable = false)
    private LocalDate startDay;

    @Column(name = "end_day")
    private LocalDate endDay;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "managerID", nullable = false)
    private Manager managerID;

    @Column(name = "end_time", length = 25)
    private String endTime;

    public Calendar() {
    }

    public Calendar(String subjectID, String roomID, String weekday, LocalDate startDate, LocalDate endDate, int managerID, String startTime, String endTime) {
        this.id = new CalendarId(startTime, roomID, weekday);
        this.subjectID = subjectID;
        this.startDay = startDate;
        this.endDay = endDate;
        this.managerID = new Manager(managerID);
        this.endTime = endTime;
    }

    public Calendar(String subjectID, String roomID, String weekday, LocalDate startDay, LocalDate endDay, Integer managerID, String startTime, String endTime) {
        this.id = new CalendarId(startTime, roomID, weekday);
        this.subjectID = subjectID;
        this.startDay = startDay;
        this.endDay = endDay;
        this.managerID = new Manager(managerID);
        this.endTime = endTime;

    }


    public CalendarId getId() {
        return id;
    }

    public void setId(CalendarId id) {
        this.id = id;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public LocalDate getStartDay() {
        return startDay;
    }

    public void setStartDay(LocalDate startDay) {
        this.startDay = startDay;
    }

    public LocalDate getEndDay() {
        return endDay;
    }

    public void setEndDay(LocalDate endDay) {
        this.endDay = endDay;
    }

    public Manager getManagerID() {
        return managerID;
    }

    public void setManagerID(Manager managerID) {
        this.managerID = managerID;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartTime() {
        return id.getStartTime();
    }

   public Integer getRoomID() {
        return id.getRoomID();
    }

    public String getWeekday() {
        return id.getWeekday();
    }


}