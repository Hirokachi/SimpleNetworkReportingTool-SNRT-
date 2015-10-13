/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snrt;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.swing;

/**
 *
 * @author Alex Gaskill
 */
public class SNRT {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //code from ramayac on stackover flow... begin imported code...
            try {
        String line;
         Process p = Runtime.getRuntime().exec
        (System.getenv("windir") +"\\system32\\"+"tasklist.exe");
        BufferedReader input =
                new BufferedReader(new InputStreamReader(p.getInputStream()));
        while ((line = input.readLine()) != null) {
            System.out.println(line); //<-- Parse data here.
        }
        input.close();
    } 
            catch (Exception err)
            {
                err.printStackTrace();
            }
        }
    // end of imported code...
 }