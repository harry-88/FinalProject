import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Student implements ActionListener {
    private JFrame window;

    private String studentName;
    private String studentId;
    private String studentClass;

    private int assignmentCounter;
    private int quizCounter;

    private JInternalFrame markFrame;

    private JTable courseTable;
    private JTable assignmentMarksTable;
    private JTable quizMarksTable;

    private JScrollPane courseScroll;
    private JScrollPane assignmentMarksScroll;
    private JScrollPane quizMarksScroll;

    private JButton btnViewMarks;
    private JButton btnLogout;

    private JLabel nameLabel;
    private JLabel showNameLabel;
    private JLabel idLabel;
    private JLabel showIdLabel;
    private JLabel classLabel;
    private JLabel showClassLabel;

    public Student(String name,String id,String clas)
    {
        studentName = name;
        studentId = id;
        studentClass = clas;

        window = new JFrame("Student Portal");

        try {
            window.setContentPane(
                    new JLabel(new ImageIcon(ImageIO.read(new File("background.jpg")))));
        } catch (IOException e) {
            e.printStackTrace();
        };

        window.setBounds(0,0,1424,900);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(null);


        btnLogout = new JButton("Logout");
        btnLogout.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btnLogout)
                {
                    window.dispose();
                    new Login();
                }
            }
        });
        btnLogout.setBounds(1224,0,100,20);

        window.add(btnLogout);

        init();
        makeSubjectTable();

        window.setVisible(true);




    }

    public void init()
    {
        idLabel = new JLabel("ID : ");
        idLabel.setBounds(50,100,100,20);
        idLabel.setForeground(Color.white);

        showIdLabel = new JLabel(studentId);
        showIdLabel.setBounds(120,100,100,20);
        showIdLabel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder()
                ,BorderFactory.createLoweredBevelBorder()));
        showIdLabel.setForeground(Color.white);

        nameLabel  = new JLabel("Name : ");
        nameLabel.setBounds(50,120,100,20);
        nameLabel.setForeground(Color.white);

        showNameLabel = new JLabel(studentName);
        showNameLabel.setForeground(Color.white);
        showNameLabel.setBounds(120,120,100,20);
        showNameLabel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder()
                ,BorderFactory.createLoweredBevelBorder()));

        classLabel = new JLabel("Class");
        classLabel.setForeground(Color.white);
        classLabel.setBounds(50,140,100,20);

        if(studentClass.equals("ICSP"))
            studentClass = "ICS-Physics";
        else if(studentClass.equals("ICSS"))
            studentClass = "ICS-Statistics";
        else if(studentClass.equals("FSCM"))
            studentClass = "FSC(pre-Medical)";
        else if(studentClass.equals("FSCE"))
            studentClass = "FSC(pre-Engenring)";



        showClassLabel = new JLabel(studentClass);
        showClassLabel.setForeground(Color.white);
        showClassLabel.setBounds(120,140,100,20);
        showClassLabel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder()
                ,BorderFactory.createLoweredBevelBorder()));



        window.add(classLabel);
        window.add(showClassLabel);
        window.add(showNameLabel);
        window.add(showIdLabel);
        window.add(idLabel);
        window.add(nameLabel);
    }

    public void makeSubjectTable()
    {
        Object col[] = {"Subjects"};
        Object row[][] = null;
        if(studentClass == "ICS-Physics")
            row = new Object[][]{{"English",}, {"Urdu"}, {"Math"}, {"Computer"}, {"Physics"},{"Pak-Study"}};
        else if(studentClass == "ICS-Statistics")
            row = new Object[][]{{"English",}, {"Urdu"}, {"Math"}, {"Computer"}, {"Statistics"},{"Pak-Study"}};
        else if(studentClass == "FSC(pre-Medical)")
            row = new Object[][]{{"English",}, {"Urdu"}, {"Biology"}, {"Chemistry"}, {"Physics"},{"Pak-Study"}};
        else if(studentClass == "FSC(pre-Engenring)")
            row = new Object[][]{{"English",}, {"Urdu"}, {"Math"}, {"Chemistry"}, {"Physics"},{"Pak-Study"}};

        courseTable = new JTable(row,col);

        courseScroll = new JScrollPane(courseTable);
        courseScroll.setBounds(300,200,150,150);

        btnViewMarks = new JButton("View");
        btnViewMarks.setBounds(460,250,80,20);
        btnViewMarks.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnViewMarks.addActionListener(this);

        window.add(btnViewMarks);
        window.add(courseScroll);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnViewMarks)
        {
            if(courseTable.getSelectedRow() == -1)
                JOptionPane.showConfirmDialog(null,"Select any Subject to view marks","Message",JOptionPane.PLAIN_MESSAGE);
            else
            {
                assignmentMarksTable = new JTable();

                markFrame = new JInternalFrame();
                markFrame.setBounds(600, 100, 400, 600);
                markFrame.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
                        BorderFactory.createLoweredBevelBorder()));
                markFrame.setVisible(true);
                markFrame.setLayout(null);

                DefaultTableModel assignmentModel = new DefaultTableModel();
                assignmentMarksTable.setModel(assignmentModel);

                assignmentModel.addColumn("Assignment no");
                assignmentModel.addColumn("Obtain marks");
                assignmentModel.addColumn("Total marks");

                FileReader reader = null;
                BufferedReader bufferedReader = null;


                assignmentMarksScroll = new JScrollPane(assignmentMarksTable);
                assignmentMarksScroll.setBounds(50, 50, 300, 200);

                quizMarksTable = new JTable();

                DefaultTableModel quizModel = new DefaultTableModel();
                quizMarksTable.setModel(quizModel);

                quizModel.addColumn("Quiz no");
                quizModel.addColumn("Obtain Marks");
                quizModel.addColumn("Total Marks");


                assignmentCounter = 0;
                quizCounter = 0;
                try {
                    reader = new FileReader("StudentRecord.txt");
                    bufferedReader = new BufferedReader(reader);
                    String line = null;
                    String token[] = null;
                    String row[] = {"","",""};

                    while ((line = bufferedReader.readLine()) != null)
                    {
                        token = line.split(",");
                        if(studentId.equals(token[0]) && token[1].equals("Assignment") && token[5].equals(courseTable.getValueAt(courseTable.getSelectedRow(),0)) )
                        {
                            row[0] = token[2];
                            row[1] = token[3];
                            row[2] = token[4];

                            assignmentCounter++;
                            assignmentModel.addRow(row);
                        }
                        else if (studentId.equals(token[0]) && token[1].equals("Quiz") && token[5].equals(courseTable.getValueAt(courseTable.getSelectedRow(),0)))
                        {
                            row[0] = token[2];
                            row[1] = token[3];
                            row[2] = token[4];

                            quizCounter++;
                            quizModel.addRow(row);
                        }
                    }

                    bufferedReader.close();
                    reader.close();
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

                if (assignmentCounter == 0)
                {
                    JLabel emptyLabel = new JLabel("We have no assignment of this subject");
                    emptyLabel.setBounds(70,70,250,20);
                    emptyLabel.setForeground(Color.DARK_GRAY);

                    markFrame.add(emptyLabel);
                }
                if (quizCounter == 0)
                {
                    JLabel emptyLabel = new JLabel("We have no quiz of this Subject");
                    emptyLabel.setBounds(70,320,200,20);
                    emptyLabel.setForeground(Color.DARK_GRAY);
                    markFrame.add(emptyLabel);
                }
                quizMarksScroll = new JScrollPane(quizMarksTable);
                quizMarksScroll.setBounds(50, 300, 300, 200);

                markFrame.add(assignmentMarksScroll);
                markFrame.add(quizMarksScroll);

                window.add(markFrame);
            }
        }
    }
}
