package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
//	Optional<User> findByNameAndPassword(String name, String password);
	@Query("select u from User u where u.Name = ?1")
	Optional<User> findByName(String Name);
	
	@Query("select u from User u where u.UserType = ?1")
	Optional<List<User>> findByUserType(String UserType);
}
