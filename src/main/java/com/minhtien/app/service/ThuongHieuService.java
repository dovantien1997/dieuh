package com.minhtien.app.service;

import java.util.List;

import com.minhtien.app.model.ThuongHieu;


public interface ThuongHieuService extends CRUDService<ThuongHieu>{


	List<ThuongHieu> searchThuongHieu(String tenTH);

}
