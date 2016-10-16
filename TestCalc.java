import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestCalc {

	CalcEngine eng;
	
	@Before
	public void setUp() throws Exception {
		eng = new CalcEngine();
	}
	
	@Test
	public void clear(){
		eng.clear();
		assertEquals(0, eng.getDisplayValue());
	}
	
	@Test
	public void plusHex(){
		eng.numberPressed(1, 16);
		eng.plus();
		eng.numberPressed(10, 16);
		eng.equals();
		assertEquals(11, eng.getDisplayValue());
	}
	
	@Test
	public void minusHex(){
		eng.numberPressed(15, 16);
		eng.numberPressed(12, 16);
		eng.minus();
		eng.numberPressed(8, 16);
		eng.equals();
		assertEquals((15*16+12)-8, eng.getDisplayValue());
	}
	
	@Test
	public void multiplyHex(){
		eng.numberPressed(1, 16);
		eng.multiply();
		eng.numberPressed(10, 16);
		eng.equals();
		assertEquals(0x0A, eng.getDisplayValue());
	}
	
	@Test
	public void divideHex(){
		eng.numberPressed(15, 16);
		eng.divide();
		eng.numberPressed(8, 16);
		eng.equals();
		assertEquals(0x02, eng.getDisplayValue());
	}
	
	@Test
	public void plusDez(){
		eng.numberPressed(5);
		eng.plus();
		eng.numberPressed(7);
		eng.equals();
		assertEquals(0x0C, eng.getDisplayValue());
	}
	
	@Test
	public void minusDez(){
		eng.numberPressed(2);
		eng.numberPressed(5);
		eng.minus();
		eng.numberPressed(7);
		eng.equals();
		assertEquals(18, eng.getDisplayValue());
	}
	
	@Test
	public void multiplyDez(){
		eng.numberPressed(3);
		eng.multiply();
		eng.numberPressed(3);
		eng.equals();
		assertEquals(9, eng.getDisplayValue());
	}
	
	@Test
	public void divideDez(){
		eng.numberPressed(3);
		eng.divide();
		eng.numberPressed(4);
		eng.equals();
		assertEquals(1, eng.getDisplayValue());
	}
	
	@Test
	public void negativeNumberDez(){
		eng.numberPressed(2);
		eng.minus();
		eng.numberPressed(7);
		eng.equals();
		assertEquals(-5, eng.getDisplayValue());
	}
	
	@Test
	public void negativeNumberHex(){
		eng.numberPressed(2, 16);
		eng.minus();
		eng.numberPressed(3, 16);
		eng.equals();
		assertEquals(0xffffffff, eng.getDisplayValue());
	}
	

}
