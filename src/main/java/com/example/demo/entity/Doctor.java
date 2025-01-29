package com.example.demo.entity;

import jakarta.persistence.Entity;

@Entity
public class Doctor extends User{
	public String ValidationString;

	public Doctor() {
		UserType="Doctor";
	}
	
	public Doctor(String name, String eMail, String password, String validationString) {
		super(name, eMail, password);
		ValidationString = validationString;
		UserType = "Doctor";
	}

	public Doctor(RegistrationRequest request) {
		super(request.name, request.eMail, request.password);
		ValidationString = request.validation;
		UserType = "Doctor";
	}
	
}
