package main;


import PaticipantCheckSystem.General.LoginForm;
import PaticipantCheckSystem.ManagerScreens.AddStudentScreen;
import PaticipantCheckSystem.ManagerScreens.DetailSchedule;
import daos.HibernateUtil;
import org.hibernate.Session;
import pojo.Calendar;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

//        new LoginForm();
//        new
        new AddStudentScreen(null);
    }

}
