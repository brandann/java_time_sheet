/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javatimesheet;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 *
 * @author brandan
 */
public class MainForm extends JFrame{
    public MainForm(String name){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(true);
        setTitle("Northshore Time Sheet -- " + name);
        setLayout(new BorderLayout());
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setBackground(Color.WHITE);
        intComponents();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(new Point((screenSize.width  - FRAME_WIDTH)  / 2,
                              (screenSize.height - FRAME_HEIGHT) / 2));
    }
    
    private void intComponents(){
        menuBar = new JMenuBar();
        menuFile = new JMenu("File");
        menuEditTime = new JMenuItem("Edit Time");
        menuOptions = new JMenuItem("Options");
        menuExit = new JMenuItem("Exit");
        
        mainPanel = new JPanel(new BorderLayout());
        sidePanel = new JPanel(new BorderLayout());
        calendarPanel = new JPanel();
        currentPanel = new JPanel();
        todayPanel = new JPanel();
        statsPanel = new JPanel();
          
        mainPanel.setBackground(Color.black);
        sidePanel.setBackground(Color.white);
        calendarPanel.setBackground(Color.yellow);
        currentPanel.setBackground(Color.green);
        todayPanel.setBackground(Color.red);
        statsPanel.setBackground(Color.blue);
           
        calendarPanel.add(new JLabel("."));
        currentPanel.add(new JLabel("."));
        todayPanel.add(new JLabel("."));
        statsPanel.add(new JLabel("."));
           
        mainPanel.add(todayPanel, BorderLayout.CENTER);
        mainPanel.add(statsPanel, BorderLayout.SOUTH);
            
        sidePanel.add(calendarPanel, BorderLayout.NORTH);
        sidePanel.add(currentPanel, BorderLayout.CENTER);
            
        add(mainPanel, BorderLayout.CENTER);
        add(sidePanel, BorderLayout.WEST);
            
        menuBar.add(menuFile);
        menuFile.add(menuEditTime);
        menuFile.add(menuOptions);
        menuFile.add(menuExit);
        
        setJMenuBar(menuBar);
        
        menuExit.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                System.exit(0);
        }});
        
        menuEditTime.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditTime e = new EditTime();
        }});
        
        menuOptions.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Options o = new Options();
        }});
    }

    class EditTime extends JFrame{
        public EditTime(){
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setVisible(true);
            setResizable(true);
            setTitle("Northshore Time Sheet -- Edit Time");
            setLayout(new BorderLayout());
            setSize(FRAME_WIDTH, FRAME_HEIGHT);
            setBackground(Color.WHITE);
            intComponents();
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            setLocation(new Point((screenSize.width  - FRAME_WIDTH)  / 2,
                              (screenSize.height - FRAME_HEIGHT) / 2));
        }
        
        private void intComponents(){
            menuBar = new JMenuBar();
            menuFile = new JMenu("File");
            menuSave = new JMenuItem("Save");
            menuCancel = new JMenuItem("Cancel");
            


            menuBar.add(menuFile);
            menuFile.add(menuSave);
            menuFile.add(menuCancel);
        
            setJMenuBar(menuBar);
            
            menuCancel.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    setVisible(false);
                    dispose();
            }});
        }
        
        
        
        private JMenuBar menuBar;
        private JMenu menuFile;
        private JMenuItem menuSave;
        private JMenuItem menuCancel;
        
        private final int FRAME_HEIGHT = 300;
        private final int FRAME_WIDTH = 400;
    }
    
    class Options extends JFrame{
        public Options(){
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setVisible(true);
            setResizable(true);
            setTitle("Northshore Time Sheet -- Options");
            setLayout(new BorderLayout());
            setSize(FRAME_WIDTH, FRAME_HEIGHT);
            setBackground(Color.WHITE);
            intComponents();
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            setLocation(new Point((screenSize.width  - FRAME_WIDTH)  / 2,
                              (screenSize.height - FRAME_HEIGHT) / 2));
        }
        
        private void intComponents(){
            
        }
        
        private final int FRAME_HEIGHT = 600;
        private final int FRAME_WIDTH = 600;
    }
    
    class SubmitTime extends JFrame{
        public SubmitTime(){
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);
            setResizable(true);
            setTitle("Northshore Time Sheet -- Options");
            setLayout(new BorderLayout());
            setSize(FRAME_WIDTH, FRAME_HEIGHT);
            setBackground(Color.WHITE);
            intComponents();
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            setLocation(new Point((screenSize.width  - FRAME_WIDTH)  / 2,
                              (screenSize.height - FRAME_HEIGHT) / 2));
        }
        
        private void intComponents(){
            
        }
        
        private final int FRAME_HEIGHT = 600;
        private final int FRAME_WIDTH = 800;
    }
    
    private JPanel mainPanel;
    private JPanel sidePanel;
    private JPanel calendarPanel;
    private JPanel currentPanel;
    private JPanel todayPanel;
    private JPanel statsPanel;
        
    private JMenuBar menuBar;
    private JMenu menuFile;
    private JMenuItem menuEditTime;
    private JMenuItem menuOptions;
    private JMenuItem menuExit;
    private final int FRAME_HEIGHT = 600;
    private final int FRAME_WIDTH = 800;
}
