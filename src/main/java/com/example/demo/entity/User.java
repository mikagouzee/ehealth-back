package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int Id;
	public String Name;
	public String eMail;
	public String Password;
	public String UserType;
	
	public User(String name, String email, String password) {
		super();
		Name = name;
		eMail = email;
		Password = password;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public boolean IsValid() {
		if(this.Name.isBlank() || this.eMail.isBlank() || this.Password.isBlank())
			return false;
		else
			return true;
 	}

}
