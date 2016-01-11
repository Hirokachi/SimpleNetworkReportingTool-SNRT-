/*
 * This class is the class that gets the processes 
 * from the computer it is running on.
 */
package snrt;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author Alex Gaskill
 */
public class ProcessComponent {
    
    /**
     * This method sets processList and returns that information to the 
     * method/class that called it.
     * @return: Will either return the process information, or "error: no 
     * processes" if it couldn't find any processes running.
     */
    public Vector<String> getProcesses() {
        
        // I create this vector as my get processes requires some way to pass 
        // the information it has obtained to the rest of the program.
        Vector<String> processList = new Vector<String>();
        
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
                    processList.addElement(line);
                }
                
                //closes the input stream, to make the input stream not leaking
                //data or anything.
                input.close();
                
                return (processList);
            }
        catch (Exception err) {
                    err.printStackTrace();
                    processList.addElement("Error: No Processes");
                    return (processList);
            }
        }
        
    /**
     * This method is to get the number tasks so that the jtable knows how many
     * rows of cells to place in the GUI.
     * @return: the number of tasks.
     */
    public int getnumberOfTasks () {
        int numberOfTasks = 0;
        String line;
        try{
            Process p = Runtime.getRuntime().exec
                        (System.getenv("windir") +"\\system32\\"+"tasklist.exe");
            BufferedReader input =
                        new BufferedReader(
                                new InputStreamReader(p.getInputStream()));
            while ((line = input.readLine()) != null) {
                    numberOfTasks++;
            }
            return (numberOfTasks);
        }
        catch (Exception err) {
            err.printStackTrace();
            return (0);
        }
                          
    }
    
    /**
     * This method will end the task of a selected process.
     * 
     * @param processName: the process string that has been selected through 
     * the GUIComponent and needs to be killed/ended.
     */
    public void killSelectedProcess (String processName) {
        
        try {
            // Create a runtime object so that when we try to kill a process
            // on the computer we are selecting it will be more successful
            // causing the computer to kill the process by verifying the
            // OS and running the command line in powershell.exe to kill it. 
            Runtime rt = Runtime.getRuntime();
            if (System.getProperty("os.name").contains("Windows")){
                if (processName.matches("[0-9]+")) {
                    rt.exec("powershell.exe taskkill /PID " + processName);
                }
                else if (processName.matches("[a-zA-Z]+\\.exe")){
                    rt.exec("powershell.exe taskkill /IM " + processName);
                }
                else {
                    JOptionPane.showMessageDialog(null, "The task you selected "
                            + "is neither a PID or the Image Name. Not ending "
                            + "any tasks.", "Error:"
                        , JOptionPane.OK_OPTION);
                }
            }
            else
                rt.exec("kill -9 " + processName);
        }
        catch (Exception err) {
            err.printStackTrace();
        }
    }
    
    public Vector<String> getComputerNames (){
        
        String line;
        
        //The vector that will contain the list of names
        Vector<String> computerNameList = new Vector<String>();
       
        
        try{
            Process p = Runtime.getRuntime().exec
                        (System.getenv("windir") +"\\system32\\"+"nbtstat -r");
            BufferedReader input =
                        new BufferedReader(
                                new InputStreamReader(p.getInputStream()));
            while ((line = input.readLine()) != null) {
                if (line.contains("<00>"))
                    computerNameList.add(line);
            }
            
            
            return (computerNameList);
        }
        catch (Exception err) {
            err.printStackTrace();
            return (null);
        }   
    }
}
