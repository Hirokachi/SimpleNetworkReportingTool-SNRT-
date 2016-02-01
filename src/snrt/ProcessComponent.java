/*
 * This class is the class that gets the processes 
 * from the computer it is running on.
 */
package snrt;

import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
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
    public ArrayDeque<String> getProcesses() {
        
        // I create this vector as my get processes requires some way to pass 
        // the information it has obtained to the rest of the program.
        ArrayDeque<String> processList = new ArrayDeque();
        
        try {
                String line;
                
                // Gets the processes. If the os is windows do windir. otherwise
                // get the process by the string "ps -e"
                Process p;
                if (System.getProperty("os.name").contains("Windows"))
                     p = Runtime.getRuntime().exec
                        (System.getenv("windir") +"\\system32\\"+"tasklist.exe");
                else
                     p = Runtime.getRuntime().exec("ps -A"); 
            try (BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
                while ((line = input.readLine()) != null) {
                    processList.add(line);
                }
            }
                
                return (processList);
            }
        catch (Exception err) {
                    processList.add("Error: No Processes" + err);
                    return (processList);
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
        catch (IOException | HeadlessException err) {
            JOptionPane.showMessageDialog(null, "could not kill task either an"
                    + "IO exception or a HeadlessException occured", "Error:"
                        , JOptionPane.OK_OPTION);
        }
    }
    
    public ArrayDeque<String> getComputerNames (){
        
        String line;
        
        //The vector that will contain the list of names
        ArrayDeque<String> computerNameList = new ArrayDeque();
        
        try{
            Process p;
            if (System.getProperty("os.name").contains("Windows")) {
                p = Runtime.getRuntime().exec
                            (System.getenv("windir") +"\\system32\\"+"nbtstat -r");
            }
            else {
                p = Runtime.getRuntime().exec("nmbstat -M");
            } 
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
            return (null);
        }   
    }
    
    /**
     * Different process for remote computer.
     * @param computerName
     * @param user
     * @param pwd
     * @return 
     */
    public ArrayDeque<String> getProcesses(Object computerName, String user
            , char[] pwd) {     
        
        // I create this vector as my get processes requires some way to pass 
        // the information it has obtained to the rest of the program.
        ArrayDeque<String> processList = new ArrayDeque();
        
        try {
            String line;

            // Gets the processes. If the os is windows do windir. otherwise
            // get the process by the string "ps -e"
            Process p;
            if (System.getProperty("os.name").contains("Windows"))
                 p = Runtime.getRuntime().exec
                    (System.getenv("windir") +"\\system32\\"+"tasklist.exe \\S " 
                            + computerName +" \\U "+ user +"\\P"+
                            Arrays.toString(pwd));
            else {
                p = Runtime.getRuntime().exec("ps aux -a"); 
            }
            
            try (BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
                while ((line = input.readLine()) != null) {
                    processList.add(line);
                }
            }

            return (processList);
                
        }
        catch (Exception err) {
                processList.add("Error: No Processes" + err);
                return (processList);
        }
    }
}