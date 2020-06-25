import javax.imageio.ImageIO;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.*;

public class Teacher implements ActionListener{
    private JFrame window; //it is the main window

    private JLabel nameLabel;   //it display the name label in rightpane
    private JLabel showNameLabel; //it show the name of the teacher in leftPab
    private JLabel idLabel;  //it display the id label in leftPab
    private JLabel showIdLabel; //it show the the if of teacher in leftPab
    private JLabel teacherSubjectLabel; //it display the teacher subject label in leftPab.
    private JLabel showTeacherSubjectLabel; //it show the subject that the teacher is teach
    private JLabel hiUserLabel; //it show the hi message to the window
    private JLabel studentNameLabel;    //it display the word "name" of student in the right pan
    private JLabel showStudentNameLabel;    //it show the name of student in which user is select from table.
    private JLabel studentIdLabel;      //it display the word Id in the right pan.
    private JLabel showStudentIdLabel;      //it show the Id of student that was selected.
    private JLabel studentClassLabel;       //it display the word class on right pan
    private JLabel showStudentClassLabel;   //it show the class of student that was selected from the table.
    private JLabel studentSubjectLabel;     //it display the word subject in pan
    private JLabel showStudentSubjectLabel;    //it show the subject that's marks is add.
    private JLabel assignmentNoLabel;     //it show the assignment no that's marks is adding.
    private JLabel assignmentObtainMarksLabel;    //it show the obtain marks label
    private JLabel assignmentTotalMarksLabel; //it show the total marks label.
    private JLabel quizNoLabel; //it show the quiz no label .
    private JLabel quizObtainMarksLabel;//it show the obtain marks label
    private JLabel quizTotalMarksLabel;//it show the total marks label.

    private JTextField searchField; //it help us to short the data in table
    private JTextField assignmentObtainMarksField; //it help us to enter obtain marks of the students.
    private JTextField assignmenttotalMarksField; //it help us to enter total marks
    private JTextField quizObtainMarksField;//it help us to enter obtain marks of the quiz.
    private JTextField quizTotalMarksField;      //it help us to enter total marks of the quiz.
    private JTextField assignmentNoField;
    private JTextField quizNoField;

    private JTable tableStudent;    //it display the student the the teacher is teach.
    private JTable quizTable;   //it display the quizes of student.
    private JTable assignmentTable; //it display the assignment of student.
    private JTable getQuizTable;    //it display the quiz of student

    private JInternalFrame displayStudentRecordFrame;
    private JInternalFrame addDataFrsame;    //in it i will take the data from the user.
    private JInternalFrame removeFrame;


    private JScrollPane StudentTableScroll; //it contain the table in which all students will show
    private JScrollPane assignmentScroll;   //it contain the table of assignment.
    private JScrollPane quizScroll; //it contain the quiz.

    private JButton btnDisplayMarks;    //it display the record of student
    private JButton btnAddMarks;        //it add the assignment or quiz for the selected student
    private JButton btnDeleteMarks;     //it delete the assignment or quiz of the selected student
    private JButton btnRemoveAssignment;    //it will remove the assignments of the students
    private JButton btnRemoveQuiz;      //it will remove quiz of the students
    private JButton btnAddAssignment;   //it add the assignment data in file
    private JButton btnAddQuiz; //it add the quiz data in file
    private JButton btnLogout;

    private String teacherName;     //it store the teacher name that is logged in
    private String teacherId;       //it store the teacher id
    private String Subject;         //it store the subject of teacher that he will teach.

    private DefaultTableModel studentTableModel;//it is the model of table that contain students
    private DefaultTableModel assignmentModel;  //it is the model of that table display the assignment.
    private DefaultTableModel quizModel;        //it is the model of the table that display the quiz.

    private FileReader fr;  //it for file reading
    private FileReader assignmentReader;//it read the data that contain assignment
    private FileReader quizReader;  //it for quiz reader

    private BufferedReader assignmentBufferedReader;    //it read data from assignment file
    private BufferedReader br;  //it for buffer reading
    private BufferedReader quizBufferedReder;   //it  read data for quiz

    private FileWriter assignmentWritter;

