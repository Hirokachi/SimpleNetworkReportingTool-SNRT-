/*
 * This class contains the information for the GUI to run
 * on this program.
 */
package snrt;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JTextArea;
 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComponent;
//import javax.swing.JScrollPane;
//import javax.swing.ScrollPaneConstants;
 
/**
 *
 * @author Alex Gaskill
 */
public class GUIComponent extends JPanel
                implements ActionListener{
    
    protected JButton getProcess, nextComputer; 
    protected JTextArea processes;
    //protected JScrollPane textScroller;
    
    /**
    * Needs nothing
    */
    public GUIComponent() {
        
        processes = new JTextArea("No Processes.", 10, 10);
        //textScroller = new JScrollPane(processes);
        getProcess = new JButton("Go Get Processes!");
        
        getProcess.setVerticalTextPosition(AbstractButton.BOTTOM);
        getProcess.setActionCommand("goGetIt");
        
        //Listen for actions on button getProcess.
        getProcess.addActionListener(this);
 
        getProcess.setToolTipText("Click this button to get processes on this"
                + " computer.");
        
        //Sets the Editable state of the TextArea to not editable.
        processes.setEditable(false);
        
        //Add Components to this container, using the default FlowLayout.
        add(getProcess);
        add(processes);
        //add(textScroller);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        ProcessComponent pID = new ProcessComponent();
        
        if ("goGetIt".equals(e.getActionCommand())){
            processes.setText(pID.getProcesses());
            //System.out.print(processes.getText());
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
    public void createAndShowGUI() {

        //Create and set up the window.
        JFrame frame = new JFrame("Simple Network Reporter Tool - SNRT");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Create and set up the content pane.
        JComponent newContentPane = new GUIComponent();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
 
        //Display the window.
        frame.pack();
        frame.setBounds(0, 0, 750, 500);
        frame.setVisible(true);
        
        
    }
} 