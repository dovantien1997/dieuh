package com.minhtien.app.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.minhtien.app.model.DieuHoa;


@Repository
public interface DieuHoaRepository extends CrudRepository<DieuHoa, Long>{

	
	
	List<DieuHoa> findByNameContaining(String name);
	
	@Query("select f from DieuHoa as f where f.status = 1")
	List<DieuHoa> findByStatusContaining();
	
	@Query("select f from DieuHoa as f where f.quangcao = 1")
	List<DieuHoa> findByQuangCaoContaining();
	
	@Query("select f from DieuHoa as f where f.thuongHieu.id = ?1")
	List<DieuHoa> findDieuHoaByThuongHieuId(Long id);

}
