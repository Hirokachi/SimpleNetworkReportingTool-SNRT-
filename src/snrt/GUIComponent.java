/*
 * This class contains the information for the GUI to run
 * on this program.
 */
package snrt;

//import javax.swing.AbstractButton;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JTextArea;
 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComponent;
//import javax.swing.DefaultSingleSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
 
/**
 *
 * @author Alex Gaskill
 */
public class GUIComponent extends JPanel
                implements ActionListener{
    
    protected JButton getProcess, nextComputer, previousComputer, killTask; 
    protected JTextArea processes;
    protected JScrollPane textScroller;
    //int process;
    
    /**
    * Defines the GUIComponent class and sets it up to be used.
    * No parameters needed.
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
        
        //Creates the Button "Go Get Processes" and sets it up.
        getProcess = new JButton("Go Get Processes!");
        getProcess.setActionCommand("goGetIt");
        
        //Creates the Button "Kill Selected Process" and sets it up.
        //killTask = new JButton("Kill Selected Process");
        //killTask.setActionCommand("goKillIt");
        
        //Creates the Button "Get Next Computer" and sets it up.
        //nextComputer = new JButton("Get Next Computer");
        //nextComputer.setActionCommand("nextComputer");
        
        //Creates the Button "Get Next Computer" and sets it up.
        //previousComputer = new JButton("Get previous Computer");
        //previousComputer.setActionCommand("previousComputer");
        
        //Listens for pressed button getProcess.
        getProcess.addActionListener(this);
        
        //Listens for pressed button killTask.
        //killTask.addActionListener(this);
        
        //Listens for pressed button nextComputer.
        //nextComputer.addActionListener(this);
 
        //Listens for pressed button nextComputer.
        //previousComputer.addActionListener(this);
        
        //Sets the tool tip on the buttons.
        getProcess.setToolTipText("Click this button to get processes on this"
                + " computer.");
        //killTask.setToolTipText("Click this button to kill a selected process."
        //        + "at this point not developt yet.");
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
        //GridLayout contentGrid = new GridLayout(1,2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Set up the Content Grid.
        //contentGrid.addLayoutComponent("Processes", textScroller);
        //contentGrid.addLayoutComponent("Buttons", nextComputer);
        //contentGrid.addLayoutComponent("Buttons", previousComputer);
        //contentGrid.addLayoutComponent("Buttons", getProcess);
        //contentGrid.addLayoutComponent("Buttons", killTask);
        
        /*
         * Create and set up the content pane.
         */
        JComponent panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        frame.setLayout(layout);
        
        
        //Create a sequential group for the horizontal axis.
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
        
        // The sequential group contains two parallel groups that align
        // the contents along the baseline. The first parallel group contains
        // the first label and text field, and the second parallel group contains
        // the second label and text field. By using a sequential group
        // the labels and text fields are positioned vertically after one another.
        GroupLayout.ParallelGroup vGroup = layout.createParallelGroup();
        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
                .addComponent(getProcess));
        layout.setVerticalGroup(vGroup);
        
        // Create the panel.
        frame.setContentPane(panel);
        
        //Display the window.
        frame.pack();
        frame.setBounds(0, 0, 750, 500);
        frame.setVisible(true);
        
        
    }
} 