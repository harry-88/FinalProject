
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.*;
import java.util.Random;

public class StudentAdmin implements ActionListener
{
    private String temp;

    private  JScrollPane quizScroll;
    private JFrame adminScreen;

    private JLabel mainTitle;
    private JLabel addStudentTitle;
    private JLabel studentName;
    private JLabel studentID;
    private JLabel studentClass;
    private JLabel studentPassword;

    private JComboBox cb;

    private JTextField studentNameField;
    private JTextField studentIDField;
    private JTextField studentClassField;
    private JTextField studentPasswordField;


    private Font mainTitleFont;
    private Font addStudentTitleFont;

    private JTable studentList;
    private JTable studentData;

    private JScrollPane studentListPane;
    private JScrollPane studentDataPane;

    private JButton studentAddRecordBtn;
    private JButton studentDeleteBtn;
    private JButton studentViewRecordBtn;
    private JButton studentSaveRecord;
    private JButton btnStudentPassword;

    private JInternalFrame studentAddRecordPanel;

    private DefaultTableModel studentListModel;
    private DefaultTableModel studentDataModel;

    private FileReader fr;
    private BufferedReader br;

    private FileWriter fw;
    private BufferedWriter bw;

    private JTabbedPane mainMenu;

    private JButton teacherDoor;

