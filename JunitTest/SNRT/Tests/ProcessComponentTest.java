/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SNRT.Tests;

import static org.hamcrest.core.IsNot.not;
import snrt.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Vector;

/**
 *
 * @author Alex Gaskill
 */
public class ProcessComponentTest {
    
    private ProcessComponent processing;
    
    @Before
    public void runBeforeEachTest() {
        processing = new ProcessComponent();
    }
    
    @Test
    public void TestGetProcesses() {
        //asserts that the process unit gets data.
        assertNotNull(processing.getProcesses());
        
        //Asserts that the message "Error: No Processes" is not what you get
        //get when you run the method "getProcesses"
        assertNotSame("Error: No Processes", processing.getProcesses());
    }
    
    @Test
    public void TestGetComputerNames() {
        //Asserting that the getComputerNames will not be null.
        assertNotNull(processing.getComputerNames());
    }
    
    @Test
    public void TestGetComputerNames2() {
        // Creates a empty vector of strings called Empty.
        Vector<String> Empty = new Vector<String> ();
        
        //Asserts that the empty vector is empty when it can't find any connected
        //computers or seeable computers.
        assertEquals("If no connected computers it must be empty but not null",
                Empty, processing.getComputerNames());
    }
}
