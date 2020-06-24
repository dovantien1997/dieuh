package com.minhtien.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.minhtien.app.model.ProductFiles;

@Repository
public interface DieuHoaFilesRepository extends CrudRepository<ProductFiles, Long>{

	@Query("select f from ProductFiles as f where f.dieuHoa.id = ?1")
	List<ProductFiles> findFilesByDieuHoaId(Long dieuHoaId);
	
	
	@Modifying
	@Query("delete from ProductFiles as f where f.dieuHoa.id = ?1")
	void deleteFileDieuHoaById(Long dieuHoaId);
	
	
	@Modifying
	@Query("delete from ProductFiles as f where f.fileName = ?1")
	void deleteProductFilesByName(String fileName);

	

}
