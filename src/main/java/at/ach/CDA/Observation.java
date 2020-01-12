package at.ach.CDA;

public class Observation
{
	// effective time
	// code
	// interpretationCode
	// reference range
	
	/*String displayname;
	String value; // TODO: discuss whether we need calculations? therefore change data type to float or sth?
	Time effectiveTime;*/
	

	private String codeCode;
	private String codeSystem;
	private String codeSystemName;
	private String displayName;
	private Time effectiveTimeValue;
	private String valueUnit;
	private int valueValue;

	
	public Observation(String codeCode, String codeSystem, String codeSystemName, String displayName,
						Time effectiveTimeValue, String valueUnit, int valueValue)
	{
		this.codeCode = codeCode;
		this.codeSystem = codeSystem;
		this.codeSystemName = codeSystemName;
		this.displayName = displayName;
		this.effectiveTimeValue = effectiveTimeValue;
		this.valueUnit = valueUnit;
		this.valueValue = valueValue;
	}
	
	public void setValueUnit(String newUnit) 
	{
		this.valueUnit = newUnit;
	}
	
	public void setValue(int newValue) 
	{
		this.valueValue = newValue;
	}
	
	public void setTime(Time newEffectiveTime) 
	{
		this.effectiveTimeValue = newEffectiveTime;
	}
	
	public String codeCode() 
	{
		return this.codeCode;
	}
	
	public String codeSystem()
	{
		return this.codeSystem;
	}
	
	public String codeSystemName()
	{
		return this.codeSystemName;
	}
	
	public String displayName()
	{
		return this.displayName;
	}
	
	public Time effectiveTimevalue() 
	{
		return this.effectiveTimeValue;
	} 
	
	public String valueUnit()
	{
		return this.valueUnit;
	}
	
	public int valueValue() 
	{
		return this.valueValue;
	}
	
	
	@Override
	public String toString() 
	{
		return displayName;
	}
}
