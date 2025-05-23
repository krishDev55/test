package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Users;

@Repository
public interface UserDao extends JpaRepository<Users, Long> {

	public List<Users> findByMobile(Long mobile);
}
