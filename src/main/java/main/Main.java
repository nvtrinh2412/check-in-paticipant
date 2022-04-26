package main;


import PaticipantCheckSystem.ManagerScreens.ManagerCheckInResult;
import daos.AttendantDAO;

import java.util.Arrays;


public class Main {
    public static void main(String[] args) {

//        new LoginForm();
//
//        new CheckInScreen();
//        AttendantDAO.takeCheckInProgress(19120696,"3");

//        new CheckInResult(null);

//        new ManagerCheckInResult(null);
        String[][] a = AttendantDAO.getCheckInResult("BIO00001543");
        System.out.println(Arrays.deepToString(a));


    }

}
