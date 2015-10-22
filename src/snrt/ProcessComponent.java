/*
 * This class is the class that gets the processes 
 * from the computer it is running on.
 */
package snrt;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.StringBuilder;
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
    public String getProcesses(){
            
        try {
                StringBuilder controller = new StringBuilder("");
                String line;
                String processInfo;
         
                // Gets the processes.
                Process p = Runtime.getRuntime().exec
                    (System.getenv("windir") +"\\system32\\"+"tasklist.exe");
                BufferedReader input =
                        new BufferedReader(new InputStreamReader(p.getInputStream()));
                while ((line = input.readLine()) != null) {
                    controller.append(line + "\n");
                }
                input.close();
                processInfo = controller.toString();
                return (processInfo);
            }
            catch (Exception err) {
                    err.printStackTrace();
                    return ("Error: No Processes");
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
            if(correctPID.equals("[0-9]+"))
                processID = correctPID;
        }
        
        //Created case to not cause a fatal error in the OS with no Process ID.
        //Otherwise, go back to trying to end the task of selected Process ID. 
        if(processID.equals("NAN")) {
               JOptionPane.showMessageDialog(null, "No Process ID was "
                       + "found; was no process selected?", "EndTask Error:",
                       JOptionPane.OK_OPTION);
        }
        else {
            try {
                // Create a runtime object so that when we try to kill a process
                // on the computer we are selecting it will be more successful
                // causing the computer to kill the process by verifying the
                // OS and running the command line to kill it. 
                Runtime rt = Runtime.getRuntime();
                if (System.getProperty("os.name").contains("windows")) 
                    rt.exec("taskkill " + processID);
                else
                    rt.exec("kill -9 " + processID);
            }
            catch (Exception err) {
                err.printStackTrace();
                JOptionPane.showMessageDialog(null, "No Process ID was "
                       + "found or Process was already ended" , "EndTask Error:"
                        , JOptionPane.OK_OPTION);
            }
        }
    }
}
