package daos;

import org.hibernate.Session;

import java.time.LocalDate;

public class CalendarDAO {
    public static boolean createCalendar(String subjectID, String roomID, String weekday, LocalDate startDay, LocalDate endDay, String startTime, String endTime) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
//        session.persist(new Calendar(subjectID, roomID, weekday, startDay, endDay, startTime, endTime));
        session.getTransaction().commit();
        return true;
    }
}