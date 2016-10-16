import java.util.Calendar;

public class JulianDate implements Date{

	private int jdate;
	
	public JulianDate(int year, int month, int day) {
		if (month<1 || month>12 || day<0 || day>31){
			throw new IllegalArgumentException("False Arguments");
		}
		int jyear;
		int jmonth;
		int jday;
		int a = 0;
		int b = 0;
		if (month > 2){
			jyear = year;
			jmonth = month;
		} else{
			jyear = year-1;
			jmonth = month+12;
		}
		jday = day;
		
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		Calendar c3 = Calendar.getInstance();
		c1.set(jyear, jmonth, jday);
		c2.set(1582, 10, 15);
		c3.set(1582, 10, 04);
		
		if (c1.compareTo(c2) >= 0){
			a = (int)jyear/100;
			b = (int)(2 - a + a/4);
		} else if (c1.compareTo(c3) <= 0){
			b = 0;	
		} else {
			throw new IllegalArgumentException("This date doesn't exist!");
		}
		double temp = (365.25*(jyear+4716) + Math.floor(30.6001*(jmonth+1)) + jday + b - 1524.5);
		if(year<0){
			temp = temp+365.25;
		}
		jdate = (int)Math.round(temp);
	}

	public int getJDate(){
		return jdate;
	}
	
	public int getTomorrow(){
		return jdate+1;
	}
	
	public int getYesterday(){
		return jdate-1;
	}
	
	public int getDifference(JulianDate j){
		if (jdate == j.getJDate())
			return 0;
		else
			return Math.abs(jdate-j.getJDate());
	}
	
	public static String getDayOfWeek(int jdate){
		String dwS = "";
		int dwN =jdate%7;
		switch(dwN){ 
        case 0: 
            dwS +="Monday"; 
            return dwS; 
        case 1: 
        	dwS +="Tuesday"; 
        	return dwS;
        case 2: 
        	dwS +="Wednesday"; 
        	return dwS;
        case 3: 
        	dwS +="Thursday"; 
        	return dwS;
        case 4: 
        	dwS +="Friday"; 
        	return dwS;
        case 5: 
        	dwS +="Saturday"; 
        	return dwS;
        case 6: 
        	dwS +="Sunday"; 
        	return dwS;
        default:
        	return "Error";
		}
	}
	
	@Override
	public String toString(){
		return "Julian date: "+jdate;
	}
	
	@Override 
	public boolean equals(Object o){
		if(!(o instanceof JulianDate))
			return false;
		JulianDate j = (JulianDate)o;
		if (jdate == j.getJDate())
			return true;
		else
			return false;
	}
	
}
