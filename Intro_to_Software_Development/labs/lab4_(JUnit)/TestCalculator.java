/*
	Eric Paulz - epaulz
	CPSC 2151-003
	Lab 4
*/

package cpsc2150.lab4;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by epaulz on 9/19/2017.
 */
public class TestCalculator extends TestCase {
    private Calculator calc;

    @Before // runs before each test marked with a @Test annotation
    public void setUp(){ calc = new Calculator(); }

    @After // runs after each method marked with a @Test annotation
    public void tearDown(){ calc = null; }

    @Test
    public void test3_4Add(){
        assertEquals("testing add(3,4)", 7.0, calc.add(3,4));
    }
	 @Test
	 public void test10_25Add(){
		  assertEquals("testing add(10,25)", 35.0, calc.add(10,25));
	 }
	 @Test
	 public void test2pt25_6pt35Add(){
        assertEquals("testing add(2.25,6.35)", 8.60, calc.add(2.25,6.35));
	 }
    @Test
	 public void test0_100Mult(){
		  assertEquals("testing mult(0,100)", 0.0, calc.mult(0,100));
	 }
	 @Test
	 public void test1pt5_2pt5Mult(){
        assertEquals("testing mult(1.5,2.5)", 3.75, calc.mult(1.5,2.5));
    }
	 @Test
	 public void test5_neg10Mult(){ 
        assertEquals("testing mult(5,-10)", -50.0, calc.mult(5,-10));
	 }
	 @Test
	 public void test50_35Subtract(){
        assertEquals("testing subtract(50,35)", 15.0, calc.subtract(50,35));
    }
	 @Test
	 public void test11pt75_15Subtract(){
        assertEquals("testing subtract(11.75,15)", -3.25, calc.subtract(11.75,15));
	 }
	 @Test
	 public void test1000_999Subtract(){
        assertEquals("testing subtract(1000,999)", 1.0, calc.subtract(1000,999));
	 }
	 @Test
	 public void test100_25Divide(){
        assertEquals("testing divide(100,25)", 4.0, calc.divide(100,25));
    }
	 @Test
	 public void test10_neg3pt5(){
        assertEquals("testing divide(10,-3.5)", -2.857142857142857, calc.divide(10,-3.5));
    }
	 @Test
	 public void test28pt75_11pt5(){
        assertEquals("testing divide(28.75,11.5)", 2.5, calc.divide(28.75,11.5));
    }
}
