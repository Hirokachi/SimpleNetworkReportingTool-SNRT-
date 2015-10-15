/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snrt;
import java.awt.GridLayout;
import javax.swing.*;
import snrt.getPID;

/**
 *
 * @author Alex Gaskill
 */
public class SNRT {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        getPID pID = new getPID();
        System.out.println(pID.getProcesses());

    }
}


