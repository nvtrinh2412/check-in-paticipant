package daos;

import PaticipantCheckSystem.General.HashPassword;
import PaticipantCheckSystem.General.UserSession;
import org.hibernate.Session;
import org.hibernate.query.Query;
import pojo.Account;

public class LoginDAO {
    public static String authorizedAccount(String username, String password) {
//        String accountQuery = " from Account where username ="+username;
        String accountQuery = " from Account where username =:username and password =:password";
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery(accountQuery);
        query.setParameter("username", username);
        query.setParameter("password", password);
        Account account = (Account) query.uniqueResult();
        session.close();


        if (account != null) {

            UserSession.setUserAccount(account);

            if ( account.getRole().equals("manager")) {
                return "Manager";
            }
            else if (account.getRole().equals("student") && account.getIsValid() == 1) {
                return "Authorized Student";
            }
            else
                return "Unauthorized Student";
        }
        return "Fail";

    }

    public static void changePassword(String username, String password) {
        String hashedPassword = HashPassword.hash(password);
        Session session = HibernateUtil.getSessionFactory().openSession();

        String passwordQuery = "update Account set password = :password where username = :username";
        session.beginTransaction();
        Query query = session.createQuery(passwordQuery);
        query.setParameter("password", hashedPassword);
        query.setParameter("username", username);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
    public static void changeValidStatus(String username, boolean isValid) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String passwordQuery = "update Account set isValid = :isValid where username = :username";
        session.beginTransaction();
        Query query = session.createQuery(passwordQuery);
        query.setParameter("isValid", 1);
        query.setParameter("username", username);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
