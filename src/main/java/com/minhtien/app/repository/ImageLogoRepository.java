package com.minhtien.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.minhtien.app.model.ImageLogo;

@Repository
public interface ImageLogoRepository extends JpaRepository<ImageLogo, Long>{

}
