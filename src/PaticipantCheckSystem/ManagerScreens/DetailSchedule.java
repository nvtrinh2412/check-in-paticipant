package PaticipantCheckSystem.ManagerScreens;

import PaticipantCheckSystem.General.ConfigurationDialog;
import PaticipantCheckSystem.General.UserSession;
import daos.CalendarDAO;
import daos.HibernateUtil;
import daos.RoomDAO;
import daos.SubjectDAO;
import org.hibernate.Session;
import pojo.Calendar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.*;

public class DetailSchedule extends JDialog{
    private JComboBox<String> comboBoxWeekDay;
    private JComboBox<String> comboBoxRoom;
    private JSpinner spinnerStartHour;
    private JSpinner spinnerStartMinute;
    private JComboBox<String> comboBoxStartMonth;
    private JButton btnConfirm;
    private JLabel labelSelectedSubject;
    private JPanel panelDetailSchedule;
    private JComboBox<String> comboBoxSubject;
    private JSpinner spinnerStartYear;
    private JSpinner spinnerStartDay;
    private JSpinner spinnerEndHour;
    private JSpinner spinnerEndMinute;
    private JComboBox<String> comboBoxEndMonth;
    private JSpinner spinnerEndDay;
    private JSpinner spinnerEndYear;

    public DetailSchedule(JFrame parent) {
        super(parent);
        setLocationRelativeTo(parent);
        createUIComponents();

        //set up room

        HashMap<String,Integer> roomList = RoomDAO.getRoomList();
        List<String> roomNameList = new ArrayList<>(roomList.keySet());
        comboBoxRoom.setModel(new DefaultComboBoxModel(roomNameList.toArray()));


        //set up Subject
        HashMap<String, String> subjectList = SubjectDAO.getSubjectList();
        List<String> subjectNameList = new ArrayList<String>(subjectList.keySet());
        comboBoxSubject.setModel(new DefaultComboBoxModel(subjectNameList.toArray()));

        //set up limit time
        SpinnerModel hourStartModel = new SpinnerNumberModel(0, 0, 23, 1); //default value,lower bound,upper bound,increment by
        SpinnerModel minuteStartModel = new SpinnerNumberModel(0, 0, 59, 1);
        SpinnerModel hourEndModel = new SpinnerNumberModel(0, 0, 23, 1);
        SpinnerModel minuteEndModel = new SpinnerNumberModel(0, 0, 59, 1);

        spinnerStartHour.setModel(hourStartModel);
        spinnerStartMinute.setModel(minuteStartModel);
        spinnerEndHour.setModel(hourEndModel);
        spinnerEndMinute.setModel(minuteEndModel);


        //set up date
        Date today = new Date();
        int year = today.getYear() + 1900;
        SpinnerModel yearStartModel = new SpinnerNumberModel(year, year, null, 1); //default value,lower bound,upper bound,increment by
        SpinnerModel monthStartModel = new SpinnerNumberModel(1, 1, 12, 1);
        SpinnerModel dayStartModel = new SpinnerNumberModel(1, 1, 31, 1); //default value,lower bound,upper bound,increment by
        SpinnerModel yearEndModel = new SpinnerNumberModel(year, year, null, 1); //default value,lower bound,upper bound,increment by
        SpinnerModel dayEndModel = new SpinnerNumberModel(1, 1, 31, 1); //default value,lower bound,upper bound,increment by

        spinnerStartYear.setModel(yearStartModel);
        spinnerStartYear.setEditor(new JSpinner.NumberEditor(spinnerStartYear, "####"));
        spinnerStartDay.setModel(dayStartModel);

        spinnerEndYear.setModel(yearEndModel);
        spinnerEndYear.setEditor(new JSpinner.NumberEditor(spinnerEndYear, "####"));
        spinnerEndDay.setModel(dayEndModel);

        //set up month
        comboBoxStartMonth.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                int month = comboBoxStartMonth.getSelectedIndex() + 1;
                var date = new Date();
                YearMonth yearMonthObject = YearMonth.of(year, month);
                int dayOfMonth = yearMonthObject.lengthOfMonth();
                SpinnerModel dayModel = new SpinnerNumberModel(1, 1, dayOfMonth, 1); //default value,lower bound,upper bound,increment by
                spinnerStartDay.setModel(dayModel);
            }
        });


        // set up when press confirm button
        btnConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String subjectName = Objects.requireNonNull(comboBoxSubject.getSelectedItem()).toString();

                int startDay = (int) spinnerStartDay.getValue();
                int startMonth = Integer.parseInt(Objects.requireNonNull(comboBoxStartMonth.getSelectedItem()).toString());
                int startYear = (int) spinnerStartYear.getValue();
                String startHour = spinnerStartHour.getValue().toString();
                String startMinute = spinnerStartMinute.getValue().toString();
                int endDay = (int) spinnerEndDay.getValue();
                int endMonth = Integer.parseInt(Objects.requireNonNull(comboBoxEndMonth.getSelectedItem()).toString());
                int endYear = (int) spinnerEndYear.getValue();
                String endHour = spinnerEndHour.getValue().toString();
                String endMinute = spinnerEndMinute.getValue().toString();

                if(endMinute.length() == 1) endMinute = "0" + endMinute;
                if(startMinute.length() == 1) startMinute = "0" + startMinute;
                if(endHour.length() == 1) endHour = "0" + endHour;
                if(startHour.length() == 1) startHour = "0" + startHour;

                String weekday = Objects.requireNonNull(comboBoxWeekDay.getSelectedItem()).toString();

                String startTime = startHour + ":" + startMinute;
                String endTime = endHour + ":" + endMinute;
                LocalDate startDate = LocalDate.of(startYear, startMonth, startDay);
                LocalDate endDate = LocalDate.of(endYear, endMonth, endDay);
                String subjectID = subjectList.get(subjectName);
                String roomID = String.valueOf(roomList.get(Objects.requireNonNull(comboBoxRoom.getSelectedItem()).toString()));
                Integer managerID = UserSession.getInstance().getId();
                LocalTime startTimeObject = LocalTime.parse(startTime);
                LocalTime endTimeObject = LocalTime.parse(endTime);


                if ( startDate.isAfter(endDate) ) {
                    JOptionPane.showMessageDialog(null, "Start of Date must be before end Date");

                }
                else if( startTimeObject.compareTo(endTimeObject) > 0){
                    JOptionPane.showMessageDialog(null, "Start of Time must be before end Time");
                }
                else {
                    if (CalendarDAO.createCalendar(subjectID, roomID, weekday, startDate, endDate, startTime, endTime)) {
                        JOptionPane.showMessageDialog(null, "Successfully added");
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "This subject is already scheduled for this room on this day");
                    }
                }
            }
        });
    }

    private void createUIComponents() {
        ConfigurationDialog.installBasicConfiguration(this,"Detail Schedule", panelDetailSchedule);
    }
}
