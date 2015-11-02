/*
 * This class contains the information for the GUI to run
 * on this program.
 */
package snrt;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JList;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
 
/**
 *
 * @author Alex Gaskill
 */
public class GUIComponent extends JPanel
                implements ActionListener {
    
    protected JButton getProcess, nextComputer, previousComputer, killTask;
    protected JList processList;
    protected JScrollPane scrollerText;
            
    /**
    * Defines the GUIComponent class and sets it up to be used.
    * No parameters needed.
    */
    public GUIComponent() {
        
        //Create the process List item.
        processList = new JList();
        
        //Sets the number of rows before it needs a scroll bar.
        processList.setVisibleRowCount(20);
        
        //Sets the width of the cell.
        processList.setFixedCellWidth(200);
        
        //Sets the processList in the ScrollPane
        scrollerText = new JScrollPane(processList
                , ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS
                , ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        //Sets the scroller panel to be able to detect the wheel and scroll.
        scrollerText.setWheelScrollingEnabled(true);
        
        //Creates the Button "Go Get Processes" and sets it up.
        getProcess = new JButton("Go Get Processes!");
        getProcess.setActionCommand("goGetIt");
        
        //Creates the Button "Kill Selected Process" and sets it up.
        killTask = new JButton("Kill Selected Process");
        killTask.setActionCommand("goKillIt");
        
        //Creates the Button "Get Next Computer" and sets it up.
        nextComputer = new JButton("Get Next Computer");
        nextComputer.setActionCommand("nextComputer");
        
        //Creates the Button "Get Next Computer" and sets it up.
        previousComputer = new JButton("Get previous Computer");
        previousComputer.setActionCommand("previousComputer");
        
        //Listens for pressed button getProcess.
        getProcess.addActionListener(this);
        
        //Listens for pressed button killTask.
        killTask.addActionListener(this);
        
        //Listens for pressed button nextComputer.
        nextComputer.addActionListener(this);
 
        //Listens for pressed button nextComputer.
        previousComputer.addActionListener(this);
        
        //Sets the tool tip on the buttons.
        getProcess.setToolTipText("Click this button to get processes on this"
                + " computer.");
        killTask.setToolTipText("Click this button after selecting a process"
                + " to kill it.");
        previousComputer.setToolTipText("Click this button to select previous "
                + "computer in the list. This button doesn't work as of yet.");
        nextComputer.setToolTipText("Click this button to select next "
                + "computer in the list. This button doesn't work as of yet.");
    }
    
    /**
     * 
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        
        //Creating the ProcessComponent object.
        ProcessComponent pID = new ProcessComponent();
        
        if (null != e.getActionCommand())switch (e.getActionCommand()) {
            case "goGetIt":
                //sets the Jlist with the processesList from the getProcesses.
                processList.setListData(pID.getProcesses());
                break;
            case "goKillIt":
                //before trying to kill selected process it will check to see
                //if there is a process selected. if no process is selected and 
                //they click the kill button it will display a warning message.
                if (!processList.isSelectionEmpty()) {
                    String [] processID;
                    
                    processID = processList.getSelectedValue().toString().split(" ");
                    
                    //pass the Process ID to the kill method
                    pID.killSelectedProcess(processID[19]);
                    
                    //upon a sucessfull killprocess refresh the processList
                    processList.setListData(pID.getProcesses());
                }
                else
                    JOptionPane.showMessageDialog(null, "No process was"
                            + " selected; not ending any tasks.", "Warning:"
                        , JOptionPane.OK_OPTION);
                break;
            case "nextComputer":
                JOptionPane.showMessageDialog(null, "Get Next Computer is not "
                        + "working at this time." , "Warning:"
                        , JOptionPane.OK_OPTION);
                break;
            case "previousComputer":
                JOptionPane.showMessageDialog(null, "Get previous Computer is "
                       + "not working at this time." , "Warning:"
                        , JOptionPane.OK_OPTION);
                break;
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
        //GridLayout contentGrid = new GridLayout(1,2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create and set up the content pane.
        JComponent panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        frame.setLayout(layout);
        
        //Create a sequential group for the horizontal axis.
        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        
        // The sequential group in turn contains two parallel groups.
        // One parallel group contains the labels, the other the text fields.
        // Putting the labels in a parallel group along the horizontal axis
        // positions them at the same x location.
        // Variable indentation is used to reinforce the level of grouping.
        hGroup.addGroup(layout.createParallelGroup().
            addComponent(scrollerText));
        layout.setHorizontalGroup(hGroup);
        
        // The sequential group contains two parallel groups that align
        // the contents along the baseline. The first parallel group contains
        // the first label and text field, and the second parallel group contains
        // the second label and text field. By using a sequential group
        // the labels and text fields are positioned vertically after one another.
        GroupLayout.ParallelGroup vGroup = layout.createParallelGroup();
        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
                .addComponent(getProcess)).addComponent(killTask)
                .addComponent(previousComputer).addComponent(nextComputer);
        layout.setVerticalGroup(vGroup);
        
        // Create the panel.
        frame.setContentPane(panel);
        
        //Display the window.
        frame.pack();
        frame.setBounds(0, 0, 750, 500);
        frame.setVisible(true);  
    }
}
