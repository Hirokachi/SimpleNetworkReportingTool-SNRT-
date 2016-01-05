/*
 * This class contains the information for the GUI to run
 * on this program.
 */
package snrt;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import javax.swing.GroupLayout;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.util.Vector;
 
/**
 *
 * @author Alex Gaskill
 */
public class GUIComponent extends JPanel
                implements ActionListener {
    
    protected JButton getProcess, killTask;
    protected JTable processList;
    protected JScrollPane scrollerText;
            
    /**
    * Defines the GUIComponent class and sets it up to be used.
    * No parameters needed.
    */
    public GUIComponent() {
    
        //Delay for refresh of the tasklist in milliseconds
        int delay = 3000;
        
        //Creates the JTable with the correct number of columns and rows
        processList = new JTable (95, 5);
        
        //Sets the "processList" to fully fill the scroller text
        processList.setFillsViewportHeight(true);
        
        //Sets the processList in the ScrollPane
        scrollerText = new JScrollPane(processList);
        
        //Sets the scroller panel to be able to detect the wheel and scroll.
        scrollerText.setWheelScrollingEnabled(true);
        
        //Creates the Button "Go Get Processes" and sets it up to allow the user
        //to refresh the task list anytime they want.
        getProcess = new JButton("Go Get Processes!");
        getProcess.setActionCommand("goGetIt");
        
        //Creates a local actionListener to refresh the task list.
        ActionListener taskPerformer = new ActionListener() {
            @Override
             public void actionPerformed(ActionEvent evt) {
                 ProcessComponent pID = new ProcessComponent();
                 setJTable(pID.getProcesses());
            }
        };
        
        //Creates a new timer to be used in refreshing the task list after the
        //number of milliseconds defined by the delay variable.
        new Timer(delay, taskPerformer).start();
        
        //Creates the Button "Kill Selected Process" and sets it up.
        killTask = new JButton("Kill Selected Process");
        killTask.setActionCommand("goKillIt");
        
        //Listens for pressed button "getProcess".
        getProcess.addActionListener(this);
        
        //Listens for pressed button "killTask".
        killTask.addActionListener(this);
        
        //Sets the tool tip on the buttons.
        getProcess.setToolTipText("Click this button to get processes on this"
                + " computer.");
        killTask.setToolTipText("Click this button after selecting a process"
                + " to kill it.");
    }
    
    /**
     * This method dictates what happens when one of the predefined buttons is 
     * pressed
     * @param e: whole button object which is passed to this method when 
     * the button is pressed and released. 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        
        //Creating the ProcessComponent object.
        ProcessComponent pID = new ProcessComponent();
        
        //The action command is obtained by the internal method called 
        //"getActionCommand" as long as that is not null then it will switch
        //on whatever the actioncommand had assigned to it.
        if (null != e.getActionCommand())
            switch (e.getActionCommand()) {
            case "goGetIt":
                //sets the Jlist with the "processList" from the "getProcesses"
                //method.
                setJTable(pID.getProcesses());
                break;
            case "goKillIt":
                //before trying to kill selected process it will check to see
                //if there is a process selected. if no process is selected and 
                //they click the kill button it will display the warning message.
                if (processList.getSelectedRow() != 0) {
                    String processID;
                    int row;
                    int column;
      
                    //Set the row and column of the selected cell.
                    row = processList.getSelectedRow();
                    column = processList.getSelectedColumn();
                    
                    processID = processList.getValueAt(row, column).toString();
                    
                    //pass the Process ID to the kill method
                    pID.killSelectedProcess(processID);
                    
                }
                else
                    JOptionPane.showMessageDialog(null, "No process was"
                            + " selected; not ending any tasks.", "Warning:"
                        , JOptionPane.OK_OPTION);
                break;
            }
    }
    
    /**
     * sets the JTable with the data for the program.
     * @param taskList is the information that it gets from the process
     * component. 
     */
    private void setJTable(Vector<String> taskList) {
        
        //verifies that the table has been set
        boolean hasBeenSet = false;
        int i = 0;
        int x = 0;
        
        //this sets the title of the jtable "processList".
        for (String title:taskList.get(1).split("\\s")){
            if (title.matches("[a-zA-Z]+")){
                processList.getColumnModel().getColumn(x).setHeaderValue(title);
                hasBeenSet = true;
            }
            if (x < 4 && x >= 0 && hasBeenSet){
                x++;
                hasBeenSet = false;
            }
        }        
        
        //resets "hasBeenSet" to false for later use.
        hasBeenSet = false;
        
        //does the heavy lifting of getting the data into the table.
        for(String words:taskList) {
            int j = 0;
            for(String word: words.split("\\s")) {
                if(word.matches("[a-zA-Z]+\\.exe") || word.contains("System") ||
                        word.matches("\\p{Digit}+") ||
                        word.matches("\\p{Digit}+\\,\\p{Digit}+\\sK") || 
                        word.contains("HP") || word.contains("Services") ||
                        word.contains("Console")) {
                    processList.setValueAt(word, i, j);
                    hasBeenSet = true;
                }
                if (j < 5 && j >= 0 && hasBeenSet){
                    j++;
                    hasBeenSet = false;
                }
            }
            i++;
        }   
    }
 
    /**
     * Create the GUI and show it.
     */
    public void createAndShowGUI() {

        //Create and set up the window.
        JFrame frame = new JFrame("Simple Network Reporter Tool - SNRT");
        
        //Sets the DefaultCloseOperation to exit; this means that when you hit 
        //the x on the window how will the program respond.In this case, it will
        //exit the program cleanly once the window is closed by hitting the x.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create and set up the content pane.
        JComponent panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        
        //sets the layout to the group layout that is previously defined.
        frame.setLayout(layout);
        
        //Create a sequential group to be added to the layout for the frame.
        GroupLayout.SequentialGroup Group = layout.createSequentialGroup();
        
        //adds the scrollerText, getProcess button, and the killTask button to
        //the group.
        Group.addGroup(layout.createSequentialGroup()
                .addComponent(scrollerText).addComponent(getProcess)
                .addComponent(killTask));
        
        //adds the group as a vertical group in the layout for the frame and
        //doesn't need to be put first because it is setting the content after
        //the layout is created and intialized.
        layout.setVerticalGroup(Group);
        
        // Create the panel.
        frame.setContentPane(panel);
        
        //Display the window.
        frame.pack();
        frame.setBounds(0, 0, 600, 500);
        frame.setVisible(true);  
    }
}
