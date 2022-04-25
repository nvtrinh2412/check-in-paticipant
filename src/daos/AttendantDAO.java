package daos;

import org.hibernate.Session;
import pojo.Attendant;
import pojo.AttendantId;
import pojo.Student;
import pojo.Subject;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;


public class AttendantDAO {
    public static boolean takeCheckInProgress(Integer studentId, String subjectID) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        LocalDate date = LocalDate.now();
        Student student= session.get(Student.class, studentId);
        Subject subject = session.get(Subject.class, subjectID);
        Attendant attendant = new Attendant(student, subject, date);
        AttendantId attendantId = new AttendantId(studentId, subjectID,date);
        if(session.get(Attendant.class,attendantId)==null){
            session.persist(attendant);
            session.getTransaction().commit();
            session.close();
            return true;
        }
        else {
            System.out.println("Attendant already exists");
            session.close();
            return false;
        }



    }


}
