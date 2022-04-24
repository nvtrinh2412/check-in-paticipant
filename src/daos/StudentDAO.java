package daos;

import org.hibernate.Session;
import org.hibernate.query.Query;
import pojo.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentDAO {
    public static HashMap<String, Integer> getStudentList() {
        HashMap<String,Integer> studentList = new HashMap<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Student");
        List<Student> students = query.list();
        for (Student s : students) {
            studentList.put(s.getName(),s.getId());
        }
        session.close();
        return studentList;

    }

    public static String[][] convertHashmap2ArrayList(HashMap<String,Integer> studentList){
        String[][] array = new String[studentList.size()][2];
        int count = 0;
        for(Map.Entry<String,Integer> entry : studentList.entrySet()){
            array[count][0] = String.valueOf(entry.getValue());
            array[count][1] = entry.getKey();
            count++;
        }
        return array;
    }
}
