package com.minhtien.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.minhtien.app.model.LoaiSanPham;

@Repository
public interface LoaiSanPhamRepository extends JpaRepository<LoaiSanPham, Long>{

}
