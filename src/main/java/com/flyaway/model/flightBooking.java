package com.flyaway.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="flightbooking")
public class flightBooking 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "bookingId")
	private int bookingId;
	
	@Column(name = "flightcode")
	private String flightCode;
	
	@Column(name = "srcId")
	private String src;
	
	@Column(name = "destnId")
	private String destination;
	
	@Column(name = "traveldate")
	private LocalDate travelDate;
	
	@Column(name = "arrtime")
	private String arrTime;
	
	@Column(name = "deptime")
	private String deptTime;
	
	@Column(name = "passengers")
	private int noOfPassengers;
	
	@Column(name = "price")
	private double price;
	
	@Column(name = "Firstname")
	private String fName;
	
	@Column(name = "Lastname")
	private String lName;
	
	@Column(name = "Emailid")
	private String emailId;
	
	@Column(name = "Contactno")
	private String contactNo;
	
	@Column(name = "bookingdate")
	private LocalDateTime bookingDate;
	
	@Column(name = "bookingstatus")
	private String bookingStatus;
	
	@OneToOne(optional = false)
    @JoinColumn(name="paymentId")
	private paymentInfo pinfo;

	public flightBooking()
	{
		
	}

	public flightBooking(String flightCode, String src, String destination, LocalDate travelDate, String arrTime,
			String deptTime, int noOfPassengers, double price, String fName, String lName, String emailId,
			String contactNo, LocalDateTime bookingDate, String bookingStatus, paymentInfo pinfo) {
		super();
		this.flightCode = flightCode;
		this.src = src;
		this.destination = destination;
		this.travelDate = travelDate;
		this.arrTime = arrTime;
		this.deptTime = deptTime;
		this.noOfPassengers = noOfPassengers;
		this.price = price;
		this.fName = fName;
		this.lName = lName;
		this.emailId = emailId;
		this.contactNo = contactNo;
		this.bookingDate = bookingDate;
		this.bookingStatus = bookingStatus;
		this.pinfo = pinfo;
	}



	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public String getFlightCode() {
		return flightCode;
	}

	public void setFlightCode(String flightCode) {
		this.flightCode = flightCode;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public LocalDate getTravelDate() {
		return travelDate;
	}

	public void setTravelDate(LocalDate travelDate) {
		this.travelDate = travelDate;
	}

	public int getNoOfPassengers() {
		return noOfPassengers;
	}

	public void setNoOfPassengers(int noOfPassengers) {
		this.noOfPassengers = noOfPassengers;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public LocalDateTime getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDateTime bookingDate) {
		this.bookingDate = bookingDate;
	}

	public paymentInfo getPinfo() {
		return pinfo;
	}

	public void setPinfo(paymentInfo pinfo) {
		this.pinfo = pinfo;
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

	
	public String getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	@Override
	public String toString() {
		return "flightBooking [bookingId=" + bookingId + ", flightCode=" + flightCode + ", src=" + src
				+ ", destination=" + destination + ", travelDate=" + travelDate + ", arrTime=" + arrTime + ", deptTime="
				+ deptTime + ", noOfPassengers=" + noOfPassengers + ", price=" + price + ", fName=" + fName + ", lName="
				+ lName + ", emailId=" + emailId + ", contactNo=" + contactNo + ", bookingDate=" + bookingDate
				+ ", bookingStatus=" + bookingStatus + ", pinfo=" + pinfo + "]";
	}

}
