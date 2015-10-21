/*
 * This class is the class that gets the processes 
 * from the computer it is running on.
 */
package snrt;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.StringBuilder;

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
         * Component side. 
         * 
         * @param process: the process id that has been selected through the
         * GUIComponent and needs to be killed/ended.
         */
        public void killSelectedProcess (int process){
            try{
                Runtime rt = Runtime.getRuntime();
                if (System.getProperty("os.name").contains("windows")) 
                    rt.exec("taskkill " + process);
                else
                    rt.exec("kill -9 " + process);
            }
            catch (Exception err){
                err.printStackTrace();
            }
        }
}
