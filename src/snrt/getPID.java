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
public class getPID {
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
                catch (Exception err)
                {
                    err.printStackTrace();
                    return ("false");
                }
        }
}
