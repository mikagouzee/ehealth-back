package com.example.demo.controller;

import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Appointment;
import com.example.demo.entity.AppointmentConfirmationRequest;
import com.example.demo.entity.AppointmentRequest;
import com.example.demo.repository.AppointmentRepository;

@RestController
@RequestMapping("/appointment")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3000/*"}, maxAge = 3600)
public class AppointmentController {

		@Autowired
		private AppointmentRepository _appointmentRepository;
		
		
		@PostMapping("/confirm")
		public ResponseEntity<Appointment> ConfirmAppointment(@RequestBody AppointmentConfirmationRequest request) {
			Appointment toConfirm = _appointmentRepository.findById(request.appointmentId).get();
			if(toConfirm != null){
				toConfirm.isConfirmed = true;
				var confirmed = _appointmentRepository.save(toConfirm);
				return ResponseEntity.ok(confirmed);
			}
			else {
				return ResponseEntity.status(500).build();
			}
			
		}
		
		@PostMapping("/book")
		public String BookAppointment(@RequestBody AppointmentRequest request) {
			Appointment toCreate = new Appointment(request);
			var created = _appointmentRepository.save(toCreate);
			
			if(created!=null) {
				return "Your appointment request was sent to the Doctor. Wait for confirmation.";	
			}
			else {
				return "Oopsie";
			}
		}
		
		@GetMapping("/doctor/{doctorId}")
		public ResponseEntity<List<Appointment>> GetByDoctorId(@PathVariable int doctorId){
			var query = _appointmentRepository.findByDoctorId(doctorId);
			List<Appointment> result= null;
			if(query==null||query.isEmpty()) {
				result = new ArrayList<Appointment>();
				}
			else {
				result = query.get();
			}
			
			return ResponseEntity.ok(result);
			
		}
		
		
		@PostMapping("/bill")
		// This is the appointment billing method 
		public String BillAppointment(int appointmentId) {
			Appointment toBill = _appointmentRepository.findById(appointmentId).get();
			if(toBill!=null) {
				toBill.isPaid = true;
				_appointmentRepository.save(toBill);
				return "Your appointment has been billed. Wait for payment.";
			}
			else {
				return "No appointment found.";
			}
		}
}
