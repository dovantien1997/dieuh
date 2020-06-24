package com.minhtien.app.service;

import java.util.List;

import com.minhtien.app.model.DieuHoa;
import com.minhtien.app.model.ProductFiles;

public interface DieuHoaService extends CRUDService<DieuHoa> {

	void deleteFileDieuHoaById(Long dieuHoaId);

	List<ProductFiles> findFilesByProductId(Long dieuHoaId);

	List<DieuHoa> searchDieuHoa(String name);
	
	DieuHoa getById(Long id);
	
	List<DieuHoa> hotDealsDieuHoa();
	
	List<DieuHoa> listAllDieuHoaByStatus();
	
	List<DieuHoa> findDieuHoaByThuongHieuId(Long id);

	
	
}
