package PaticipantCheckSystem.StudentScreens;

import PaticipantCheckSystem.General.ConfigurationDialog;
import PaticipantCheckSystem.General.UserSession;
import daos.AttendantDAO;
import daos.CalendarDAO;
import daos.StudentDAO;
import daos.SubjectDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class CheckInResult extends JDialog{
    private JComboBox<String> comboBoxSubject;
    private JPanel panelCheckInResult;
    private JTable tableCheckInResult;
    private JButton btnSearch;
    String[] weekData;
    Object [][] data;
    DefaultTableModel tableModel;

    public CheckInResult(JFrame parent) {
        super(parent);
        setLocationRelativeTo(parent);
        createUIComponents();


        //Set up the table
        String[] header = {"Week","Check in status"};
        weekData = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"};

        tableModel = new DefaultTableModel(){
            public Class<?> getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return String.class;
                    case 1:
                        return Boolean.class;
                }
                return String.class;
            }
        };
        data = new Object[weekData.length][2];
        for(int i = 0; i < weekData.length; i++){
            data[i][0] = weekData[i];
            data[i][1] = false;
        }
        tableModel.setColumnIdentifiers(header);
        tableCheckInResult.setModel(tableModel);
        tableModel.setDataVector(data,header);
        JTableHeader headerStudentList = tableCheckInResult.getTableHeader();
        headerStudentList.setBackground(new java.awt.Color(185,242,215));

        //Set up the combo box
        HashMap<String,String> subjectMap = SubjectDAO.getSubjectList();
        String[] subjectNameList = subjectMap.keySet().toArray(new String[0]);
        comboBoxSubject.setModel(new DefaultComboBoxModel<>(subjectNameList));
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String subjectName = Objects.requireNonNull(comboBoxSubject.getSelectedItem()).toString();
                String subjectID = subjectMap.get(subjectName);
                Integer studentID = UserSession.getUserID();
                List<LocalDate> checkInList = AttendantDAO.getCheckInResult(studentID, subjectID);
                if(checkInList.size() == 0){
                    JOptionPane.showMessageDialog(CheckInResult.this, "You have not checked in yet!","Caution",JOptionPane.ERROR_MESSAGE);
                }
                else{
                    LocalDate startDate = CalendarDAO.getStartDate(subjectID);
                    int currentWeek = getCurrentWeek(startDate);
                    String[] weekCheckIn = getCheckInWeeks(checkInList,startDate);
                    System.out.println(Arrays.toString(weekCheckIn));
                    setCheckInWeek(weekCheckIn,tableModel);
                    tableCheckInResult.setRowSelectionInterval(currentWeek-1,currentWeek-1);
                    tableCheckInResult.setValueAt(currentWeek-1+" (current week)",currentWeek-1,0);
                }

            }
        });
        comboBoxSubject.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                weekData = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"};
               data = new Object[weekData.length][2];
                for(int i = 0; i < weekData.length; i++){
                    data[i][0] = weekData[i];
                    data[i][1] = false;
                }
                tableModel.setDataVector(data,header);

            }
        });
    }

    private void createUIComponents() {
        ConfigurationDialog.installBasicConfiguration(this,"Check in result", panelCheckInResult);
    }

    public static String[] getCheckInWeeks(List<LocalDate> checkInList, LocalDate startDate){
        List<String> checkInWeeks = new ArrayList<>();
        for(int i = 0; i < checkInList.size(); i++){
            LocalDate checkInDate = checkInList.get(i);
            long dayBetween = ChronoUnit.DAYS.between(startDate,checkInDate);
            int week = (int) (dayBetween / 7) + 1;
            checkInWeeks.add(String.valueOf(week));
        }
        return checkInWeeks.toArray(new String[0]);
    }
    public static void setCheckInWeek(String[] weeks, DefaultTableModel tableModel){
        for (String s : weeks) {
            int week = Integer.parseInt(s);
            tableModel.setValueAt(true, week-1, 1);
        }
    }

    public static int getCurrentWeek(LocalDate startDate) {
        LocalDate today = LocalDate.now();
        long dayBetween = ChronoUnit.DAYS.between(startDate, today);
        int week = (int) (dayBetween / 7) + 1;
        return week;
    }
}
