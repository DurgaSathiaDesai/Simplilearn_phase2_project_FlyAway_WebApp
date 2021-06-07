package com.flyaway.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="airlines")
public class airlines 
{
	@Id
	@Column(name = "Aid")
	String airlineId;
	
	@Column(name = "Aname")
	String airlinename;
	
	@Column(name = "Astatus")
	String airlinestatus;
	
	public airlines()
	{
		
	}

	public airlines(String airlineId, String airlinename, String airlinestatus) {
		super();
		this.airlineId = airlineId;
		this.airlinename = airlinename;
		this.airlinestatus = airlinestatus;
	}

	public String getAirlineId() {
		return airlineId;
	}

	public void setAirlineId(String airlineId) {
		this.airlineId = airlineId;
	}

	public String getAirlinename() {
		return airlinename;
	}

	public void setAirlinename(String airlinename) {
		this.airlinename = airlinename;
	}

	
	public String getAirlinestatus() {
		return airlinestatus;
	}

	public void setAirlinestatus(String airlinestatus) {
		this.airlinestatus = airlinestatus;
	}

	@Override
	public String toString() {
		return "airlines [airlineId=" + airlineId + ", airlinename=" + airlinename + ", airlinestatus=" + airlinestatus
				+ "]";
	}
	
}