    public StudentAdmin()
    {
        studentAddRecordPanel = new JInternalFrame();
        adminScreen=new JFrame("STUDENT");

        try {
            adminScreen.setContentPane(
                    new JLabel(new ImageIcon(ImageIO.read(new File("background.jpg")))));
        } catch (IOException e) {
            e.printStackTrace();
        }

        teacherDoor = new JButton("Teacher");
        teacherDoor.setBounds(300,0,100,20);
        teacherDoor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == teacherDoor)
                {
                    adminScreen.dispose();
                    new TeacherAdmin();
                }
            }
        });

        JButton btnLogout = new JButton("Logout");
        btnLogout.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btnLogout)
                {
                    adminScreen.dispose();
                    new Login();
                }
            }
        });
        btnLogout.setBounds(1224,0,100,20);

        adminScreen.add(btnLogout);


        adminScreen.add(teacherDoor);
        adminScreen.setLayout(null);
        adminScreen.setBounds(50,30,1400,900);
        mainTitle=new JLabel("WELCOME ADMIN");
        mainTitle.setBounds(450,30,300,30);
        adminScreen.add(mainTitle);
        mainTitleFont=new Font("Arial",Font.BOLD,28);
        mainTitle.setFont(mainTitleFont);
        studentTable();
        studentComponents();




        adminScreen.setVisible(true);


    }
    public void studentTable()
    {

        studentList=new JTable();

        studentListModel = new DefaultTableModel();
        studentList.setModel(studentListModel);

        studentListModel.addColumn("Name");
        studentListModel.addColumn("ID");
        studentListModel.addColumn("Class");


        studentListPane=new JScrollPane(studentList);
        studentListPane.setBounds(60,85,400,400);

        try {
            studentFileRead();
        } catch (IOException e) {
            e.printStackTrace();
        }

        adminScreen.add(studentListPane);
    }
    public void studentComponents()
    {
        studentAddRecordBtn=new JButton("Add Record");
        studentAddRecordBtn.setBounds(485,120,120,30);
        studentAddRecordBtn.addActionListener(this);
        adminScreen.add(studentAddRecordBtn);
        studentAddRecordBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        studentDeleteBtn=new JButton("Delete Record");
        studentDeleteBtn.setBounds(485,180,120,30);
        studentDeleteBtn.addActionListener(this);
        studentDeleteBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        adminScreen.add(studentDeleteBtn);

        studentViewRecordBtn=new JButton("View Record");
        studentViewRecordBtn.setBounds(485,240,120,30);
        studentViewRecordBtn.addActionListener(this);
        studentViewRecordBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        adminScreen.add(studentViewRecordBtn);

        btnStudentPassword = new JButton("Password");
        btnStudentPassword.setBounds(485,300,120,30);
        btnStudentPassword.addActionListener(this);
        btnStudentPassword.setCursor(new Cursor(Cursor.HAND_CURSOR));
        adminScreen.add(btnStudentPassword);
    }

    public void studentAddRecord()
    {



        studentAddRecordPanel=new JInternalFrame("Add New Student");
        studentAddRecordPanel.setVisible(true);
        studentAddRecordPanel.setLayout(null);
        studentAddRecordPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder()));
        studentAddRecordPanel.setBounds(640,85,500,500);
        studentAddRecordPanel.setBackground(Color.LIGHT_GRAY);
        adminScreen.add(studentAddRecordPanel);

        addStudentTitle=new JLabel("Add New Student Record");
        addStudentTitle.setBounds(100,30,300,30);
        addStudentTitleFont=new Font("Arial",Font.BOLD,24);
        addStudentTitle.setFont(addStudentTitleFont);
        addStudentTitle.setForeground(Color.WHITE);
        studentAddRecordPanel.add(addStudentTitle);

        studentName=new JLabel("Student Name");
        studentName.setBounds(20,90,100,20);
        studentName.setForeground(Color.BLACK);
        studentAddRecordPanel.add(studentName);

        studentID=new JLabel("Student ID");
        studentID.setBounds(190,90,100,20);
        studentID.setForeground(Color.BLACK);
        studentAddRecordPanel.add(studentID);

        studentClass=new JLabel("Student Class");
        studentClass.setBounds(20,185,100,20);
        studentClass.setForeground(Color.BLACK);
        studentAddRecordPanel.add(studentClass);

        studentPassword=new JLabel("Student Password");
        studentPassword.setBounds(190,185,150,20);
        studentPassword.setForeground(Color.BLACK);
        studentAddRecordPanel.add(studentPassword);

        studentNameField=new JTextField();
        studentNameField.setBounds(20,120,120,25);
        studentAddRecordPanel.add(studentNameField);

        studentClassField=new JTextField();
        studentClassField.setBounds(20,215,120,25);

        String option[] = {"ICS-Physics","ICS-Statistics","FSC(pre-Medical)","FSC(pre-engineering)"};
        JComboBox cb = new JComboBox(option);
        temp = "ICSP" ;
        cb.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getItem() == "ICS-Statistics")
                    temp = "ICSS";
                else if(e.getItem() == "ICS-Physics")
                    temp = "ICSP";
                else if(e.getItem() == "FSC(pre-Medical)")
                    temp = "FSCM";
                else
                    temp = "FSCE";
            }
        });
        cb.setBounds(20,215,120,25);
        cb.addActionListener(this);
        studentAddRecordPanel.add(cb);

        System.out.println(cb.getSelectedItem());
        studentIDField=new JTextField();
        studentIDField.setBounds(190,120,120,25);
        studentAddRecordPanel.add(studentIDField);

        studentPasswordField=new JTextField();
        studentPasswordField.setBounds(190,215,120,25);
        Random rand = new Random();
        int i=rand.nextInt();
        if(i<0)
            i=i*(-1);
        studentPasswordField.setText(String.valueOf(i));
        studentAddRecordPanel.add(studentPasswordField);

        studentSaveRecord=new JButton("Save Record");
        studentSaveRecord.setBounds(260,290,130,40);
        studentSaveRecord.addActionListener(this);
        studentAddRecordPanel.add(studentSaveRecord);


    }

    public void studentFileRead() throws IOException {
        fr=new FileReader("Student.txt");
        br=new BufferedReader(fr);
        String line;
        Object[] token = {"","",""};
        while((line = br.readLine())!=null)
        {
            token = line.split(",");
            if(token[2].equals("FSCM"))
                token[2] = "FSC(pre-Medical)";
            else if(token[2].equals("FSCE"))
                token[2] = "FSC(pre-engeening";
            else if(token[2].equals("ICSP"))
                token[2]="ICS-Physics";
            else if(token[2].equals("ICSS"))
                token[2]="ICS-Statistics";

            studentListModel.addRow(token);
        }
    }

    public void funBtnDisplayMarks()
    {

        studentAddRecordPanel = new JInternalFrame();
        studentAddRecordPanel.setBounds(640,85,500,500);
        studentAddRecordPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.
                createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));


        int row = 0;
        row = studentList.getSelectedRow();

        JLabel studentIdLabel = new JLabel("ID :");
        studentIdLabel.setBounds(5,10,100,20);


        JLabel showStudentIdLabel = new JLabel((String) studentList.getValueAt(3,0));
        showStudentIdLabel.setBounds(70,10,100,20);
        showStudentIdLabel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
                BorderFactory.createLoweredBevelBorder()));



        JLabel studentNameLabel = new JLabel("Name : ");
        studentNameLabel.setBounds(200,10,100,20);

        JLabel showStudentNameLabel = new JLabel((String) studentList.getValueAt(studentList.getSelectedRow(),1));
        showStudentNameLabel.setBounds(270,10,100,20);
        showStudentNameLabel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLoweredBevelBorder(),
                BorderFactory.createRaisedBevelBorder()));

        JLabel studentClassLabel = new JLabel("Class :");
        studentClassLabel.setBounds(5,40,100,20);

        JLabel showStudentClassLabel = new JLabel((String) studentList.getValueAt(row,2));
        showStudentClassLabel.setBounds(70,40,100,20);
        showStudentClassLabel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLoweredBevelBorder()
                ,BorderFactory.createRaisedBevelBorder()));



        makeAssignmentTable();
        try {
            makeQuizTable();
        } catch (IOException e) {
            e.printStackTrace();
        }

        studentAddRecordPanel.setLayout(null);
        studentAddRecordPanel.add(quizScroll);
        studentAddRecordPanel.add(studentClassLabel);
        studentAddRecordPanel.add(showStudentClassLabel);
        studentAddRecordPanel.add(showStudentNameLabel);
        studentAddRecordPanel.add(studentNameLabel);
        studentAddRecordPanel.setVisible(true);
        studentAddRecordPanel.add(studentIdLabel);
        studentAddRecordPanel.add(showStudentIdLabel);
        studentAddRecordPanel.add(studentDataPane);
        adminScreen.add(studentAddRecordPanel);
    }
    public void makeAssignmentTable(){

        String row[] = {"1", "20", "30"};
        studentData = new JTable();

        studentDataPane = new JScrollPane(studentData);
        studentDataPane.setBounds(20,80,350,150);

        DefaultTableModel assignmentModel = new DefaultTableModel();
        studentData.setModel(assignmentModel);
        assignmentModel.addColumn("Assignment no");
        assignmentModel.addColumn("Obtain Marks");
        assignmentModel.addColumn("Total Marks");

        FileReader assignmentReader = null;
        BufferedReader assignmentBufferedReader = null;
        int selectedRow = studentData.getSelectedRow();
        try {
            assignmentReader = new FileReader("StudentRecord.txt");
            assignmentBufferedReader = new BufferedReader(assignmentReader);
            String line = null;
            String token[] = {"","","","","",""};
            while ((line = assignmentBufferedReader.readLine()) != null)
            {

                token = line.split(",");
                if(token[0].equals(studentList.getValueAt(studentList.getSelectedRow(),0)))
                {
                    if(token[1].equals("Assignment"))    //it check whether it is assignment or quiz
                    {
                        row[0] = token[2];
                        row[1] = token[3];
                        row[2] = token[4];
                        assignmentModel.addRow(row);
                    }
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        br = new BufferedReader(fr);


        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        studentDataPane.setVisible(true);


    }

    public void makeQuizTable () throws IOException
    {
        JTable quizTable = new JTable();

        DefaultTableModel quizModel = new DefaultTableModel();
        quizTable.setModel(quizModel);
        quizModel.addColumn("Quiz no");
        quizModel.addColumn("Obtain Marks");
        quizModel.addColumn("Total Marks");

        FileReader quizReader = new FileReader("StudentRecord.txt");
        BufferedReader quizBufferedReder = new BufferedReader(quizReader);

        String line = null;
        String []token;
        String []row = {"","",""};
        int selectedRow = studentData.getSelectedRow();
        while ((line = quizBufferedReder.readLine()) != null)
        {

            token = line.split(",");
            if(token[0].equals(studentList.getValueAt(studentList.getSelectedRow(),0)))//it check the rule number
            {
                System.out.println("in quiz");
                if(token[1].equals("Quiz"))    //it check whether it is assignment or quiz
                {System.out.println("in quiz condititon");
                    row[0] = token[2];
                    row[1] = token[3];
                    row[2] = token[4];
                    quizModel.addRow(row);
                }
            }
        }

        quizScroll = new JScrollPane(quizTable);
        quizScroll.setBounds(20,280,350,150);



    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource()==studentAddRecordBtn)
        {
            adminScreen.remove(studentAddRecordPanel);
            studentAddRecord();
        }

        else if(e.getSource() == btnStudentPassword)
        {
            if(studentList.getSelectedRow() == -1)
            {
                JOptionPane.showConfirmDialog(null,"Select any student to view password",
                        "Message",JOptionPane.PLAIN_MESSAGE);
            }
            else
            {
                String id = (String) studentList.getValueAt(studentList.getSelectedRow(),0);
                try {
                    FileReader reader = new FileReader("Login.txt");
                    BufferedReader bufferedReader = new BufferedReader(reader);
                    String line = null;
                    String token[] = null;
                    while ((line = bufferedReader.readLine())!= null)
                    {
                        token = line.split(",");
                        if(token[0].equals(id) && token[4].equals("Student"))
                        {
                            JOptionPane.showConfirmDialog(null,"ID : "+id+"\nName : "+token[2]+"\nPassword : "+token[1],
                                    "Message",JOptionPane.PLAIN_MESSAGE);
                        }
                    }

                    bufferedReader.close();
                    reader.close();
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }
        }
        else if(e.getSource()==studentDeleteBtn)
        {
            adminScreen.remove(studentAddRecordPanel);
            if(studentList.getSelectedRow() != -1)
                studentListModel.removeRow(studentList.getSelectedRow());
            else
                JOptionPane.showMessageDialog(null,"select the the row to delete");
            System.out.println("Student Delete Record button clicked");
        }
        else if(e.getSource()==studentViewRecordBtn)
        {

            adminScreen.remove(studentAddRecordPanel);
            funBtnDisplayMarks();

        }
        else if (e.getSource()==studentSaveRecord)
        {
            adminScreen.remove(studentAddRecordPanel);
            try {
                adminScreen.remove(studentAddRecordPanel);
                fw=new FileWriter("Student.txt",true);
                bw=new BufferedWriter(fw);
                String clas = studentClassField.getText();
                if(clas.equals("ICS-Physics"))
                    clas = "ICSP";
                bw.write(studentIDField.getText()+","+studentNameField.getText()+","+temp+"\n");

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            try {
                bw.close();
                fw.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            try{
                fw=new FileWriter("Login.txt",true);
                bw=new BufferedWriter(fw);
                bw.write(studentIDField.getText()+","+studentPasswordField.getText()+","+studentNameField.getText()+","+temp+","+"Student"+"\n");
            }catch(IOException e1)
            {
                e1.printStackTrace();
            }
            try{
                bw.close();
                fw.close();
            }catch(IOException e2)
            {
                e2.printStackTrace();
            }

            if(temp=="FSCE")
                temp="FSC(pre-engineering)";
            else if(temp=="FSCM")
                temp="FSC(pre-medical)";
            else if(temp=="ICSS")
                temp="ICS-statistics";
            else if(temp=="ICSP")
                temp="ICS-physics";
            String row[] = {studentIDField.getText(),studentNameField.getText(),temp};
            studentListModel.addRow(row);

        }
    }
}
