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
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
 
/**
 *
 * @author Alex Gaskill
 */
public class GUIComponent extends JPanel
                implements ActionListener{
    
    protected JButton getProcess, nextComputer, KillTask; 
    protected JTextArea processes;
    JScrollPane textScroller;
    
    /**
    * Needs nothing
    */
    public GUIComponent() {
        
        //Creates the JTextArea Container.
        processes = new JTextArea("No Processes.", 20, 40);
        
        //Sets the textArea to not be editable
        processes.setEditable(false);
        
        // Creates the JScrollPane to have the JTextArea in it.
        textScroller = new JScrollPane(processes, 
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        //Creates the Button "Go Get Processes" for the project and sets it up.
        getProcess = new JButton("Go Get Processes!");
        getProcess.setVerticalTextPosition(AbstractButton.BOTTOM);
        getProcess.setActionCommand("goGetIt");
        
        //Listen for actions on button getProcess.
        getProcess.addActionListener(this);
 
        getProcess.setToolTipText("Click this button to get processes on this"
                + " computer.");

        //Add Components to this container, using the default FlowLayout.
        add(getProcess);
        add(textScroller);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        ProcessComponent pID = new ProcessComponent();
        
        if ("goGetIt".equals(e.getActionCommand())){
            processes.setText(pID.getProcesses());
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
        
        /*
         * Create and set up the content pane.
         */
        JComponent panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        frame.setLayout(layout);
         
        // Turn on automatically adding gaps between components
        layout.setAutoCreateGaps(true);

        // Turn on automatically creating gaps between components that touch
        // the edge of the container and the container.
        layout.setAutoCreateContainerGaps(true);
        
        // Create a sequential group for the horizontal axis.
        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();

        
        // The sequential group in turn contains two parallel groups.
        // One parallel group contains the labels, the other the text fields.
        // Putting the labels in a parallel group along the horizontal axis
        // positions them at the same x location.
        //
        // Variable indentation is used to reinforce the level of grouping.
        hGroup.addGroup(layout.createParallelGroup().
            addComponent(textScroller));
        layout.setHorizontalGroup(hGroup);
        

        // Create a sequential group for the vertical axis.
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
            addComponent(getProcess));
        layout.setVerticalGroup(vGroup);
        
        // Create the panel.
        frame.setContentPane(panel);
 
        //Display the window.
        frame.pack();
        frame.setBounds(0, 0, 750, 500);
        frame.setVisible(true);
        
        
    }
} 