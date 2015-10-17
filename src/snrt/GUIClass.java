/*
 * This class contains the information for the GUI to run
 * on this program.
 */
package snrt;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComponent;
/**
 *
 * @author Alex Gaskill
 */
public class GUIClass extends JPanel
                implements ActionListener{
    
    protected JButton getProcess, nextComputer; 
    protected static JLabel processes;
    protected static JPanel panelForText;
    
    /**
    * Needs nothing
    */
    public GUIClass() {
        
        
        getProcess = new JButton("Go Get Processes!");
        getProcess.setVerticalTextPosition(AbstractButton.CENTER);
        getProcess.setActionCommand("goGetIt");
        
        //Listen for actions on button getProcess.
        getProcess.addActionListener(this);
 
        getProcess.setToolTipText("Click this button to get processes on computer.");
 
        //Add Components to this container, using the default FlowLayout.
        add(getProcess);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        getPID pID = new getPID();
        
        if ("goGetIt".equals(e.getActionCommand())){
            processes.setText(pID.getProcesses());
            System.out.print(pID.getProcesses());
        }
        else {
            processes.setText("Could not find any processes!");
        }
    }
 
    /**
     * Create the GUI and show it.  For thread safety, 
     * this method should be invoked from the 
     * event-dispatching thread.
     */
    public static void createAndShowGUI() {

        //Create and set up the window.
        JFrame frame = new JFrame("Processes");
        processes = new JLabel("Test");
        panelForText = new JPanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        /*
        *  Add the text field to the panel that will house the text field then
        *  to the window.
        */
        panelForText.add(processes);
        frame.add(panelForText);
        
 
        //Create and set up the content pane.
        JComponent newContentPane = new GUIClass();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
        
    }
} 