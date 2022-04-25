package daos;

import PaticipantCheckSystem.General.UserSession;
import org.hibernate.Session;
import org.hibernate.query.Query;
import pojo.Calendar;

import java.time.LocalDate;
import java.util.List;

public class CalendarDAO {
    public static boolean createCalendar(String subjectID, String roomID, String weekday, LocalDate startDay, LocalDate endDay, String startTime, String endTime) {
        Integer creatorID = UserSession.getUserID();
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.persist(new Calendar(subjectID, roomID, weekday, startDay, endDay, creatorID, startTime, endTime));
        session.getTransaction().commit();
        return true;
    }

    public static Calendar getCalendar(String subjectID, String roomID, String weekday){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        String hql = "FROM Calendar WHERE subjectID = :subjectID";

        Query query = session.createQuery(hql);
        query.setParameter("subjectID", subjectID);
        List<Calendar> calendars = query.list();
        for (Calendar calendar : calendars) {
            if (calendar.getRoomID().toString().equals(roomID) && calendar.getWeekday().toString().equals(weekday)) {
                session.close();
                return calendar;
            }
        }
        session.close();
        return null;

    }
}