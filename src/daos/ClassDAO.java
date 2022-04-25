package daos;

import org.hibernate.Session;
import org.hibernate.query.Query;
import pojo.ClassId;
import pojo.Classroom;
import pojo.Student;
import pojo.Subject;

import java.util.*;


public class ClassDAO {
    public static void addStudentToClass(List<Integer> studentId, String subjectID, String classID) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
       for(Integer id : studentId) {

           ClassId classId1 = new ClassId(id,subjectID, classID);
           Student student = session.get(Student.class, id);
           System.out.println("Student " + student.getId());
           Subject subject = session.get(Subject.class, subjectID);
           System.out.println("Subject " + subject.getId());

           Classroom classroom = new Classroom(student, subject, classID);
           if(session.get(Classroom.class, classId1) == null) {
               session.persist(classroom);
           }
           else{
               System.out.println("Classroom already exists-> ignored");
           }

       }
        session.getTransaction().commit();
        session.close();

    }

    public static Set<String> getClassList() {
        Set<String> classList = new HashSet<>() ;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Classroom");
        List<Classroom> classroomList = query.list();
        session.getTransaction().commit();
        session.close();

        if(classroomList.size() > 0) {
            for(Classroom classroom : classroomList) {
                classList.add(classroom.getId().getId());
            }
        }
        return classList;
    }

}
