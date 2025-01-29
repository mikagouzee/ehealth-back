package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entity.Doctor;
import com.example.demo.entity.LoginRequest;
import com.example.demo.entity.Patient;
import com.example.demo.entity.RegistrationRequest;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3000/login"}, maxAge = 3600)
public class UserController {

		@Autowired
		private UserRepository _userRepository;
		
		@GetMapping("/doctors")
		public ResponseEntity<?> GetDoctors(){
			var dbquery = _userRepository.findByUserType("Doctor");
			List<User> doctors = null;
			// Ensure we always return a list
	        if (dbquery == null || dbquery.isEmpty()) {
	            doctors = new ArrayList<>();
	        }
	        else {
	        	doctors = dbquery.get();
	        }
			return ResponseEntity.ok(doctors);
		}
				
		@PostMapping("/login")
 		public ResponseEntity<?> Login(@RequestBody LoginRequest request){
			var dbquery =_userRepository.findByName(request.Name); 
			var exists = dbquery.get();
			if(exists!=null && exists.Password.equals(request.Password)) {
				return ResponseEntity.ok(exists);
			}
			else{
				return ResponseEntity.status(404).body("Wrong user or password");
			}
		}
	
		@PostMapping("/create")
		public ResponseEntity<?> Create(@RequestBody RegistrationRequest request){
			
			User user = null;
			
			if(request.userType.equals("patient")) {
				user = new Patient(request);
			}
			else if (request.userType.equals("doctor")){
				user = new Doctor(request);
			}
			
			var created = _userRepository.save(user);
			if(created.IsValid()) {
				return ResponseEntity.ok(created);	
			}
			else {
				return ResponseEntity.status(500).body("Something went wrong with your registration");
			}
			
		}
		
		@GetMapping("/{userid}")
		public ResponseEntity<User> GetById(@PathVariable int userId){
			var result = _userRepository.findById(userId).get();
			return ResponseEntity.ok(result);
		}
		
		@PutMapping("/update")
		public ResponseEntity<User> Update(@RequestBody User user){
			User toUpdate = _userRepository.findById(user.Id).get();
			if(toUpdate!=null) {
				toUpdate = user;
				_userRepository.save(toUpdate);
			}
			else {
				_userRepository.save(user);
			}
			return ResponseEntity.ok(user);
		}
		
		@DeleteMapping("/{userId}")
		public String Delete(@PathVariable int userId) {
			User toDelete = _userRepository.findById(userId).get();
			if(toDelete != null) {
				_userRepository.delete(toDelete);
			}
			return "success";
		}
}
