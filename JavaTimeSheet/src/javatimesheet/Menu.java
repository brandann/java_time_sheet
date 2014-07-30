/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javatimesheet;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;


/**
 *
 * @author brandan
 */
public class Menu extends JFrame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
            throws IOException, FileNotFoundException, ClassNotFoundException {
        try {
            javax.swing.UIManager.LookAndFeelInfo[] installedLookAndFeels=
                    javax.swing.UIManager.getInstalledLookAndFeels();
            for (int idx=0; idx<installedLookAndFeels.length; idx++)
                if ("Nimbus".equals(installedLookAndFeels[idx].getName())) {
                    javax.swing.UIManager.setLookAndFeel(
                            installedLookAndFeels[idx].getClassName());
                    break;
                }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(
                    Menu.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(
                    Menu.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(
                    Menu.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(
                    Menu.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        if((args.length < 1)){
            JFrame frame = new Menu();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            frame.setResizable(true);
            frame.setTitle("Northshore Time Sheet -- Main Menu");
        }
        else if (args[0].equals("-a")){
            UserProfile newUser = new UserProfile(args[2] + ", " + args[1]);
            newUser.setPassword("clown");
            newUser.save();
            newUser.print();
            //add user to list
            String[] users = new Driver().getUsers();
            File file = new File(USERS_PATH);
            PrintWriter out = new PrintWriter(new FileOutputStream(file));
            try
            {
                for(String s: users)
                    out.println(s);
                out.println(args[2] + ", " + args[1]);
                out.close( );
            }
            catch(Exception e){
            }
        }
        else{
            System.out.println("Sorry " + args[0] + " is not allowed...");
        }
        
    }
    
    public Menu() 
            throws IOException, FileNotFoundException, ClassNotFoundException {
        setLayout(new BorderLayout());
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setBackground(Color.WHITE);
        intComponents();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(new Point((screenSize.width  - FRAME_WIDTH)  / 2,
                              (screenSize.height - FRAME_HEIGHT) / 2));
    }
    
    private void intComponents() 
            throws IOException, FileNotFoundException, ClassNotFoundException {
        driver = new Driver();
        menuBar = new JMenuBar();
        menuFile = new JMenu("File");
        newUser = new JMenuItem("New User");
        removeUser = new JMenuItem("Remove User");
        menuClose = new JMenuItem("Exit");
        listbox = new JList(loadNames());
        passwordField = new JPasswordField(20);
        label = new JLabel("Login");
        loginButton = new JButton("Login...");
        loginPanel = new JPanel(new FlowLayout());
        bottomPanel = new JPanel(new FlowLayout());
        tabelPanel = new JPanel(new FlowLayout());
        
        loginPanel.setBackground(Color.LIGHT_GRAY);
        bottomPanel.setBackground(Color.LIGHT_GRAY);
        listbox.setBackground(Color.LIGHT_GRAY);
        
        listbox.setFixedCellHeight(23);
        listbox.setAutoscrolls(true);
        listbox.setSelectionBackground(Color.darkGray);
        listbox.setSelectionForeground(Color.lightGray);
        listbox.setForeground(Color.darkGray);

        label.setFont(new Font(FONT, Font.BOLD, FONT_NORMAL));
        loginPanel.add(label);
        bottomPanel.add(passwordField);
        bottomPanel.add(loginButton);
        tabelPanel.add(listbox);        
        
        menuBar.add(menuFile);
        menuFile.add(newUser);
        menuFile.add(removeUser);
        menuFile.add(menuClose);
        
        setJMenuBar(menuBar);
        add(loginPanel,BorderLayout.NORTH);
        add(listbox,BorderLayout.CENTER);
        add(bottomPanel,BorderLayout.SOUTH);
        
        menuClose.addActionListener(new java.awt.event.ActionListener() {
            @Override
        public void actionPerformed(java.awt.event.ActionEvent evt) {
                System.exit(0);
        }});
        
        newUser.addActionListener(new java.awt.event.ActionListener() {
            @Override
        public void actionPerformed(java.awt.event.ActionEvent evt) {
                //AddUser AU = new AddUser();
        }});
        
        removeUser.addActionListener(new java.awt.event.ActionListener() {
            @Override
        public void actionPerformed(java.awt.event.ActionEvent evt) {
                //remmove selected user from list box
                //remove selected user from user.txt
                //delete users profile
        }});
        
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
        public void actionPerformed(java.awt.event.ActionEvent evt) {
                login();
            }});
    }
    
    private String[] loadNames() 
            throws IOException, FileNotFoundException, ClassNotFoundException {
        String[] names = driver.getUsers();
        return names;
    }
    
    public void infoBox(String infoMessage, String title) {
        JOptionPane.showMessageDialog(null, infoMessage, "Info: " 
                + title, JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void login(){
        String selected = "";
                try{
                    selected = (String) listbox.getSelectedValue();
                }catch(Exception e){
                    System.out.println("Error: no user selected");
                }
                String password = String.valueOf(passwordField.getPassword());
                try {
                    if(selected.equals(null) || selected.equals("") || (selected.length() == 0)){
                        infoBox("Who are you??\nPlease select your name to login.","Missing User");
                    }
                    else if(password.equals("") || password.equals(null) || (password.length() == 0)){
                        infoBox("Please enter your password.","Missing Password");
                    }
                    else if(driver.checkPassword(selected,password)){
                        System.out.println("Yay Login Good");
                        MainForm dfs = new MainForm(selected);
                        setVisible(false);
                        dispose();
                    }
                    else{
                        infoBox("The password you entered is incorrect, Please Try again\nCheck to make sure you do not have Caps-Lock enabled.","Incorrect Password");
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                }
                passwordField.setText("");
    }

    class AddUser extends JFrame{
        public AddUser(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(true);
        setTitle("Northshore Time Sheet -- Add New User");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setBackground(Color.WHITE);
        intComponents();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(new Point((screenSize.width  - FRAME_WIDTH)  / 2,
                              (screenSize.height - FRAME_HEIGHT) / 2));
        }
        
        private void intComponents(){
            mainPanel = new JPanel(new GridLayout(6,2));
            nameFLabel = new JLabel("First Name:");
            nameLLabel = new JLabel("Last Name:");
            passwordLabel = new JLabel("Password:");
            positionLabel = new JLabel("Position:");
            rateLabel = new JLabel("Rate (Hourly/Salery):");
            nameFTextField = new JTextField("");
            nameLTextField = new JTextField("");
            passwordTextField = new JTextField("");
            positionTextField = new JTextField("");
            rateTextField = new JTextField("");
            saveButton = new JButton("Save And Exit");
            cancelButton = new JButton("Cancel Without Saving");
            
            mainPanel.add(nameFLabel);
            mainPanel.add(nameFTextField);
            mainPanel.add(nameLLabel);
            mainPanel.add(nameLTextField);
            mainPanel.add(passwordLabel);
            mainPanel.add(passwordTextField);
            mainPanel.add(positionLabel);
            mainPanel.add(positionTextField);
            mainPanel.add(rateLabel);
            mainPanel.add(rateTextField);
            mainPanel.add(saveButton);
            mainPanel.add(cancelButton);
            add(mainPanel);
            
            cancelButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
        public void actionPerformed(java.awt.event.ActionEvent evt) {
                dispose();
            }});
            
            saveButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
        public void actionPerformed(java.awt.event.ActionEvent evt) {
                
                /*
                boolean ok = true;
                boolean contains = true;
                String userName = "";
                
                if(nameFTextField.getText() == ""){
                    ok = false;}
                else if(nameLTextField.getText() == ""){
                    ok = false;
                }
                else if(passwordTextField.getText() == ""){
                    ok = false;
                }
                else if(positionTextField.getText() == ""){
                    ok = false;
                }
                else if(rateTextField.getText() == ""){
                    ok = false;
                }
                else if(nameFTextField.getText() == ""){
                    ok = false;
                }
                else if(passwordTextField.getText().length() < 4){
                    ok = false;
                }
                else{
                    String[] names = null;
                    try {
                        names = driver.getUsers();
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    userName = makeName(nameFTextField.getText(), nameLTextField.getText());
                    for(String s:names){
                        if(!s.equals(userName)){
                            contains = false;
                        }
                    }
                }
                if(!contains){
                    infoBox("The user name you have entered is already in use","User Name Taken");
                }
                if(!ok){
                    infoBox("Make sure that all the text areas are filled in.\nPasswords need at least 4 digits to be accepted","Invalid Entry(s)");
                }

                if(ok || !contains){
                    dispose();
                }
                else{
                    
                }
                */
            }});
            
            
        }
        
        public String makeName(String f, String l){
            String name = "";
            String first = "";
            first = String.valueOf(l.charAt(0)).toUpperCase();
            first = first + l.substring(1).toLowerCase();
            name = first + ", ";
            first = String.valueOf(f.charAt(0)).toUpperCase();
            first = first + f.substring(1).toLowerCase();
            name = name + first;
            System.out.println(name);
            return name;
        }
        
        public void infoBox(String infoMessage, String title){
                JOptionPane.showMessageDialog(null, infoMessage, "Info: " 
                    + title, JOptionPane.INFORMATION_MESSAGE);
            }
        
        private JPanel mainPanel;
        private JLabel nameFLabel;
        private JLabel nameLLabel;
        private JLabel passwordLabel;
        private JLabel positionLabel;
        private JLabel rateLabel;
        private JTextField nameFTextField;
        private JTextField nameLTextField;
        private JTextField passwordTextField;
        private JTextField positionTextField;
        private JTextField rateTextField;
        private JButton saveButton;
        private JButton cancelButton;
        private final int FRAME_HEIGHT = 300;
        private final int FRAME_WIDTH = 400;
    }
    
    Driver driver;
    private JMenuBar menuBar;
    private JMenu menuFile;
    private JMenuItem newUser;
    private JMenuItem removeUser;
    private JMenuItem menuClose;

    private JPanel loginPanel;
    private JPanel bottomPanel;
    private JPanel tabelPanel;
    
    private JLabel label;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JList listbox;   
    
    private final int FRAME_HEIGHT = 600;
    private final int FRAME_WIDTH = 400;
    private final int FONT_XLARGE = 22;
    private final int FONT_NORMAL = 14;
    private final int FONT_SMALL = 12;
    private final String FONT = "";
    static  public final String PATH = "P:\\Northshore Time Sheet\\TimesSheet2013\\";
    static  public final String USERS_PATH = PATH + "users.txt";
    
}
