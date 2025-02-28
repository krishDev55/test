package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Users;

import jakarta.persistence.NamedQueries;

@Repository
public interface UserDao extends JpaRepository<Users, Integer> {

	public List<Users> findByMobile(Long mobile);
	
	@Query(value = "select u from Users u where u.id=:name")
	public Users getUserById(@Param("name") int name);
}
