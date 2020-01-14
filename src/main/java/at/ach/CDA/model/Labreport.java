package at.ach.CDA.model;

import java.util.ArrayList;
import java.util.List;

import at.ach.CDA.converter.CDALabreportParser;

public class Labreport
{
	private Patient patient;
	private List<Observation> observations = new ArrayList<Observation>();
	
	// TODO: add further info concerning the labreport
	
	public Labreport(Patient patient, List<Observation> observations)
	{
		this.patient = patient;
		if (observations!=null) this.observations = observations;
	}
	
	public Labreport()
	{
	}
	
	public Labreport(String cdaFilepath)
	{
		Labreport parsed = CDALabreportParser.extractLabreport(cdaFilepath);
		this.patient = parsed.getPatient();
		this.observations = parsed.getObservations();
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	public boolean hasPatient()
	{
		return patient != null ? true : false;
	}

	public List<Observation> getObservations() {
		return observations;
	}

	public void setObservations(List<Observation> observations) {
		this.observations = observations;
	}
}
