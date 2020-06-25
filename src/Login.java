import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Login implements ActionListener {
    final private String fileName = "Login.txt";

    private JFrame window;

    private JLabel idLabel;
    private JLabel passwordLabel;

    private JTextField idField;
    private JPasswordField passwordField;

    private JCheckBox checkBox;

    private JButton btnLogin;


    public Login(){
        window = new JFrame("Teacher Portal");

        window.setBounds(400,200,400,300);




        window.setResizable(false);
        window.setBackground(Color.gray);
        window.setLayout(null);





        try {
            window.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("log.jpg")))));
        } catch (IOException e) {
            e.printStackTrace();
        }


        init();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        window.setVisible(true);
    }

    public void init()
    {
        idLabel = new JLabel("Enter ID");
        idLabel.setBounds(120,50,150,20);
        idLabel.setForeground(Color.white);

        idField = new JTextField();
        idField.setBounds(120,70,120,20);

        passwordLabel = new JLabel("Enter Password");
        passwordLabel.setBounds(120,90,150,20);
        passwordLabel.setForeground(Color.white);

        passwordField = new JPasswordField();
        passwordField.setBounds(120,110,120,20);

        checkBox = new JCheckBox("show password");
        checkBox.setBounds(120,130,120,20);
        checkBox.setBackground(new Color(12,12,23,120));
        checkBox.setForeground(Color.white);
        checkBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == 1)
                {
                    passwordField.setEchoChar((char)0);
                }
                else
                {
                    passwordField.setEchoChar('*');
                }
            }
        });

        btnLogin = new JButton("Login");
        btnLogin.setBounds(140,160,100,20);
       btnLogin.addActionListener(this);


        window.add(btnLogin);
        window.add(checkBox);
        window.add(passwordField);
        window.add(passwordLabel);
        window.add(idField);
        window.add(idLabel);
    }

    public void checkLogin()
    {
        FileReader reader = null;
        BufferedReader bufferedReader = null;
        try{
            reader = new FileReader(fileName);
            bufferedReader = new BufferedReader(reader);
            String line = null;
            String token[] = null;
            boolean flag = true;
            while((line = bufferedReader.readLine()) != null)
            {
                token = line.split(",");
                if(idField.getText().trim().equals(token[0]) && passwordField.getText().trim().equals(token[1]))
                {

                    if(token[4].equals("Teacher"))
                    {
                        window.dispose();
                        new Teacher(token[2],token[0],token[3]);
                    }
                    else if(token[4].equals("Student"))
                    {
                        window.dispose();
                        new Student(token[2],token[0],token[3]);
                    }
                    else if(token[3].equals("Admin"))
                    {
                       window.dispose();
                       new StudentAdmin();
                    }

                    flag = false;
                    break;
                }
            }
            if(flag)
            {
                JOptionPane.showConfirmDialog(null,"password or id is in-currect","Message",JOptionPane.PLAIN_MESSAGE);
            }
        }catch (IOException e1)
        {
            e1.printStackTrace();
        }

        try
        {
            bufferedReader.close();
            reader.close();

        }catch (IOException e2)
        {
            e2.printStackTrace();
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnLogin)
        {
            if(idField.getText().trim().isEmpty() || passwordField.getText().trim().isEmpty())
                JOptionPane.showConfirmDialog(null,"All fields are required","Message",JOptionPane.PLAIN_MESSAGE);
            else
                checkLogin();
        }
    }
}