    private BufferedWriter assignmentBuffer;
    private TableRowSorter shorter; //it will short student table


    public Teacher(String name,String Id,String subject)
    {
        teacherName = name;
        teacherId = Id;
        Subject = subject;

        displayStudentRecordFrame = new JInternalFrame();
        removeFrame = new JInternalFrame();
        addDataFrsame = new JInternalFrame();
        window = new JFrame("Teacher Portal");
        window.setDefaultCloseOperation(window.EXIT_ON_CLOSE);

        try {
            window.setContentPane(
                    new JLabel(new ImageIcon(ImageIO.read(new File("background.jpg")))));
        } catch (IOException e) {
            e.printStackTrace();
        };
        window.setBounds(0,0,1424,900);

        btnLogout = new JButton("Logout");

        btnLogout.setBounds(1200,20,100,20);
        btnLogout.setCursor(new Cursor(Cursor.HAND_CURSOR));

        window.setLayout(null);

        window.validate();
        btnLogout.addActionListener(this);

        init();
        makeTable();


        window.add(btnLogout);
        window.setVisible(true);

    }

    public void init()
    {



        nameLabel = new JLabel("Name :");
        nameLabel.setBounds(5,160,100,20);
        nameLabel.setForeground(Color.white);

        showNameLabel = new JLabel(teacherName);
        showNameLabel.setBounds(30,180,150,20);
        showNameLabel.setForeground(Color.white);
        showNameLabel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.
                createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));


        idLabel = new JLabel("ID");
        idLabel.setBounds(5,200,100,20);
        idLabel.setForeground(Color.white);

        showIdLabel = new JLabel(teacherId);
        showIdLabel.setBounds(30,220,150,20);
        showIdLabel.setForeground(Color.white);
        showIdLabel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.
                createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));


        teacherSubjectLabel = new JLabel("subject : ");
        teacherSubjectLabel.setBounds(5,240,100,20);
        teacherSubjectLabel.setForeground(Color.white);

        showTeacherSubjectLabel = new JLabel(Subject);
        showTeacherSubjectLabel.setBounds(30,260,150,20);
        showTeacherSubjectLabel.setForeground(Color.white);
        showTeacherSubjectLabel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.
                createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));

        window.add(showTeacherSubjectLabel);
        window.add(teacherSubjectLabel);
        window.add(showIdLabel);
        window.add(idLabel,null);
        window.add(nameLabel,null);
        window.add(showNameLabel);



    }

    public void makeTable()
    {
        tableStudent = new JTable();

        studentTableModel = new DefaultTableModel();
        tableStudent.setModel(studentTableModel);

        studentTableModel.addColumn("ID");
        studentTableModel.addColumn("Name");
        studentTableModel.addColumn("Class");

        String []row = {"none","not assigned","none"};

        try {
            fr = new FileReader("Student.txt");
            br = new BufferedReader(fr);
            String line;
            String stId;
            String stName;
            String stClass;

            String []token;
            while ((line = br.readLine()) != null) {
                token = line.split(",");

                stId = token[0];
                stName = token[1];
                stClass = token[2];

                if(( Subject.equals("English") || Subject.equals("Urdu") ||
                        Subject.equals("Islamiat") || Subject.equals("Pak-Study")) && (stClass.equals("FA") ||stClass.equals("FSCE") || stClass.equals("ICSP") || stClass.equals("ICSS") || stClass.equals("FSCM")))
                {
                    System.out.println("in function");

                    row[0] = stId;
                    row[1] = stName;
                    if(stClass.equals("FSCE"))
                        row[2] = "FSC(pre-engineering)";
                    else if(stClass.equals("ICSP"))
                        row[2] = "ICS-Physics";
                    else if(stClass.equals("ICSS"))
                        row[2] = "ICS-Statistics";
                    else if(stClass.equals("FA"))
                        row[2] = "FA";
                    else if(stClass.equals("FSCM"))
                        row[2] = "FSC(pre-medical)";
                    studentTableModel.addRow(row);
                }

                else if((Subject.equals("Statistics") || Subject.equals("Computer") ) && (stClass.equals("ICSS") || stClass.equals("ICSP")))
                {
                    row[0] = stId;
                    row[1] = stName;
                    if(stClass.equals("ICSS"))
                        row[2] = "ICS-Statistics";
                    else if(Subject.equals("Computer") || Subject.equals("Math"))
                        row[2] = "ICS-Physics";
                    studentTableModel.addRow(row);
                }
                else if(Subject.equals("Biology") && (stClass.equals("FSCM")))
                {
                    row[0] = stId;
                    row[1] = stName;
                    row[2] = "FSC(pre-Medical)";
                    studentTableModel.addRow(row);
                }
                else if((Subject.equals("Physics")) && (stClass.equals("ICSP") || stClass.equals("FSCM") || stClass.equals("FSCE")))
                {
                    row[0] = stId;
                    row[1] = stName;
                    if(stClass.equals("ICSP"))
                        row[2] = "ICS-Physics";
                    else if(stClass.equals("FSCM"))
                        row[2] = "FSC(pre-Medical)";
                    else if(stClass.equals("FSCE"))
                        row[2] = "FSC(pre-engineering)";
                    studentTableModel.addRow(row);
                }
                else if((Subject.equals("Math")) && (stClass.equals("FSCE") || stClass.equals("ICSS") || stClass.equals("ICSP")))
                {
                    row[0] = stId;
                    row[1] = stName;
                    if(stClass.equals("ICSP"))
                        row[2] = "ICS-Physics";
                    else if(stClass.equals("ICSS"))
                        row[2] = "ICS Statistics";
                    else if(stClass.equals("FSCE"))
                        row[2] = "FSC(pre-engineering)";
                    studentTableModel.addRow(row);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try{
                br.close();
                fr.close();
            }catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        hiUserLabel = new JLabel("hi "+teacherName);
        hiUserLabel.setForeground(Color.white);
        hiUserLabel.setBounds(500,100,200,50);
        hiUserLabel.setFont(new Font("Arial",Font.ROMAN_BASELINE,35));

        StudentTableScroll = new JScrollPane(tableStudent);
        StudentTableScroll.setBounds(250,300,300,300);


        searchField = new JTextField("Search");
        searchField.setBounds(450,280,100,20);

        shorter = new TableRowSorter(studentTableModel);
        tableStudent.setRowSorter(shorter);
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = searchField.getText().trim();
                if(text.length() == 0)
                    shorter.setRowFilter(null);
                else
                    shorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = searchField.getText().trim();
                if(text.length() == 0)
                    shorter.setRowFilter(null);
                else
                    shorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        btnDisplayMarks = new JButton("Display");
        btnDisplayMarks.setBounds(555,320,100,20);
        btnDisplayMarks.addActionListener(this);
        Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
        btnDisplayMarks.setCursor(cursor);

        btnAddMarks = new JButton("Add");
        btnAddMarks.setBounds(555,321 + 20,100,20);
        btnAddMarks.addActionListener(this);
        btnAddMarks.setCursor(cursor);

        btnDeleteMarks = new JButton("remove");
        btnDeleteMarks.setBounds(555,342 + 20,100,20);
        btnDeleteMarks.addActionListener(this);
        btnDeleteMarks.setCursor(cursor);

        window.add(btnDeleteMarks);
        window.add(hiUserLabel);
        window.add(btnAddMarks);
        window.add(btnDisplayMarks);
        window.add(StudentTableScroll);
        window.add(searchField);
    }

    public void funBtnDisplayMarks()
    {

        displayStudentRecordFrame = new JInternalFrame();
        displayStudentRecordFrame.setBounds(700,150,400,500);
        displayStudentRecordFrame.setBorder(BorderFactory.createCompoundBorder(BorderFactory.
                createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));


        int row = tableStudent.getSelectedRow();

        studentIdLabel = new JLabel("ID :");
        studentIdLabel.setBounds(5,10,100,20);


        showStudentIdLabel = new JLabel((String) tableStudent.getValueAt(row,0));
        showStudentIdLabel.setBounds(70,10,100,20);
        showStudentIdLabel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
                BorderFactory.createLoweredBevelBorder()));



        studentNameLabel = new JLabel("Name : ");
        studentNameLabel.setBounds(200,10,100,20);

        showStudentNameLabel = new JLabel((String) tableStudent.getValueAt(row,1));
        showStudentNameLabel.setBounds(270,10,100,20);
        showStudentNameLabel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLoweredBevelBorder(),
                                    BorderFactory.createRaisedBevelBorder()));

        studentClassLabel = new JLabel("Class :");
        studentClassLabel.setBounds(5,40,100,20);

        showStudentClassLabel = new JLabel((String) tableStudent.getValueAt(row,2));
        showStudentClassLabel.setBounds(70,40,100,20);
        showStudentClassLabel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLoweredBevelBorder()
                ,BorderFactory.createRaisedBevelBorder()));

        studentSubjectLabel = new JLabel("Subject :");
        studentSubjectLabel.setBounds(200,40,100,20);


        showStudentSubjectLabel = new JLabel(Subject);
        showStudentSubjectLabel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLoweredBevelBorder()
                    ,BorderFactory.createRaisedBevelBorder()));
        showStudentSubjectLabel.setBounds(270,40,100,20);

        makeAssignmentTable();
        try {
            makeQuizTable();
        } catch (IOException e) {
            e.printStackTrace();
        }

        displayStudentRecordFrame.setLayout(null);
        displayStudentRecordFrame.add(quizScroll);
        displayStudentRecordFrame.add(studentSubjectLabel);
        displayStudentRecordFrame.add(showStudentSubjectLabel);
        displayStudentRecordFrame.add(studentClassLabel);
        displayStudentRecordFrame.add(showStudentClassLabel);
        displayStudentRecordFrame.add(showStudentNameLabel);
        displayStudentRecordFrame.add(studentNameLabel);
        displayStudentRecordFrame.setVisible(true);
        displayStudentRecordFrame.add(studentIdLabel);
        displayStudentRecordFrame.add(showStudentIdLabel);
        displayStudentRecordFrame.add(assignmentScroll);
        window.add(displayStudentRecordFrame);
    }

    public void makeAssignmentTable(){

        String row[] = {"1", "20", "30","20"};
        assignmentTable = new JTable();

        assignmentScroll = new JScrollPane(assignmentTable);
        assignmentScroll.setBounds(20,80,350,150);

        assignmentModel = new DefaultTableModel();
        assignmentTable.setModel(assignmentModel);
        assignmentModel.addColumn("Assignment no");
        assignmentModel.addColumn("Subject");
        assignmentModel.addColumn("Obtain Marks");
        assignmentModel.addColumn("Total Marks");

        int selectedRow = tableStudent.getSelectedRow();
        try {
            assignmentReader = new FileReader("StudentRecord.txt");
            assignmentBufferedReader = new BufferedReader(assignmentReader);
            String line;
            String token[];
            while ((line = assignmentBufferedReader.readLine()) != null)
            {

                token = line.split(",");
                if(token[0].equals(tableStudent.getValueAt(selectedRow,0)))//it check the rule number
                {
                    if(token[1].equals("Assignment") && token[5].equals(Subject))    //it check whether it is assignment or quiz
                    {
                        row[0] = token[2];
                        row[1] = token[5];
                        row[2] = token[3];
                        row[3] = token[4];
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


        assignmentScroll.setVisible(true);

    }

    public void makeQuizTable () throws IOException
    {
        quizTable = new JTable();

        quizModel = new DefaultTableModel();
        quizTable.setModel(quizModel);
        quizModel.addColumn("Quiz no");
        quizModel.addColumn("Subject");
        quizModel.addColumn("Obtain Marks");
        quizModel.addColumn("Total Marks");

        quizReader = new FileReader("StudentRecord.txt");
        quizBufferedReder = new BufferedReader(quizReader);

        String line = null;
        String []token;
        String []row = {"","","",""};
        int selectedRow = tableStudent.getSelectedRow();
        while ((line = quizBufferedReder.readLine()) != null)
        {

            token = line.split(",");
            if(token[0].equals(tableStudent.getValueAt(selectedRow,0)))//it check the rule number
            {
                if(token[1].equals("Quiz") && token[5].equals(Subject))    //it check whether it is assignment or quiz
                {
                    row[0] = token[2];
                    row[1] = token[5];
                    row[2] = token[3];
                    row[3] = token[4];
                    quizModel.addRow(row);
                }
            }
        }

        quizScroll = new JScrollPane(quizTable);
        quizScroll.setBounds(20,280,350,150);



    }

    public void AddData() throws IOException {

        addDataFrsame = new JInternalFrame();
        addDataFrsame.setBounds(700,150,400,500);
        addDataFrsame.setBorder(BorderFactory.createCompoundBorder(BorderFactory.
                createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
        addDataFrsame.setLayout(null);
        int row = tableStudent.getSelectedRow();

        studentIdLabel = new JLabel("ID :");
        studentIdLabel.setBounds(5,10,100,20);


        showStudentIdLabel = new JLabel();
        showStudentIdLabel.setText((String)tableStudent.getValueAt(row,0));
        showStudentIdLabel.setBounds(70,10,100,20);
        showStudentIdLabel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
                BorderFactory.createLoweredBevelBorder()));



        studentNameLabel = new JLabel("Name : ");
        studentNameLabel.setBounds(200,10,100,20);

        showStudentNameLabel = new JLabel((String) tableStudent.getValueAt(row,1));
        showStudentNameLabel.setBounds(270,10,100,20);
        showStudentNameLabel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLoweredBevelBorder(),
                BorderFactory.createRaisedBevelBorder()));

        studentClassLabel = new JLabel("Class :");
        studentClassLabel.setBounds(5,40,100,20);

        showStudentClassLabel = new JLabel((String) tableStudent.getValueAt(row,2));
        showStudentClassLabel.setBounds(70,40,100,20);
        showStudentClassLabel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLoweredBevelBorder()
                ,BorderFactory.createRaisedBevelBorder()));

        studentSubjectLabel = new JLabel("Subject :");
        studentSubjectLabel.setBounds(200,40,100,20);


        showStudentSubjectLabel = new JLabel(Subject);
        showStudentSubjectLabel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLoweredBevelBorder()
                ,BorderFactory.createRaisedBevelBorder()));
        showStudentSubjectLabel.setBounds(270,40,100,20);

        String stId = (String) tableStudent.getValueAt(tableStudent.getSelectedRow(),0);


        assignmentReader = new FileReader("StudentRecord.txt");
        assignmentBufferedReader = new BufferedReader(assignmentReader);
        int assignment = 0;
        int quiz = 0;

        String line = "";
        String token[];
        while((line = assignmentBufferedReader.readLine()) != null)
        {
            token = line.split(",");
            if(stId.equals(token[0]))
            {

                if(token[1].equals("Assignment")) {
                    assignment += 1;
                } else
                    quiz += 1;
            }
        }

        String assignmentCounter = ""+(assignment + 1);
        String quizCounter = ""+(quiz + 1);
        assignmentNoLabel = new JLabel("assignment No :");
        assignmentNoLabel.setBounds(5,80,100,20);

        assignmentNoField = new JTextField(assignmentCounter);
        assignmentNoField.setBounds(110,80,20,20);

        assignmentObtainMarksLabel = new JLabel("Obtain Marks:");
        assignmentObtainMarksLabel.setBounds(5,110,100,20);

        assignmentObtainMarksField = new JTextField();
        assignmentObtainMarksField.setBounds(110,110,40,20);

        assignmentTotalMarksLabel = new JLabel("Total Marks");
        assignmentTotalMarksLabel.setBounds(5,140,100,20);

        assignmenttotalMarksField = new JTextField();
        assignmenttotalMarksField.setBounds(110,140,40,20);

        btnAddAssignment = new JButton("ADD");
        btnAddAssignment.setBounds(50,170,100,20);
        btnAddAssignment.addActionListener(this);
        Cursor c = new Cursor(Cursor.HAND_CURSOR);
        btnAddAssignment.setCursor(c);

        quizNoLabel = new JLabel("Quiz No :");
        quizNoLabel.setBounds(5,80 + 200,100,20);

        quizNoField = new JTextField( quizCounter);
        quizNoField.setBounds(110,80+ 200,20,20);

        quizObtainMarksLabel = new JLabel("Obtain Marks:");
        quizObtainMarksLabel.setBounds(5,110+ 200,100,20);

        quizObtainMarksField = new JTextField();
        quizObtainMarksField.setBounds(110,110+ 200,40,20);

        quizTotalMarksLabel = new JLabel("Total Marks");
        quizTotalMarksLabel.setBounds(5,140+ 200,100,20);

        quizTotalMarksField = new JTextField();
        quizTotalMarksField.setBounds(110,140+ 200,40,20);

        btnAddQuiz = new JButton("ADD");
        btnAddQuiz.setBounds(50,170+ 200,100,20);
        btnAddQuiz.addActionListener(this);
        Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
        btnAddQuiz.setCursor(cursor);



        addDataFrsame.add(quizNoField);
        addDataFrsame.add(quizNoLabel);
        addDataFrsame.add(quizObtainMarksField);
        addDataFrsame.add(quizObtainMarksLabel);
        addDataFrsame.add(quizTotalMarksField);
        addDataFrsame.add(quizTotalMarksLabel);
        addDataFrsame.add(btnAddQuiz);
        addDataFrsame.add(btnAddAssignment);
        addDataFrsame.add(assignmenttotalMarksField);
        addDataFrsame.add(assignmentTotalMarksLabel);
        addDataFrsame.add(assignmentObtainMarksField);
        addDataFrsame.add(assignmentObtainMarksLabel);
        addDataFrsame.add(assignmentNoField);
        addDataFrsame.add(assignmentNoLabel);
        addDataFrsame.add(studentSubjectLabel);
        addDataFrsame.add(showStudentSubjectLabel);
        addDataFrsame.add(studentClassLabel);
        addDataFrsame.add(showStudentClassLabel);
        addDataFrsame.add(showStudentNameLabel);
        addDataFrsame.add(studentNameLabel);
        addDataFrsame.setVisible(true);
        addDataFrsame.add(studentIdLabel);
        addDataFrsame.add(showStudentIdLabel);

        window.add(addDataFrsame);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAddMarks) {
            if (tableStudent.getSelectedRow() == -1)
                JOptionPane.showConfirmDialog(null, "select any student for add data of student",
                        "Check it", JOptionPane.PLAIN_MESSAGE);
            else {
                try {
                    window.remove(displayStudentRecordFrame);
                    window.remove(addDataFrsame);
                    window.remove(removeFrame);
                    AddData();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }

        else if(e.getSource() == btnLogout)
        {
            window.dispose();
            new Login();
        }
        else if (e.getSource() == btnDeleteMarks) {
            if (tableStudent.getSelectedRow() == -1)
                JOptionPane.showConfirmDialog(null, "select any student to delete the data of student",
                        "Check it", JOptionPane.PLAIN_MESSAGE);
            else {

                window.remove(displayStudentRecordFrame);
                window.remove(addDataFrsame);
                window.remove(removeFrame);
                funDelete();


            }
        }

        else if (e.getSource() == btnDisplayMarks) {
            if (tableStudent.getSelectedRow() == -1)
                JOptionPane.showConfirmDialog(null, "select any student for display data of student",
                        "Check it", JOptionPane.PLAIN_MESSAGE);
            else {
                window.remove(displayStudentRecordFrame);
                window.remove(addDataFrsame);
                window.remove(removeFrame);
                funBtnDisplayMarks();
            }
        }

        else if (e.getSource() == btnRemoveAssignment) {
            if (assignmentTable.getSelectedRow() == -1)
                JOptionPane.showConfirmDialog(null, "select any assignment of student for display data of student",
                        "Check it", JOptionPane.PLAIN_MESSAGE);
            else {
                String assignmentNo = (String) assignmentModel.getValueAt(assignmentTable.getSelectedRow(), 0);
                String stId = showStudentIdLabel.getText();
                assignmentModel.removeRow(assignmentTable.getSelectedRow());
                JOptionPane.showConfirmDialog(null,"Remove successfully ","Message",JOptionPane.PLAIN_MESSAGE);
                try {
                    assignmentReader = new FileReader("StudentRecord.txt");
                    assignmentBufferedReader = new BufferedReader(assignmentReader);

                    assignmentWritter = new FileWriter("Temp.txt", true);
                    assignmentBuffer = new BufferedWriter(assignmentWritter);

                    String line;
                    String token[];
                    while ((line = assignmentBufferedReader.readLine()) != null) {
                        token = line.split(",");
                        if (!(stId.equals(token[0]) && token[1].equals("Assignment") && assignmentNo.equals(token[2]))) {
                            assignmentWritter.write(line + "\n");
                        }
                    }
                }catch (IOException e1)
                {
                    e1.printStackTrace();
                }

                try {
                    assignmentBufferedReader.close();
                    assignmentReader.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

                try
                {
                    assignmentBuffer.close();
                    assignmentWritter.close();

                }catch (IOException e2) {
                    e2.printStackTrace();
                }

                File file = new File("StudentRecord.txt");
                File fileForRename = new File("Temp.txt");
                fileForRename.renameTo(file);
                if(file.exists())
                    file.delete();

            }
        }

        else if (e.getSource() == btnRemoveQuiz) {
            if (quizTable.getSelectedRow() == -1)
                JOptionPane.showConfirmDialog(null, "select any quiz of student for display data of student",
                        "Check it", JOptionPane.PLAIN_MESSAGE);
            else
            {
                String QuizNo = (String) quizModel.getValueAt(quizTable.getSelectedRow(), 0);
                String stId = showStudentIdLabel.getText();
                quizModel.removeRow(quizTable.getSelectedRow());
                JOptionPane.showConfirmDialog(null,"Remove successfully ","Message",JOptionPane.PLAIN_MESSAGE);
                try {
                    assignmentReader = new FileReader("StudentRecord.txt");
                    assignmentBufferedReader = new BufferedReader(assignmentReader);

                    assignmentWritter = new FileWriter("Temp.txt", true);
                    assignmentBuffer = new BufferedWriter(assignmentWritter);

                    String line;
                    String token[];
                    while ((line = assignmentBufferedReader.readLine()) != null) {
                        token = line.split(",");
                        if (!(stId.equals(token[0]) && token[1].equals("Quiz") && QuizNo.equals(token[2]))) {
                            assignmentWritter.write(line + "\n");
                        }
                    }
                }catch (IOException e1)
                {
                    e1.printStackTrace();
                }
                try {
                    assignmentBufferedReader.close();
                    assignmentReader.close();
                    assignmentBuffer.getClass();
                    assignmentWritter.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                File file = new File("StudentRecord.txt");
                file.delete();
                File forRename = new File("Temp.txt");
                forRename.renameTo(new File("StudentRecord.txt"));


            }
        }

        else if (e.getSource() == btnAddAssignment) {
            assignmentWritter = null;
            assignmentBuffer = null;
            int o = Integer.parseInt(assignmentObtainMarksField.getText());
            int t = Integer.parseInt(assignmenttotalMarksField.getText());
            assignmentWritter = null;
            assignmentBuffer = null;
                if(assignmentObtainMarksField.getText().trim().isEmpty() || assignmenttotalMarksField.getText().trim().isEmpty())
                    JOptionPane.showConfirmDialog(null,"All fields are requied","Message", JOptionPane.PLAIN_MESSAGE);
                else if(o>t)
                {
                    JOptionPane.showConfirmDialog(null,"your obtain marks is greater then total marks. which is not valid.","Message",JOptionPane.PLAIN_MESSAGE);
                }
                else
                {

                    try {
                        assignmentWritter = new FileWriter("StudentRecord.txt", true);
                        assignmentBuffer = new BufferedWriter(assignmentWritter);
                        assignmentBuffer.write(tableStudent.getValueAt(tableStudent.getSelectedRow(), 0) + "," + "Assignment," + assignmentNoField.getText()
                                + "," + assignmentObtainMarksField.getText() + "," + assignmenttotalMarksField.getText() + ","+Subject+"\n");
                        JOptionPane.showConfirmDialog(null, "assignment added", "Message", JOptionPane.PLAIN_MESSAGE);

                        assignmentBuffer.close();
                        assignmentWritter.close();

                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }

            try {
                assignmentBuffer.close();
                assignmentWritter.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        }

        else if (e.getSource() == btnAddQuiz) {
            if(!(quizObtainMarksField.getText().trim().isEmpty() || quizTotalMarksField.getText().trim().isEmpty())) {
                try {
                    assignmentWritter = new FileWriter("StudentRecord.txt",true);
                    assignmentBuffer = new BufferedWriter(assignmentWritter);
                    assignmentBuffer.write(tableStudent.getValueAt(tableStudent.getSelectedRow(), 0) + ",Quiz," + quizNoField.getText()
                            + "," + quizObtainMarksField.getText() + "," + quizTotalMarksField.getText() +","+Subject+ "\n");

                    assignmentBuffer.close();
                    assignmentWritter.close();

                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                JOptionPane.showConfirmDialog(null, "quiz added", "Message", JOptionPane.PLAIN_MESSAGE);
            }
            else
            {
                JOptionPane.showConfirmDialog(null,"all fields are requid","Message",JOptionPane.PLAIN_MESSAGE);
            }
        }
    }

    private void funDelete() {

        removeFrame = new JInternalFrame();
        removeFrame.setBounds(700,150,400,500);
        removeFrame.setBorder(BorderFactory.createCompoundBorder(BorderFactory.
                createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));


            int row = tableStudent.getSelectedRow();

            studentIdLabel = new JLabel("ID :");
            studentIdLabel.setBounds(5,10,100,20);


            showStudentIdLabel = new JLabel((String) tableStudent.getValueAt(row,0));
            showStudentIdLabel.setBounds(70,10,100,20);
            showStudentIdLabel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
                    BorderFactory.createLoweredBevelBorder()));



            studentNameLabel = new JLabel("Name : ");
            studentNameLabel.setBounds(200,10,100,20);

            showStudentNameLabel = new JLabel((String) tableStudent.getValueAt(row,1));
            showStudentNameLabel.setBounds(270,10,100,20);
            showStudentNameLabel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLoweredBevelBorder(),
                    BorderFactory.createRaisedBevelBorder()));

            studentClassLabel = new JLabel("Class :");
            studentClassLabel.setBounds(5,40,100,20);

            showStudentClassLabel = new JLabel((String) tableStudent.getValueAt(row,2));
            showStudentClassLabel.setBounds(70,40,100,20);
            showStudentClassLabel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLoweredBevelBorder()
                    ,BorderFactory.createRaisedBevelBorder()));

            studentSubjectLabel = new JLabel("Subject :");
            studentSubjectLabel.setBounds(200,40,100,20);


            showStudentSubjectLabel = new JLabel(Subject);
            showStudentSubjectLabel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLoweredBevelBorder()
                    ,BorderFactory.createRaisedBevelBorder()));
            showStudentSubjectLabel.setBounds(270,40,100,20);

            makeAssignmentTable();
            try {
                makeQuizTable();
            } catch (IOException e) {
                e.printStackTrace();
            }

            removeFrame.setLayout(null);

            removeFrame.add(assignmentScroll);
            removeFrame.add(studentSubjectLabel);
            removeFrame.add(showStudentSubjectLabel);
            removeFrame.add(studentClassLabel);
            removeFrame.add(showStudentClassLabel);
            removeFrame.add(showStudentNameLabel);
            removeFrame.add(studentNameLabel);
            removeFrame.setVisible(true);
            removeFrame.add(studentIdLabel);
            removeFrame.add(showStudentIdLabel);
            removeFrame.add(quizScroll);
            window.add(removeFrame);


        btnRemoveAssignment = new JButton("Remove");
        btnRemoveAssignment.setBounds(250, 150 + 80, 100, 20);
        btnRemoveAssignment.addActionListener(this);
        Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
        btnRemoveAssignment.setCursor(cursor);

        btnRemoveQuiz = new JButton("Remove");
        btnRemoveQuiz.setBounds(250, 150 + 280, 100, 20);
        btnRemoveQuiz.addActionListener(this);
        btnRemoveQuiz.setCursor(cursor);

        removeFrame.add(btnRemoveQuiz);
        removeFrame.add(btnRemoveAssignment);

    }
}
