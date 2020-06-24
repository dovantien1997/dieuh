package com.minhtien.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.minhtien.app.model.ThuongHieu;

@Repository
public interface ThuongHieuRepository extends JpaRepository<ThuongHieu, Long>{

	List<ThuongHieu> findByTenTHContaining(String tenTH);

}
