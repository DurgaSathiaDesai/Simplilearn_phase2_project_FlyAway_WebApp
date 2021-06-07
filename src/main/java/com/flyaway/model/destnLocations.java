package com.flyaway.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="destnlocation")
public class destnLocations 
{
	@Id
	@Column(name = "destnId")
	String destnId;
	
	@Column(name = "destnname")
	String destnname;

		public destnLocations()
	{
		
	}


	public destnLocations(String destnId, String destnname) {
			super();
			this.destnId = destnId;
			this.destnname = destnname;
		}


	public String getDestnId() {
		return destnId;
	}


	public void setDestnId(String destnId) {
		this.destnId = destnId;
	}


	public String getDestnname() {
		return destnname;
	}


	public void setDestnname(String destnname) {
		this.destnname = destnname;
	}


	@Override
	public String toString() {
		return "destnLocations [destnId=" + destnId + ", destnname=" + destnname + "]";
	}


}
