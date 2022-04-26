package daos;

import org.hibernate.Session;
import org.hibernate.query.Query;
import pojo.Manager;
import pojo.Subject;

import java.util.HashMap;
import java.util.List;

public class SubjectDAO {
    public static void createSubject(String subjectID, String subjectName, int creatorID) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Subject subject = new Subject(subjectID, subjectName, new Manager(creatorID));

        session.beginTransaction();
        if(!session.contains(subject)) {
            session.persist(subject);

        }
        else{
            System.out.println("Subject already exists");
        }
        session.getTransaction().commit();
        session.close();

    }

    public static HashMap<String,String> getSubjectList() {
        HashMap<String,String> subject = new HashMap<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Subject");
        List<Subject> subjects = query.list();
        for (Subject s : subjects) {
            subject.put(s.getName(),s.getId());
        }
        session.close();
        return subject;
    }


}
