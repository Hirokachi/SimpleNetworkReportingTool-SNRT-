/*
 * This class contains the information for the GUI to run
 * on this program.
 */
package snrt;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
                implements ActionListener, MouseListener {
    
    protected JButton getProcess, nextComputer, previousComputer, killTask; 
    protected JTextArea processes;
    protected JScrollPane textScroller;
    protected String processID;
            
    /**
    * Defines the GUIComponent class and sets it up to be used.
    * No parameters needed.
    */
    public GUIComponent() {
        
        //Creates the JTextArea Container.
        processes = new JTextArea("No Processes.", 20, 40);
        
        //Sets the textArea to not be editable
        processes.setEditable(false);
        
        //Sets the textArea to have a mouseListener.
        processes.addMouseListener(this);
        
        // Creates the JScrollPane to have the JTextArea in it.
        textScroller = new JScrollPane(processes, 
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
              ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
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
    
    @Override
    public void mouseClicked(MouseEvent e) {
        //Point pointOfClick = e.getPoint();
        //JOptionPane.showMessageDialog(null, "You Clicked on " + pointOfClick
        //       , "YAY!", JOptionPane.OK_OPTION);
        try {
            int line = processes.getLineOfOffset(e.getY());
            int start = processes.getLineStartOffset( line );
            int end = processes.getLineEndOffset( line );
            processID = processes.getDocument().getText(start, end - start);
            JOptionPane.showMessageDialog(null, processID 
                    , null, JOptionPane.OK_OPTION);
        }
        catch(Exception err){
            err.getStackTrace();
        }
        
        
    }
    @Override
    public void mouseExited(MouseEvent e){
        //necessary to implement mouselistener
    }
    
    @Override
    public void mouseEntered(MouseEvent e){
        //necessary to implement mouselistener
    }
    
    @Override
    public void mouseReleased(MouseEvent e){
       //necessary to implement mouselistener     
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        //necessary to implement mouselistener        
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
                //sets the text in process with the information from the getProcess
                // method
                processes.setText(pID.getProcesses());
                break;
            case "goKillIt":
                String [] processInfo = processID.split(" ");
                pID.killSelectedProcess(processInfo);
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
            addComponent(textScroller));
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
