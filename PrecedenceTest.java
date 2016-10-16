import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PrecedenceTest {

	CalcEngine calc;
	
	@Before
	public void setUp() throws Exception {
		 calc = new CalcEngine();
	}
	
	@Test
	public void test1() {
		calc.setDisplayValue("2*3+5");
		calc.calculateResult();
		assertEquals("11" , calc.getDisplayValue());
	}
	
	@Test
	public void test2() {
		calc.setDisplayValue("2*(3+5)");
		calc.calculateResult();
		String firstResult = calc.getDisplayValue();
		calc.setDisplayValue("2*3+5");
		calc.calculateResult();
		assertNotEquals(calc.getDisplayValue(), firstResult);
	}

	@Test
	public void test3() {
		calc.setDisplayValue("2^3*3");
		calc.calculateResult();
		assertEquals("24" , calc.getDisplayValue());
	}
	
	@Test
	public void test4() {
		calc.setDisplayValue("2*3^2");
		calc.calculateResult();
		assertEquals("18" , calc.getDisplayValue());
	}
	
	@Test
	public void test5() {
		calc.setDisplayValue("3^2+5*2");
		calc.calculateResult();
		assertEquals("19" , calc.getDisplayValue());
	}
	
	@Test
	public void test6() {
		calc.setDisplayValue("20^2/4");
		calc.calculateResult();
		assertEquals("100" , calc.getDisplayValue());
	}
	
	@Test
	public void test7() {
		calc.setDisplayValue("3*6+2*4");
		calc.calculateResult();
		assertEquals("26" , calc.getDisplayValue());
	}
	
	@Test
	public void test8() {
		calc.setDisplayValue("10+7*8");
		calc.calculateResult();
		assertEquals("66" , calc.getDisplayValue());
	}
	
	@Test
	public void test9() {
		calc.setDisplayValue("2+4*4+2");
		calc.calculateResult();
		assertEquals("20" , calc.getDisplayValue());
	}
	
	@Test
	public void test10() {
		calc.setDisplayValue("2*4+4*2");
		calc.calculateResult();
		assertEquals("16" , calc.getDisplayValue());
	}
	
	@Test
	public void test11() {
		calc.setDisplayValue("2^6*2^4");
		calc.calculateResult();
		assertEquals("1024" , calc.getDisplayValue());
	}
	
	@Test
	public void test12() {
		calc.setDisplayValue("3+2*2+3^3+2*4+5");
		calc.calculateResult();
		assertEquals("47" , calc.getDisplayValue());
	}
	
	@Test
	public void test13() {
		calc.setDisplayValue("3*2+2^3*3+2+4*5");
		calc.calculateResult();
		assertEquals("52" , calc.getDisplayValue());
	}
	
	@Test
	public void test14() {
		calc.setDisplayValue("9*9+19");
		calc.calculateResult();
		String firstResult = calc.getDisplayValue();
		calc.setDisplayValue("19+9*9");
		calc.calculateResult();
		assertEquals(calc.getDisplayValue() , firstResult);
	}
	
	@Test
	public void test15() {
		calc.setDisplayValue("3*2*3+4*2*4");
		calc.calculateResult();
		assertEquals("50" , calc.getDisplayValue());
	}
}