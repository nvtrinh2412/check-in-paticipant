package daos;

import org.hibernate.Session;
import org.hibernate.query.Query;
import pojo.Attendant;
import pojo.AttendantId;
import pojo.Student;
import pojo.Subject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class AttendantDAO {
    public static boolean takeCheckInProgress(Integer studentId, String subjectID) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        LocalDate date = LocalDate.now();
        Student student = session.get(Student.class, studentId);
        Subject subject = session.get(Subject.class, subjectID);
        Attendant attendant = new Attendant(student, subject, date);
        AttendantId attendantId = new AttendantId(studentId, subjectID, date);
        if (session.get(Attendant.class, attendantId) == null) {
            session.persist(attendant);
            session.getTransaction().commit();
            session.close();
            return true;
        } else {
            System.out.println("Attendant already exists");
            session.close();
            return false;
        }

    }

    public static List<LocalDate> getCheckInResult(Integer studentId, String subjectID) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery(" from Attendant");
        List<Attendant> attendants = query.list();
        List <LocalDate> dates =  new ArrayList<>();
        for (Attendant attendant : attendants) {
            if (attendant.getId().getStudentID().equals(studentId) && attendant.getId().getSubjectID().equals(subjectID)) {
                dates.add(attendant.getId().getDate());
            }
        }
        session.close();
//        return dates;
        return dates;


    }

    public static String[][] getCheckInResult(String subjectID) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery(" from Attendant");
        List<Attendant> attendants = query.list();
        List<String[]> result = new ArrayList<>();
        for (Attendant attendant : attendants) {
            if (attendant.getId().getSubjectID().equals(subjectID)) {
                String[] row = new String[2];
                row[0] = attendant.getId().getStudentID().toString();
                row[1] = attendant.getId().getDate().toString();
                result.add(row);
            }
        }
        session.close();
        String[][] resultArray = new String[result.size()][2];
        for (int i = 0; i < result.size(); i++) {
            resultArray[i] = result.get(i);
        }
        return resultArray;
    }

    public static List<LocalDate> getCheckInWeekOf(String studentID,String[][] resultArray){
        List<LocalDate> weekOf = new ArrayList<>();
        for(int i = 0; i < resultArray.length; i++){
            if(resultArray[i][0].equals(studentID)){
                weekOf.add(LocalDate.parse(resultArray[i][1]));
            }
        }
        return weekOf;
    }

    }

}
