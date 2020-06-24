package com.minhtien.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.minhtien.app.model.CongNghe;

@Repository
public interface CongNgheRepository extends JpaRepository<CongNghe, Long>{

}
