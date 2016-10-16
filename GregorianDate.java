
public class GregorianDate {

	int year;
	int month;
	int day;

	public GregorianDate(int jDate) {
		int jalpha,ja,jb,jc,jd,je,year,month,day;
		   ja = (int) jDate;
		   if (ja<1721424){
			   ja = ja+2;
		   }
		   if (ja>= (15 + 31*(10+12*1582))) {    
		     jalpha = (int) (((ja - 1867216) - 0.25) / 36524.25);
		     ja = ja + 1 + jalpha - jalpha / 4;
		   }
		    
		   jb = ja + 1524;
		   jc = (int) (6680.0 + ((jb - 2439870) - 122.1) / 365.25);
		   jd = 365 * jc + jc / 4;
		   je = (int) ((jb - jd) / 30.6001);
		   day = jb - jd - (int) (30.6001 * je);
		   month = je - 1;
		   if (month > 12) month = month - 12;
		   year = jc - 4715;
		   if (month > 2) year--;
		   if (year <= 0) year--;
		   this.year = year;
		   this.month = month;
		   this.day = day;
	}
	
	@Override
	public String toString(){
		return "G"+day+"."+month+"."+year;
	}

}
