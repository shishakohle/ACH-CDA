package at.ach.CDA;

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

	Patient()
	{
		
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
}