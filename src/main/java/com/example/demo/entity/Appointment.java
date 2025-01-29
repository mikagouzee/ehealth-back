package com.example.demo.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Appointment {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int Id;
	public Date Timeslot;
	public int PatientId;
	public int DoctorId;
	public boolean isConfirmed;
	public boolean isPaid;
	
	public Appointment() {};
	
	public Appointment(AppointmentRequest request) {
		PatientId = request.patientId;
		Timeslot = request.timeslot;
		DoctorId = request.doctorId;
		isConfirmed = false;
		isPaid = false;
	}
	
	public Appointment(Date timeslot, int patientId, int doctorId) {
		super();
		Timeslot = timeslot;
		PatientId = patientId;
		DoctorId = doctorId;
		isConfirmed = false;
		isPaid = false;
	}
	
	
}
