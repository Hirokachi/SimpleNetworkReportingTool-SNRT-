/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snrt;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.*;

/**
 *
 * @author Alex Gaskill
 */
public class SNRT {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame();
        
        // Adding the dementions and title of the window used for the reporting
        // Tool
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Simple Network Reporting Tool ");
        frame.setLocation(500,400);
        frame.setSize(500,500);
        
        JPanel panel = new JPanel(new GridLayout(1,10){
            //code from ramayac on stackover flow... begin imported code...
            
            String line;
            try {
            Process p = Runtime.getRuntime().exec
                (System.getenv("windir") +"\\system32\\"+"tasklist.exe");
            BufferedReader input =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((line = input.readLine()) != null) {
                add(line, frame);
            }
            input.close();
        }
            catch (Exception err)
            {
                err.printStackTrace();
            }
        };
                // end of imported code...
                
        });
 

    public SNRT() {
    }
 }