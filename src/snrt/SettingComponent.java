/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snrt;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Alex Gaskill
 */
public class SettingComponent extends JPanel implements ActionListener{
    
    protected GUIComponent GUI;
    protected JTextField refreshDelay;
    protected JFrame settings;
    
    /**
     * 
     */
    public SettingComponent() {
    
    }


    /**
     * Create the setting GUI
     */
    public void SettingGUI() {

        //Create and set up the window.
        settings = new JFrame("SNRT: Settings page");
        
        //Make a instance of the GUIComponent
        GUI = new GUIComponent();
        
        //Label for the TextField
        JLabel delayLabel = new JLabel("Delay(milliseconds) until next refresh");

        //TextField Setup
        refreshDelay = new JTextField(12);

        //Sets the label to the Text 
        delayLabel.setLabelFor(refreshDelay);

        //Creates and sets up the Apply settings button
        JButton applySettings = new JButton("Apply and close");
        applySettings.setActionCommand("setSettings");
        applySettings.addActionListener(this);


        // Create and set up the content pane.
        JComponent panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);

        //sets the layout to the group layout that is previously defined.
        settings.setLayout(layout);

        //Create a sequential group to be added to the layout for the frame.
        GroupLayout.SequentialGroup Group = layout.createSequentialGroup();


        Group.addGroup(layout.createSequentialGroup()
                .addComponent(delayLabel).addComponent(refreshDelay)
                .addComponent(applySettings));

        //adds the group as a vertical group in the layout for the frame and
        //doesn't need to be put first because it is setting the content after
        //the layout is created and intialized.
        layout.setVerticalGroup(Group);

        // Create the panel.
        settings.setContentPane(panel);

        //Display the window.
        settings.pack();
        settings.setBounds(0, 0, 550, 600);
        settings.setVisible(true);  

    }
    
    /**
     * Does the action for the Apply Button
     * @param e: This class 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //The action command is obtained by the internal method called 
        //"getActionCommand" as long as that is not null then it will switch
        //on whatever the actioncommand had assigned to it.
        if (null != e.getActionCommand())
            switch (e.getActionCommand()) {
            case "setSettings":
                if(refreshDelay.getText().isEmpty()) {
                   JOptionPane.showMessageDialog(null, "No value for the"
                        + " Delay(milliseconds) until next refresh."
                           + " Not setting delay.", "Warning:"
                    , JOptionPane.OK_OPTION); 
                }
                else{
                    String delay = refreshDelay.getText();
                    GUI.setDelay(Integer.parseInt(delay));
                    settings.dispose();
                }
            break;
        }

    }
}