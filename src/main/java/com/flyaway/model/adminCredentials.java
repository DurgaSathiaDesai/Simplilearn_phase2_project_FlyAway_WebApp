package com.flyaway.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="admincredentials")
public class adminCredentials 
{
	@Id
	@Column(name = "Username")
	private String userName;
	
	@Column(name = "UserPassword")
	private String password;

	public adminCredentials()
	{
		
	}
	
	public adminCredentials(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "adminCredentials [userName=" + userName + ", password=" + password + "]";
	}
	
}
