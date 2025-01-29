package com.example.demo.entity;

import jakarta.persistence.Entity;

@Entity
public class Patient extends User {

	public Patient() {
		UserType="Patient";
	}
	
	public Patient(String name, String eMail, String password) {
		super(name, eMail, password);
		UserType="Patient";
		// TODO Auto-generated constructor stub
	}
	
	public Patient(RegistrationRequest request) {
		super(request.name, request.eMail, request.password);
		UserType="Patient";
	}
}
