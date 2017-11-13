/*
 * Travis Ferguson - Apache 2.0 Licensed
 */
package com.overwrittenstack.raceday.helper;

import com.overwrittenstack.raceday.model.Vehicle;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Travis
 */
public class RaceRandomizerTests {
    
    public RaceRandomizerTests() {
    }
    
    
    @Test
    public void testByes() {
        assertEquals(-1, RaceRandomizer.getNumberOfByes(null));
        assertEquals(-1, RaceRandomizer.getNumberOfByes(new ArrayList<Vehicle>()));
        ArrayList<Vehicle> tests = new ArrayList<>();
        tests.add(new Vehicle());
        System.out.println("--Size 1");
        assertEquals(-1, RaceRandomizer.getNumberOfByes(tests));
        
        tests.add(new Vehicle());
        System.out.println("--Size 2");
        assertEquals(0, RaceRandomizer.getNumberOfByes(tests));
        
        tests.add(new Vehicle());
        System.out.println("--Size 3");
        assertEquals(1, RaceRandomizer.getNumberOfByes(tests));
        
        tests.add(new Vehicle());
        System.out.println("--Size 4");
        assertEquals(0, RaceRandomizer.getNumberOfByes(tests));
        
        tests = new ArrayList<>();
        for(int i = 0; i < 30; i++) {
            tests.add(new Vehicle());
        }
        assertEquals(2, RaceRandomizer.getNumberOfByes(tests));
        
        
        tests = new ArrayList<>();
        for(int i = 0; i < 64; i++) {
            tests.add(new Vehicle());
        }
        assertEquals(0, RaceRandomizer.getNumberOfByes(tests));
        
        
        tests = new ArrayList<>();
        for(int i = 0; i < 50; i++) {
            tests.add(new Vehicle());
        }
        assertEquals(14, RaceRandomizer.getNumberOfByes(tests));
        
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
