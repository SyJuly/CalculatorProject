import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class JulianDateTest {
	
	@Test
	public void today() {
		JulianDate jd = new JulianDate(2016,6,10);
		assertEquals(2457550 , jd.getJDate());
	}
	
	@Test
	public void randomDay() {
		JulianDate jd = new JulianDate(1444,6,10);
		assertEquals(2248640 , jd.getJDate());
	}
	
	@Test
	public void negativeYear1() {
		JulianDate jd = new JulianDate(-100,1,1);
		assertEquals(1684899 , jd.getJDate());
	}
	
	@Test
	public void negativeYear2() {
		JulianDate jd = new JulianDate(-40,10,10);
		assertEquals(1707096 , jd.getJDate());
	}
	
	@Test
	public void dayOfWeek() {
		JulianDate jd = new JulianDate(2016,6,10);
		assertEquals("Friday" , JulianDate.getDayOfWeek(jd.getJDate()));
	}

}
