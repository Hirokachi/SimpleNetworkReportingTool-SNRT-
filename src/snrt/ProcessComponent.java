/*
 * This class is the class that gets the processes 
 * from the computer it is running on.
 */
package snrt;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Vector;
/*import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
*/
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
                rt.exec("powershell.exe taskkill /IM " + processName);
            }
            else
                rt.exec("kill -9 " + processName);
        }
        catch (Exception err) {
            err.printStackTrace();
        }
    }
    
    /**
     * 
     * @return 
     */
    /*public Vector nextComputer() {
        
        String hostName;
        String hostIP;
        Vector hostInfo = new Vector();
        Vector hostError = new Vector();
        hostError.add("Error");
        
        try {
            //find the host name and ip v4 address and throw that into a string
            InetAddress addr;
            addr = InetAddress.getLocalHost();
            hostName = addr.getHostName();
            hostIP = Inet4Address.getLocalHost().toString();
            
            //add the hostIP and hostIP that was aquired into a vector.
            hostInfo.add(hostIP);
            hostInfo.add(hostName);
            
            return (hostInfo);
        }
        catch (Exception err) {
            err.printStackTrace();
            
            //return an one line vector that says error.
            return (hostError);
        }
        
    }*/
}
