package com.flyaway.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="flightinfo")
public class flightInfo 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "finfoId")
	private int finfoId;
	
	@ManyToOne(optional = false)
    @JoinColumn(name="flightcode")
	private airlines flightCode;
	
	@ManyToOne(optional = false)
    @JoinColumn(name="srcId")
	private sourceLocations src;
	
	@ManyToOne(optional = false)
    @JoinColumn(name="destnId")
	private destnLocations destination;
	
	/*@Temporal(TemporalType.DATE)*/
	@Column(name = "traveldate")
	private LocalDate travelDate;
	
	@Column(name = "arrtime")
	private String arrTime;
	
	@Column(name = "deptime")
	private String deptTime;
	
	@Column(name = "totalseats")
	private int totalSeats;
	
	@Column(name = "availseats")
	private int availSeats;
	
	@Column(name = "price")
	private double price;
	
	@Column(name = "flightstatus")
	private String flightStatus;

	public flightInfo()
	{
		
	}

	public flightInfo(airlines flightCode, sourceLocations src, destnLocations destination, LocalDate travelDate,
			String arrTime, String deptTime, int totalSeats, int availSeats, double price, String flightStatus) {
		super();
		this.flightCode = flightCode;
		this.src = src;
		this.destination = destination;
		this.travelDate = travelDate;
		this.arrTime = arrTime;
		this.deptTime = deptTime;
		this.totalSeats = totalSeats;
		this.availSeats = availSeats;
		this.price = price;
		this.flightStatus = flightStatus;
	}

	public int getFinfoId() {
		return finfoId;
	}

	public void setFinfoId(int finfoId) {
		this.finfoId = finfoId;
	}

	public airlines getFlightCode() {
		return flightCode;
	}

	public void setFlightCode(airlines flightCode) {
		this.flightCode = flightCode;
	}

	public sourceLocations getSrc() {
		return src;
	}

	public void setSrc(sourceLocations src) {
		this.src = src;
	}

	public destnLocations getDestination() {
		return destination;
	}

	public void setDestination(destnLocations destination) {
		this.destination = destination;
	}

	public LocalDate getTravelDate() {
		return travelDate;
	}

	public void setTravelDate(LocalDate travelDate) {
		this.travelDate = travelDate;
	}

	public String getArrTime() {
		return arrTime;
	}

	public void setArrTime(String arrTime) {
		this.arrTime = arrTime;
	}

	public String getDeptTime() {
		return deptTime;
	}

	public void setDeptTime(String deptTime) {
		this.deptTime = deptTime;
	}

	public int getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getAvailSeats() {
		return availSeats;
	}

	public void setAvailSeats(int availSeats) {
		this.availSeats = availSeats;
	}

	
	public String getFlightStatus() {
		return flightStatus;
	}

	public void setFlightStatus(String flightStatus) {
		this.flightStatus = flightStatus;
	}

	@Override
	public String toString() {
		return "flightInfo [finfoId=" + finfoId + ", flightCode=" + flightCode + ", src=" + src + ", destination="
				+ destination + ", travelDate=" + travelDate + ", arrTime=" + arrTime + ", deptTime=" + deptTime
				+ ", totalSeats=" + totalSeats + ", availSeats=" + availSeats + ", price=" + price + ", flightStatus="
				+ flightStatus + "]";
	}

}
