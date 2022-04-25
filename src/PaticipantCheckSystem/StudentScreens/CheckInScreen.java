package PaticipantCheckSystem.StudentScreens;

import PaticipantCheckSystem.General.ConfigurationDialog;
import PaticipantCheckSystem.General.UserSession;
import daos.*;
import pojo.Attendant;
import pojo.Calendar;

import javax.swing.*;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;
import java.util.Set;

public class CheckInScreen extends JDialog{
    private JComboBox<String> comboBoxSubject;
    private JButton btnCheckIn;
    JPanel panel1;
    private JComboBox<String> comboBoxClass;
    private JComboBox<String> comboBoxWeekDay;
    private JPanel panelCheckIn;

    public CheckInScreen(JFrame parent) {
        super(parent);
        setLocationRelativeTo(parent);
        createUIComponents();

        //set up data for comboBoxSubject, comboBoxWeekDay, comboBoxClass
        HashMap<String,String> subjectMap = SubjectDAO.getSubjectList();
        String[] subjectNameList = subjectMap.keySet().toArray(new String[0]);
        HashMap<String, Integer> roomMap = RoomDAO.getRoomList();
        String[] roomNameList = roomMap.keySet().toArray(new String[0]);
        String[] weekDayList = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};

        comboBoxSubject.setModel(new DefaultComboBoxModel<>(subjectNameList));
        comboBoxWeekDay.setModel(new DefaultComboBoxModel<>(weekDayList));
        comboBoxClass.setModel(new DefaultComboBoxModel<>(roomNameList));

        //set up event for btnCheckIn
        btnCheckIn.addActionListener(e -> {
            String subjectName = Objects.requireNonNull(comboBoxSubject.getSelectedItem()).toString();
            String subjectID = subjectMap.get(subjectName);
            String roomName = Objects.requireNonNull(comboBoxClass.getSelectedItem()).toString();
            Integer roomID = roomMap.get(roomName);
            String weekDay = Objects.requireNonNull(comboBoxWeekDay.getSelectedItem()).toString();
            Integer attendantID = 19120700;


            if(checkValidCheckIn(subjectID,roomID,weekDay)){
                AttendantDAO.takeCheckInProgress(attendantID,subjectID);
            }

        });
    }

    private void createUIComponents() {
        ConfigurationDialog.installBasicConfiguration(this,"Check in",panelCheckIn);
    }

    public boolean checkValidCheckIn(String subjectID, Integer roomID, String weekDay){
        Calendar calendar = CalendarDAO.getCalendar(subjectID,roomID.toString(),weekDay);
        Date date = new Date();
        String current = date.getHours() + ":" + date.getMinutes();


        if(calendar == null){
            JOptionPane.showMessageDialog(this,"No class on this day");
            return false;
        }
        else{
            String startTimeSchedule = calendar.getStartTime();
            String endTimeSchedule = calendar.getEndTime();
            LocalTime startTime = LocalTime.parse(startTimeSchedule);
            LocalTime endTime =  LocalTime.parse(endTimeSchedule);
            LocalTime currentTime = LocalTime.parse(current);
            if(currentTime.isBefore(startTime) && currentTime.isAfter(endTime)){
                JOptionPane.showMessageDialog(this,"Check in success");
                return true;

            }
            else{
                JOptionPane.showMessageDialog(this,"You must checking during class time");
                return false;
            }
        }
    }
}
