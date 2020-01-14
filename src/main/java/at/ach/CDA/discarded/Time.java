package at.ach.CDA.discarded;

@Deprecated
@SuppressWarnings("unused")
public class Time
{ // TODO
	/*year
	month
	day
	hours
	minutes
	second
	timezone*/
	
	// TODO as an alternative to inner enums, you could also create inner classes, that just hold and validate an int
	
	/* Example of a time value used in CDA files:
	 * 
	 *    <time value="20121201121500+0100"/>
	 * 
	 * split up: 2012 12    01  12   15     00
	 *           year month day hour minute second
	 *           
	 *                                       +         01    00
	 *           Timezone (Offset from UTC): direction hours minutes
	 */
	
	private String year; // TODO: reconsider data type
	private Month month;
	private Day day;
	private Hour hour;
	private Minute minute;
	private Second second;
	//private Timezone timezone;
	private OffsetFromUTC timezone;
	
	Time(/*TODO*/)
	{
		// TODO
	}
	
	/*@Override
	toString() TODO*/
	
	private enum Month
	{
		January(1),
	    February(2),
	    March(3),
	    April(4),
	    May(5),
	    June(6),
	    July(7),
	    August(8),
	    September(9),
	    October(10),
	    November(11),
	    December(12);

	    private int month;

	    Month(int month)
	    {
	        this.month = month;
	    }

	    public int getMonthAsInt()
	    {
	        return this.month;
	    }
	    
	    public String getMonthAsString()
	    {
	    	return String.valueOf(this.month);
	    }
	}
	
	private enum Day
	{
		d1 ( 1),
		d2 ( 2),
		d3 ( 3),
		d4 ( 4),
		d5 ( 5),
		d6 ( 6),
		d7 ( 7),
		d8 ( 8),
		d9 ( 9),
		d10(10),
		d11(11),
		d12(12),
		d13(13),
		d14(14),
		d15(15),
		d16(16),
		d17(17),
		d18(18),
		d19(19),
		d20(20),
		d21(21),
		d22(22),
		d23(23),
		d24(24),
		d25(25),
		d26(26),
		d27(27),
		d28(28),
		d29(29),
		d30(30),
		d31(31);
		
		private int day;
		
	    Day(int day)
	    {
	        this.day = day;
	    }
		
	    public int getDayAsInt()
	    {
	        return this.day;
	    }
	    
	    public String getDayAsString()
	    {
	    	return String.valueOf(this.day);
	    }
	}
	
	private enum Hour
	{
		h1 ( 1),
		h2 ( 2),
		h3 ( 3),
		h4 ( 4),
		h5 ( 5),
		h6 ( 6),
		h7 ( 7),
		h8 ( 8),
		h9 ( 9),
		h10(10),
		h11(11),
		h12(12),
		h13(13),
		h14(14),
		h15(15),
		h16(16),
		h17(17),
		h18(18),
		h19(19),
		h20(20),
		h21(21),
		h22(22),
		h23(23);
		
		private int hour;
		
	    Hour(int hour)
	    {
	        this.hour = hour;
	    }
		
	    public int getHourAsInt()
	    {
	        return this.hour;
	    }
	    
	    public String getHourAsString()
	    {
	    	return String.valueOf(this.hour);
	    }
	}
	
	private enum Minute
	{
		m1 ( 1),
		m2 ( 2),
		m3 ( 3),
		m4 ( 4),
		m5 ( 5),
		m6 ( 6),
		m7 ( 7),
		m8 ( 8),
		m9 ( 9),
		m10(10),
		m11(11),
		m12(12),
		m13(13),
		m14(14),
		m15(15),
		m16(16),
		m17(17),
		m18(18),
		m19(19),
		m20(20),
		m21(21),
		m22(22),
		m23(23),
		m24(24),
		m25(25),
		m26(26),
		m27(27),
		m28(28),
		m29(29),
		m30(30),
		m31(31),
		m32(32),
		m33(33),
		m34(34),
		m35(35),
		m36(36),
		m37(37),
		m38(38),
		m39(39),
		m40(40),
		m41(41),
		m42(42),
		m43(43),
		m44(44),
		m45(45),
		m46(46),
		m47(47),
		m48(48),
		m49(49),
		m50(50),
		m51(51),
		m52(52),
		m53(53),
		m54(54),
		m55(55),
		m56(56),
		m57(57),
		m58(58),
		m59(59);
		
		private int minute;
		
	    Minute(int minute)
	    {
	        this.minute = minute;
	    }
		
	    public int getMinuteAsInt()
	    {
	        return this.minute;
	    }
	    
	    public String getMinuteAsString()
	    {
	    	return String.valueOf(this.minute);
	    }
	}
	
	private enum Second
	{
		s1 ( 1),
		s2 ( 2),
		s3 ( 3),
		s4 ( 4),
		s5 ( 5),
		s6 ( 6),
		s7 ( 7),
		s8 ( 8),
		s9 ( 9),
		s10(10),
		s11(11),
		s12(12),
		s13(13),
		s14(14),
		s15(15),
		s16(16),
		s17(17),
		s18(18),
		s19(19),
		s20(20),
		s21(21),
		s22(22),
		s23(23),
		s24(24),
		s25(25),
		s26(26),
		s27(27),
		s28(28),
		s29(29),
		s30(30),
		s31(31),
		s32(32),
		s33(33),
		s34(34),
		s35(35),
		s36(36),
		s37(37),
		s38(38),
		s39(39),
		s40(40),
		s41(41),
		s42(42),
		s43(43),
		s44(44),
		s45(45),
		s46(46),
		s47(47),
		s48(48),
		s49(49),
		s50(50),
		s51(51),
		s52(52),
		s53(53),
		s54(54),
		s55(55),
		s56(56),
		s57(57),
		s58(58),
		s59(59);
		
		private int second;
		
	    Second(int second)
	    {
	        this.second = second;
	    }
		
	    public int getSecondAsInt()
	    {
	        return this.second;
	    }
	    
	    public String getSecondAsString()
	    {
	    	return String.valueOf(this.second);
	    }
	}
	
	/*private enum Timezone
	{
		UTCminus1200(-1200),
		January(1),
	    February(2),
	    March(3),
	    April(4),
	    May(5),
	    June(6),
	    July(7),
	    August(8),
	    September(9),
	    October(10),
	    November(11),
	    December(12);

	    private int month;

	    Timezone(int month)
	    {
	        this.month = month;
	    }

	    public int getMonthAsInt()
	    {
	        return this.month;
	    }
	    
	    public String getMonthAsString()
	    {
	    	return String.valueOf(this.month);
	    }
	}*/
	
	private class OffsetFromUTC
	{
		private Direction direction;
		private Hour hours;
		private Second seconds;
		
		OffsetFromUTC()
		{
			// TODO
		}
		
		public String getAsString()
		{
			return this.direction.getDirectionAsString() + this.hours.getHourAsString() + this.seconds.getSecondAsString();
		}
		
		@Override
		public String toString()
		{
			return this.getAsString();
		}
	}
	
	private enum Direction
	{
		plus("+"),
		minus("-");
		
		private String direction;
		
		Direction(String direction)
		{
			this.direction = direction;
		}
		
		public String getDirectionAsString()
		{
			return this.direction;
		}
	}
}
