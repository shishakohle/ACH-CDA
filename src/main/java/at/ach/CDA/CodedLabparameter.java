package at.ach.CDA;

public class CodedLabparameter
{
	private String codeSystem;       // e.g. 2.16.840.1.113883.6.1
	private String codeSystemName;   // e.g. LOINC
	private String parameterCode;    // e-g- 26464-8
	private String displayName;      // e.g. Leukozyten
//	private String valueUnit;        // e.g. G/L
	
	public CodedLabparameter(String codeSystem, String codeSystemName, String parameterCode, String displayName,
			String valueUnit) {
		this.codeSystem = codeSystem;
		this.codeSystemName = codeSystemName;
		this.parameterCode = parameterCode;
		this.displayName = displayName;
		/*this.valueUnit = valueUnit*/;
	}
	
	public CodedLabparameter()
	{
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

	public String getParameterCode() {
		return parameterCode;
	}

	public void setParameterCode(String parameterCode) {
		this.parameterCode = parameterCode;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/*public String getValueUnit() {
		return valueUnit;
	}

	public void setValueUnit(String valueUnit) {
		this.valueUnit = valueUnit;
	}*/
}
