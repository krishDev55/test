package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.ULoginDetails;

@Repository
public interface ULoginDetailsDao extends JpaRepository<ULoginDetails, Long>{
	

 
}
