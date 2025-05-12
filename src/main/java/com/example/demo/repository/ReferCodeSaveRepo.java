package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.ReferCodeSave;

@Repository
public interface ReferCodeSaveRepo  extends JpaRepository<ReferCodeSave, Long>{

	Long findReferCodeSaveByReferCode(String code);
}
