package daos;

import org.hibernate.Session;
import org.hibernate.query.Query;
import pojo.Room;

import java.util.HashMap;
import java.util.List;

public class RoomDAO {
    public static HashMap<String,Integer> getRoomList() {
        HashMap<String,Integer> roomList = new HashMap<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Room");
        List<Room> rooms = query.list();
        for (Room s : rooms) {
            roomList.put(s.getName(),s.getId());
        }
        session.close();
        return roomList;
    }

}
