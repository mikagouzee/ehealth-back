package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;

@RestController
//@RequestMapping("/api")
public class HomeController {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@GetMapping("/")
	public String index() {
		return "HELLOOOOOOOO MY DUDE";
	}
	
	@PostMapping("/saveStudent")
	public ResponseEntity<Student> saveData(@RequestBody Student student) {
		try {
			Student savedStudent = studentRepository.save(student);
			return ResponseEntity.ok(savedStudent);
		}catch (Exception e) {
			return ResponseEntity.status(500).build();
		}
	}
	
	@GetMapping("/getAll")
	public List<Student> getAll(){
		List<Student> result = studentRepository.findAll();
		return result;
	}
}
