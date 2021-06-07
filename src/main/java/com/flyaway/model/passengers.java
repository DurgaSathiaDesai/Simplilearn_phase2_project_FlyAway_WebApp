package com.flyaway.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="passengersinfo")
public class passengers 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "passengerId")
	private int passengerId;
	
	@Column(name = "Firstname")
	private String firstName;
	
	@Column(name = "Lastname")
	private String lastName;
	
	@ManyToOne(optional = false)
    @JoinColumn(name="bookingId")
	private flightBooking fbook;

	public passengers()
	{
		
	}
	
	public passengers(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public int getPassengerId() {
		return passengerId;
	}

	public void setPassengerId(int passengerId) {
		this.passengerId = passengerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public flightBooking getFbook() {
		return fbook;
	}

	public void setFbook(flightBooking fbook) {
		this.fbook = fbook;
	}

	@Override
	public String toString() {
		return "passengers [passengerId=" + passengerId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", fbook=" + fbook + "]";
	}

}
