package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
	@Query("select a from Appointment a where a.DoctorId = ?1")
	Optional<List<Appointment>> findByDoctorId(int DoctorId);
}
