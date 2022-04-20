package pojo;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "calendar")
public class Calendar {
    @EmbeddedId
    private CalendarId id;

    @Column(name = "start_day")
    private LocalDate startDay;

    @Column(name = "end_day")
    private LocalDate endDay;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "giaovuID", nullable = false)
    private Manager giaovuID;

    @Column(name = "start_time", length = 25)
    private String startTime;

    @Column(name = "end_time", length = 25)
    private String endTime;

    public CalendarId getId() {
        return id;
    }

    public void setId(CalendarId id) {
        this.id = id;
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

    public Manager getGiaovuID() {
        return giaovuID;
    }

    public void setGiaovuID(Manager giaovuID) {
        this.giaovuID = giaovuID;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

}