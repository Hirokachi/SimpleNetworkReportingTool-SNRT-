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
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
 
/**
 *
 * @author Alex Gaskill
 */
public class GUIComponent extends JPanel
                implements ActionListener {
    
    /**
     * The secret and protected variable that I don't wish to pass everywhere.
     * Here is the List:
     * JButton getProcess, killTask
     * JTable processList
     * JScrollPane scrollerText
     * ProcessComponent pID
     */
    protected JButton getProcess, killTask;
    protected JTable processList;
    protected JScrollPane scrollerText;
    protected ProcessComponent pID;
    protected JTextArea numberOfProcess, nameOfComputers;
    
    /**
    * Defines the GUIComponent class and sets it up to be used.
    * No parameters needed.
    */
    public GUIComponent() {
    
        //Delay for refresh of the tasklist in milliseconds
        int delay = 4500;
        
        //Creating the ProcessComponent object.
        pID = new ProcessComponent();
        
       //
//        setRadioButtons();
        
        //get the number of tasks
        int numberOfProcesses = pID.getnumberOfTasks();
        
        //Set up the string that will go in to the text area
        String processes = "number of Processes: " + numberOfProcesses;
        
        //Create an object of a textArea.
        numberOfProcess = new JTextArea(processes);
        
        //Make the number of processes not editable.
        numberOfProcess.setEditable(false);
        
        //Creates the JTable with the correct number of columns and rows
        processList = new JTable (numberOfProcesses, 5);
        
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
                
                int row;
                int column;

                //Set the row and column of the selected cell.
                row = processList.getSelectedRow();
                column = processList.getSelectedColumn();
                
                //before trying to kill selected process it will check to see
                //if there is a process selected. if no process is selected and 
                //they click the kill button it will display the warning message.
                if (processList.getSelectedRow() != 0 && 
                        processList.getValueAt(row, column) != null ) {
                    
                    //pass the Process ID to the kill method
                    pID.killSelectedProcess(processList.getValueAt(row, column)
                            .toString());
                }
                else
                    JOptionPane.showMessageDialog(null, "No process was"
                            + " selected; not ending any tasks.", "Warning:"
                        , JOptionPane.OK_OPTION);
                break;
            }
    }
    
    /*
     * sets the JTable with the data for the program.
     * @param taskList is the information that it gets from the process
     * component. 
     */
    private void setJTable(Vector<String> taskList) {
        
        //add the number of rows to the jtable so it looks nice.
        DefaultTableModel dtm = (DefaultTableModel) processList.getModel();
        dtm.setRowCount(pID.getnumberOfTasks());
        
        //Set up the string that will go in to the text area
        String processes = "number of Processes: " + pID.getnumberOfTasks();
        
        //Set the number of processes in the text area.
        numberOfProcess.setText(processes);
        
        //verifies that the table has been set
        boolean hasBeenSet = false;
        int i = 0;
        String[] title = {"Image Name", "PID", "Session Name", "Session#",
            "Mem Usage (K)"};
        
        //this sets the title of the jtable "processList".
        for (int x = 0 ; x < 5; x++) {
            processList.getColumnModel().getColumn(x).setHeaderValue(title[x]);
        }        
        
        //does the heavy lifting of getting the data into the table.
        for(String words:taskList) {
            int j = 0;
            for(String word: words.split("\\s")) {
                if(word.matches("[a-zA-Z]+\\.exe") || word.contains("System") ||
                        word.matches("\\p{Digit}+") ||
                        word.matches("\\p{Digit}+\\,\\p{Digit}+") || 
                        word.contains("HP") || word.contains("Services") ||
                        word.contains("Console") ||
                        word.matches("[a-zA-Z]+[0-9]+\\.exe") ||
                        word.matches("[a-zA-Z]+\\-[a-zA-Z]+\\.exe") ) {
                    processList.setValueAt(word, i, j);
                    hasBeenSet = true;
                }
                if (j < 5 && j >= 0 && hasBeenSet){
                    j++;
                    hasBeenSet = false;
                }
            }
            if(words.contains(".exe") || words.contains("HP") 
                    || words.contains("System")) {
                i++;
            }
        }
    }
    
    /*
     * set the names of the connected devices as radio button
     */
//    private void setRadioButtons() {
//        
//        //
//        if (!pID.getComputerNames().isEmpty()){
//            //Does the heavy Lifting for the names of computers;
//            for (String lines: pID.getComputerNames()){
//                JRadioButton computerName = new JRadioButton(lines);
//                computerName.setActionCommand("goGetIt");
//                computerName.addActionListener(this);
//                nameOfComputers.add(computerName);
//            }
//        }
//        else
//            JOptionPane.showMessageDialog(null, "There aren't any"
//                    + "computers connected to this device!", "Warning:"
//                        , JOptionPane.OK_OPTION);
//    }
 
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
                .addComponent(nameOfComputers).addComponent(scrollerText)
                .addComponent(getProcess).addComponent(killTask)
                .addComponent(numberOfProcess));
        
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
