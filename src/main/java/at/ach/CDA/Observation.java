package at.ach.CDA;

public class Observation
{
	private String codeCode;
	private String codeSystem;
	private String codeSystemName;
	private String displayName;
	private String effectiveTimeValue;
	private String valueUnit;
	private String valueValue;

	
	public Observation()
	{
	}
	
	public Observation(String codeCode, String codeSystem, String codeSystemName, String displayName,
						String effectiveTimeValue, String valueUnit, String valueValue)
	{
		this.codeCode = codeCode;
		this.codeSystem = codeSystem;
		this.codeSystemName = codeSystemName;
		this.displayName = displayName;
		this.effectiveTimeValue = effectiveTimeValue;
		this.valueUnit = valueUnit;
		this.valueValue = valueValue;
	}

	public String getCodeCode() {
		return codeCode;
	}

	public void setCodeCode(String codeCode) {
		this.codeCode = codeCode;
	}

	public String getCodeSystem() {
		return codeSystem;
	}

	public void setCodeSystem(String codeSystem) {
		this.codeSystem = codeSystem;
	}

	public String getCodeSystemName() {
		return codeSystemName;
	}

	public void setCodeSystemName(String codeSystemName) {
		this.codeSystemName = codeSystemName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getEffectiveTimeValue() {
		return effectiveTimeValue;
	}

	public void setEffectiveTimeValue(String effectiveTimeValue) {
		this.effectiveTimeValue = effectiveTimeValue;
	}

	public String getValueUnit() {
		return valueUnit;
	}

	public void setValueUnit(String valueUnit) {
		this.valueUnit = valueUnit;
	}

	public String getValueValue() {
		return valueValue;
	}

	public void setValueValue(String valueValue) {
		this.valueValue = valueValue;
	}
}
