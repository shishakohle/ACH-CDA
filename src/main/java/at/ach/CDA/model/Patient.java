package at.ach.CDA.model;

public class Patient
{
	private String socialInsuranceNumber;
	private String givenName;
	private String familyName;
	private String gender;
	private String birthdate;
	
	public Patient(String socialInsuranceNumber, String givenName, String familyName, String gender, String birthdate)
	{
		this.socialInsuranceNumber = socialInsuranceNumber;
		this.givenName = givenName;
		this.familyName = familyName;
		this.gender = gender;
		this.birthdate = birthdate;
	}

	public Patient() {
		// TODO Auto-generated constructor stub
	}

	public void setSocialInsuranceNumber(String socialInsuranceNumber) {
		this.socialInsuranceNumber = socialInsuranceNumber;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getSocialInsuranceNumber()
	{
		return this.socialInsuranceNumber;
	}

	public String getGivenName()
	{
		return this.givenName;
	}

	public String getFamilyName()
	{
		return this.familyName;
	}

	public String getGender()
	{
		return this.gender;
	}

	public String getBirthdate()
	{
		return this.birthdate;
	}
	
	@Override
    public String toString() 
	{
        return givenName + " " + familyName;
    }
	
}