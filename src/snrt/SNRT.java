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
import snrt.getPID;
import snrt.GUIClass;

/**
 *
 * @author Alex Gaskill
 */
public class SNRT {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args){
        // Creates a new instance of the GUIClass for later use.
        GUIClass display = new GUIClass();
        // Creates a new instance of the getPID class for later use.
        getPID pID = new getPID();
        // Prints the processes out to the command line after running the method
        System.out.println(pID.getProcesses());
    }
}
