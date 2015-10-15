/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
