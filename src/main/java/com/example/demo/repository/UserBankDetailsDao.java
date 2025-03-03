package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.UserBankDetails;

@Repository
public interface UserBankDetailsDao extends JpaRepository<UserBankDetails, String> {

	public List<UserBankDetails> findByMobile(Long mobile);
}
