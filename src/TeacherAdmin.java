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

public class TeacherAdmin extends JFrame implements ActionListener
{

    private JButton studentDoor;
    private JInternalFrame addPan;

    private JComboBox comboBox;
    private JLabel nameLabel;
    private JLabel idLabel;
    private JLabel passwordLabel;
    private JLabel subjectLabel;

    private String subject;

    private JButton addBtnRecord;
    private JButton btnPassword;

    private JTextField nameField;
    private JTextField idField;
    private JTextField passwordField;

    private JFrame teacherFrame;



    private JTable teacherListTable;

    private JScrollPane teacherListPane;

    private JLabel teacherMainLabel;

    private JButton teacherAddRecordBtn;
    private JButton teacherDeleteRecordBtn;

    private DefaultTableModel teacherListModel;
    public TeacherAdmin()
    {
        teacherFrame=new JFrame("TEACHER");
        teacherFrame.setLayout(null);
        addPan = new JInternalFrame();

        try {
            teacherFrame.setContentPane(
                    new JLabel(new ImageIcon(ImageIO.read(new File("background.jpg")))));
        } catch (IOException e) {
            e.printStackTrace();
        }


       JButton btnLogout = new JButton("Logout");
        btnLogout.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btnLogout)
                {
                    teacherFrame.dispose();
                    new Login();
                }
            }
        });
        btnLogout.setBounds(1224,0,100,20);

        teacherFrame.add(btnLogout);


        studentDoor = new JButton("Student");
        studentDoor.setBounds(300,0,100,20);
        studentDoor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == studentDoor)
                {
                    teacherFrame.dispose();
                    new StudentAdmin();
                }
            }
        });

        teacherFrame.add(studentDoor);
        teacherFrame.setBounds(50,30,1400,900);
        teacherList();
        teacherComponents();
        teacherFrame.setVisible(true);
    }

    public void teacherList()
    {
        teacherMainLabel=new JLabel("TEACHER");
        teacherMainLabel.setBounds(450,30,300,30);
        teacherFrame.add(teacherMainLabel);

        teacherListTable=new JTable();

        teacherListModel=new DefaultTableModel();
        teacherListTable.setModel(teacherListModel);

        Object[] row={"Waqas","223","Programming"};
        teacherListModel.addColumn("Name");
        teacherListModel.addColumn("ID");
        teacherListModel.addColumn("Subject");

        try {
            FileReader reader = new FileReader("Teacher.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line = null;
            String token[] = null;
            while ((line = bufferedReader.readLine()) != null)
            {
                token = line.split(",");
                teacherListModel.addRow(token);
            }

            bufferedReader.close();
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



        teacherListPane=new JScrollPane(teacherListTable);
        teacherListPane.setBounds(60,85,400,400);

        teacherFrame.add(teacherListPane);
    }

    public void teacherComponents()
    {
        teacherAddRecordBtn=new JButton("Add Record");
        teacherAddRecordBtn.setBounds(485,120,120,30);
        teacherAddRecordBtn.addActionListener(this);
        teacherAddRecordBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        teacherFrame.add(teacherAddRecordBtn);


        teacherDeleteRecordBtn=new JButton("Delete Record");
        teacherDeleteRecordBtn.setBounds(485,180,120,30);
        teacherDeleteRecordBtn.addActionListener(this);
        teacherDeleteRecordBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnPassword = new JButton("Password");
        btnPassword.setBounds(485,240,120,30);
        btnPassword.addActionListener(this);
        btnPassword.setCursor(new Cursor(Cursor.HAND_CURSOR));

        teacherFrame.add(btnPassword);

        teacherFrame.add(teacherDeleteRecordBtn);

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==teacherAddRecordBtn)
        {
            teacherFrame.remove(addPan);
            addTeacherFun();
        }
        else if (e.getSource() == btnPassword)
        {
            if(teacherListTable.getSelectedRow() == -1)
            {
                JOptionPane.showConfirmDialog(null,"Select any Teacher to view password",
                        "Message",JOptionPane.PLAIN_MESSAGE);
            }
            else
            {
                String id = (String) teacherListTable.getValueAt(teacherListTable.getSelectedRow(),1);
                try {
                    FileReader reader = new FileReader("Login.txt");
                    BufferedReader bufferedReader = new BufferedReader(reader);
                    String line = null;
                    String token[] = null;
                    while ((line = bufferedReader.readLine())!= null)
                    {
                        token = line.split(",");
                        if(token[0].equals(id)&& token[4].equals("Teacher"))
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
        else if(e.getSource()==teacherDeleteRecordBtn)
        {
            System.out.println("teacher delete record button clicked");
            if(teacherListTable.getSelectedRow() == -1)
            {
                JOptionPane.showConfirmDialog(null,"select teacher to delete");
            }
            else
            {
                teacherFrame.remove(addPan);
                int row = teacherListTable.getSelectedRow();
                String id = (String) teacherListModel.getValueAt(row,2);
                teacherListModel.removeRow(row);
                deleteRow(id);
            }
        }
        else if(e.getSource() == addBtnRecord)
        {
            FileWriter writer;
            BufferedWriter bufferedWriter;
            try {
                writer = new FileWriter("Teacher.txt",true);
                bufferedWriter = new BufferedWriter(writer);
                bufferedWriter.write(nameField.getText().trim()+","+idField.getText().trim()+","+subject+"\n");
                bufferedWriter.close();
                writer.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            FileWriter writer1 = null;
            BufferedWriter bufferedWriter1 = null;

            try {
                writer1 = new FileWriter("Login.txt",true);
                bufferedWriter1 = new BufferedWriter(writer1);

                bufferedWriter1.write(idField.getText().trim()+"," +passwordField.getText().trim()
                        +","+nameField.getText().trim()+","+subject+",Teacher\n");

                String row[] = {nameField.getText(),idField.getText(),subject};
                teacherListModel.addRow(row);
                bufferedWriter1.close();
                writer1.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    private void addTeacherFun() {

        addPan = new JInternalFrame();
        addPan.setBounds(640,85,500,400);
        addPan.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLoweredBevelBorder(),
                BorderFactory.createLoweredBevelBorder()));
        addPan.setBackground(Color.LIGHT_GRAY);
        addPan.setLayout(null);
        addPan.setVisible(true);


        JLabel addStudentTitle=new JLabel("Add New Student Record");
        addStudentTitle.setBounds(100,30,300,30);
        Font addStudentTitleFont=new Font("Arial",Font.BOLD,24);
        addStudentTitle.setFont(addStudentTitleFont);
        addStudentTitle.setForeground(Color.WHITE);
        addPan.add(addStudentTitle);

        nameLabel = new JLabel("Name ");
        nameLabel.setBounds(30,80,100,20);

        nameField = new JTextField();
        nameField.setBounds(30,100,100,20);

        idLabel = new JLabel("ID");
        idLabel.setBounds(300,80,100,20);

        FileReader reader =null;
        BufferedReader bufferedReader = null;
        int n = 0;
        try {
            reader = new FileReader("Teacher.txt");
            bufferedReader = new BufferedReader(reader);
            String line = null;
            String token[] = null;

            while ((line = bufferedReader.readLine()) != null)
            {
                n++;

            }
            n++;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        idField = new JTextField();
        idField.setBounds(300,100,100,20);
        idField.setText(String.valueOf(n));

        subjectLabel = new JLabel("Subject");
        subjectLabel.setBounds(30,140,100,20);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(300,140,100,20);

        Random rand = new Random();
        int i=rand.nextInt();
        if(i<0)
            i=i*(-1);

        passwordField = new JTextField();
        passwordField.setBounds(300,160,100,20);
        passwordField.setText(String.valueOf(i));

        addBtnRecord = new JButton("Add");
        addBtnRecord.setBounds(150,200,100,40);
        addBtnRecord.addActionListener(this);


        String option[] = {"Physics","Chemistry","Biology","Math","Urdu","English","Islamiat","Computer"};

        comboBox = new JComboBox(option);
        comboBox.setBounds(30,160,100,20);
        subject = "Physics";
        comboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getItem() == "Physics")
                    subject = "Physics";
                else if(e.getItem() == "Chemistry")
                    subject = "Chemistry";
                else if(e.getItem() == "Biology")
                    subject = "Biology";
                else if(e.getItem() == "Computer")
                    subject = "Computer";
                else if(e.getItem() == "Math")
                    subject = "Math";
                else if(e.getItem() == "Urdu")
                    subject = "Urdu";
                else if(e.getItem() == "English")
                    subject = "English";
                else if(e.getItem() == "Islamiat")
                    subject = "Islamiat";
                else if(e.getItem() == "Computer")
                    subject = "Computer";
            }});



        addPan.add(addBtnRecord);
        addPan.add(comboBox);
        addPan.add(subjectLabel);
        addPan.add(passwordField);
        addPan.add(passwordLabel);
        addPan.add(idLabel);
        addPan.add(nameLabel);
        addPan.add(nameField);
        addPan.add(idField);

        teacherFrame.add(addPan);

    }

    private void deleteRow(String id){
        FileReader reader = null;
        BufferedReader bufferedReader = null;
        FileWriter writer = null;
        BufferedWriter bufferedWriter = null;

        try {
            reader = new FileReader("Teacher.txt");
            bufferedReader = new BufferedReader(reader);

            writer = new FileWriter("Temp.txt",true);
            bufferedWriter = new BufferedWriter(writer);

            String line = null;
            String token[] = null;
            while ((line = bufferedReader.readLine()) != null)
            {
                token = line.split(",");

                if(!(id.equals(token[1])))
                {
                    System.out.println("in condition");
                    bufferedWriter.write(line+"\n");
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {

            bufferedReader.close();
            reader.close();

            bufferedWriter.close();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        File forRemove = new File("Teacher.txt");
        if(forRemove.exists())
            forRemove.delete();
        File forRename = new File("Temp.txt");
        if(forRename.exists())
            forRename.renameTo(new File("Teacher.txt"));
    }
}
