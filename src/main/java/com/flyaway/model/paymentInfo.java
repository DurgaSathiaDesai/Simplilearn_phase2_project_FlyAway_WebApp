package com.flyaway.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="paymentinfo")
public class paymentInfo 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "paymentId")
	private int paymentId;
	
	@Column(name = "cardnumber")
	private String cardNo;
	
	@Column(name = "emonth")
	private int expMonth;
	
	@Column(name = "eyear")
	private int expYear;
	
	@Column(name = "fullname")
	private String fullName;
	
	@Column(name = "cvv")
	private int cvv;
	
	public paymentInfo()
	{
		
	}

	public paymentInfo(String cardNo, int expMonth, int expYear, String fullName, int cvv) {
		super();
		this.cardNo = cardNo;
		this.expMonth = expMonth;
		this.expYear = expYear;
		this.fullName = fullName;
		this.cvv = cvv;
	}

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public int getExpMonth() {
		return expMonth;
	}

	public void setExpMonth(int expMonth) {
		this.expMonth = expMonth;
	}

	public int getExpYear() {
		return expYear;
	}

	public void setExpYear(int expYear) {
		this.expYear = expYear;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getCvv() {
		return cvv;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

	@Override
	public String toString() {
		return "paymentInfo [paymentId=" + paymentId + ", cardNo=" + cardNo + ", expMonth=" + expMonth + ", expYear="
				+ expYear + ", fullName=" + fullName + ", cvv=" + cvv + "]";
	}
	
}
