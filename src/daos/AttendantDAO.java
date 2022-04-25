package daos;

import org.hibernate.Session;
import pojo.Attendant;
import pojo.Student;
import pojo.Subject;

import java.time.Instant;



public class AttendantDAO {
    public static void takeCheckInProgress(Integer studentId, String subjectID) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Instant today = Instant.now();
        Student student= session.get(Student.class, studentId);
        Subject subject = session.get(Subject.class, subjectID);
        Attendant attendant = new Attendant(student, subject, today);
        if(!session.contains(attendant)) {
            session.persist(attendant);
        }
        session.getTransaction().commit();
        session.close();
    }


}
