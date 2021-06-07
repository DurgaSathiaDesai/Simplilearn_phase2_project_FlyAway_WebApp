package com.flyaway.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="srclocation")
public class sourceLocations 
{
	@Id
	@Column(name = "srcId")
	String srcId;
	
	@Column(name = "srcname")
	String srcname;

	public sourceLocations()
	{
		
	}

	public sourceLocations(String srcId, String srcname) {
		super();
		this.srcId = srcId;
		this.srcname = srcname;
	}

	public String getSrcId() {
		return srcId;
	}

	public void setSrcId(String srcId) {
		this.srcId = srcId;
	}

	public String getSrcname() {
		return srcname;
	}

	public void setSrcname(String srcname) {
		this.srcname = srcname;
	}

	@Override
	public String toString() {
		return "sourceLocations [srcId=" + srcId + ", srcname=" + srcname + "]";
	}

	
	
	
}
