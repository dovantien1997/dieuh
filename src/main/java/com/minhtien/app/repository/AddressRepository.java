package com.minhtien.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.minhtien.app.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{

}
