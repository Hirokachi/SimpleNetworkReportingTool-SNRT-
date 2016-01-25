/*
 * This is the Settings class.
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
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Alex Gaskill
 */
public class SettingComponent extends JPanel implements ActionListener{
    
    /**
     * Global private variables that are dumb to pass everywhere.
     */
    private GUIComponent GUI;
    private JTextField refreshDelay;
    private JFrame settings;
    private String[] description = { "choose an option", "Highlight results",
        "Eliminate nonmatching results" };
    private JComboBox filterOptions = new JComboBox();
    
    /**
     * Things that need to be set up before any methods can be called
     */
    public SettingComponent() {
        
        //Sets the options for filtering in the dropdown menu
        for (int i = 0; i < 3; i++) {
            filterOptions.addItem(description[i]);
        }
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
        JLabel delayLabel = new JLabel("Delay(milliseconds) until next refresh:");

        //Sets the default action for hitting the x button on the window to 
        // close the window instead of the program.
        settings.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        //TextField Setup
        refreshDelay = new JTextField(12);

        //Sets the label to the Text 
        delayLabel.setLabelFor(refreshDelay);

        //Creates and sets up the Apply settings button
        JButton applySettings = new JButton("Apply and close");
        applySettings.setActionCommand("setSettings");
        applySettings.addActionListener(this);
        
        //sets Label for the dropdown FilterOptions
        JLabel filterOptionsLabel = new JLabel("Configuration of Filter results:");
        
        //Sets the dropdown FilterOptions label
        filterOptionsLabel.setLabelFor(filterOptions);

        // Create and set up the content pane.
        JComponent panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);

        //sets the layout to the group layout that is previously defined.
        settings.setLayout(layout);

        //Create a sequential group to be added to the layout for the frame.
        GroupLayout.SequentialGroup Group = layout.createSequentialGroup();

        //Adds the components to this setting group.
        Group.addGroup(layout.createSequentialGroup()
                .addComponent(delayLabel).addComponent(refreshDelay)
                .addComponent(filterOptionsLabel).addComponent(filterOptions)
                .addComponent(applySettings));

        //adds the group to the layout and the layout is already set to 
        //the window
        layout.setVerticalGroup(Group);

        // Create the panel.
        settings.setContentPane(panel);

        //Display the window.
        settings.pack();
        settings.setBounds(0, 0, 300, 300);
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
                if (refreshDelay.getText().isEmpty() && 
                        filterOptions.getSelectedItem().equals("choose an option")) {
                    JOptionPane.showMessageDialog(null, "No Settings where "
                            + "changed. Not setting anything.", "Warning:"
                    , JOptionPane.OK_OPTION); 
                }
                else if(refreshDelay.getText().isEmpty() && 
                        !filterOptions.getSelectedItem().equals("choose an option")) {
                    GUI.setFilter(filterOptions.getSelectedItem().toString());
                    settings.dispose();
                }
                else if (!refreshDelay.getText().isEmpty() && 
                       filterOptions.getSelectedItem().equals("choose an option")) {
                    String delay = refreshDelay.getText();
                    GUI.setDelay(Integer.parseInt(delay));
                    settings.dispose();
                }
                else {
                    String delay = refreshDelay.getText();
                    GUI.setDelay(Integer.parseInt(delay));
                    GUI.setFilter(filterOptions.getSelectedItem().toString());
                    settings.dispose();
                }
            break;
        }

    }
}