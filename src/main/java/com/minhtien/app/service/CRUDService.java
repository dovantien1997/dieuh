package com.minhtien.app.service;

import java.util.List;
import java.util.Optional;

public interface CRUDService<T> {

	List<T> listAll();

	Optional<T> getOne(Long id);

    T save(T modelObject);
    
    T update(T modelObject);

    void delete(Long id);
    
    
}
