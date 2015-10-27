/*
 * This class is the class that gets the processes 
 * from the computer it is running on.
 */
package snrt;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Vector;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author Alex Gaskill
 */
public class ProcessComponent {
    
    /**
     * This method sets processInfo and returns that information to the 
     * method/class that called it.
     * @return: Will either return the process information, or "error: no 
     * processes" if it couldn't find any processes running.
     */
    public Vector getProcesses(){
        
        Vector processList;
        processList = new Vector();
        
        try {
                String line;
         
                // Gets the processes. If the os is windows do windir. otherwise
                // get the process by the string "ps -e"
                Process p;
                if (System.getProperty("os.name").contains("Windows"))
                     p = Runtime.getRuntime().exec
                        (System.getenv("windir") +"\\system32\\"+"tasklist.exe");
                else
                     p = Runtime.getRuntime().exec("ps -e"); 
                BufferedReader input =
                        new BufferedReader(new InputStreamReader(p.getInputStream()));
                while ((line = input.readLine()) != null) {
                    processList.addElement(line + "\n");
                }
                input.close();
                return (processList);
            }
        catch (Exception err) {
                    err.printStackTrace();
                    processList.addElement("Error");
                    return (processList);
            }
        }
        
    /**
     * This method will end the task of a selected process on the GUI-
     * Component side. A little more work should be done here to get the PID
     * from the string of the process selected.
     * 
     * @param processInfo: the process string that has been selected through 
     * the GUIComponent and needs to be killed/ended.
     */
    public void killSelectedProcess (String[] processInfo) {

        String processID = "NAN";

        //find the PID from the processInfo String.
        for (String correctPID : processInfo) {
            if(Pattern.matches("[0-9]+" ,correctPID)) {
                processID = correctPID;
                break;
            }
        }
        
        try {
            // Create a runtime object so that when we try to kill a process
            // on the computer we are selecting it will be more successful
            // causing the computer to kill the process by verifying the
            // OS and running the command line to kill it. 
            Runtime rt = Runtime.getRuntime();
            rt.exec("taskkill " + processID);
        }
        catch (Exception err) {
            err.printStackTrace();
            JOptionPane.showMessageDialog(null, "No Process ID was "
                   + "found or Process was already ended" , "EndTask Error:"
                    , JOptionPane.OK_OPTION);
        }
    }
}
