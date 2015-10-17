/*
 * This class contains the information for the GUI to run
 * on this program.
 */
package snrt;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JTextField;
 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import snrt.getPID;
/**
 *
 * @author Alex Gaskill
 */
public class GUIClass extends JPanel
                implements ActionListener{
    
    protected JButton getProcess, nextComputer; 
   
    public void ButtonMenu() {
        
        getProcess = new JButton("Go Get Processes!");
        getProcess.setVerticalTextPosition(AbstractButton.CENTER);
        getProcess.setActionCommand("goGetIt");
 
        //Listen for actions on button getProcess.
        getProcess.addActionListener(this);
 
        getProcess.setToolTipText("Click this button to get processes on computer.");
 
        //Add Components to this container, using the default FlowLayout.
        add(getProcess);
    }

    public void actionPerformed(ActionEvent e) {
        /*
        getPID pID = new getPID();
        if ("goGetIt".equals(e.getActionCommand())){
            pID.getProcesses();
        }
        */
    }
 
    /**
     * Create the GUI and show it.  For thread safety, 
     * this method should be invoked from the 
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
 /*
        //Create and set up the window.
        JFrame frame = new JFrame("ButtonDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Create and set up the content pane.
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
         */
    }
} 