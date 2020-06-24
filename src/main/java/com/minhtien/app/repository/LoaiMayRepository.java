package com.minhtien.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.minhtien.app.model.LoaiMay;

@Repository
public interface LoaiMayRepository extends JpaRepository<LoaiMay, Long>{

}
