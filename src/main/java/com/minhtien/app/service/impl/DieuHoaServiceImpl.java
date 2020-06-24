package com.minhtien.app.service.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.minhtien.app.model.DieuHoa;
import com.minhtien.app.model.ProductFiles;
import com.minhtien.app.repository.DieuHoaFilesRepository;
import com.minhtien.app.repository.DieuHoaRepository;
import com.minhtien.app.service.DieuHoaService;
import com.minhtien.app.service.UploadPathService;

@Service
@Transactional
public class DieuHoaServiceImpl implements DieuHoaService {

	final String DATE_FORMAT = "dd/MM/yyyy";
	SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

	@Autowired
	private DieuHoaRepository dieuHoaRepository;

	@Autowired
	private DieuHoaFilesRepository dieuHoaFilesRepository;

	@Autowired
	private UploadPathService uploadpathService;

	@Autowired
	private ServletContext context;

	@Override
	public List<DieuHoa> listAll() {
		return (List<DieuHoa>) dieuHoaRepository.findAll();
	}

	@Override
	public void deleteFileDieuHoaById(Long dieuhoaId) {
		List<ProductFiles> productFiles = dieuHoaFilesRepository.findFilesByDieuHoaId(dieuhoaId);
		if (productFiles != null && productFiles.size() > 0) {
			for (ProductFiles dbFile : productFiles) {
				File dbStoreFile = new File(context.getRealPath("/images/" + File.separator + dbFile.getFileName()));
				if (dbStoreFile.exists()) {
					dbStoreFile.delete();
				}
			}
			dieuHoaFilesRepository.deleteFileDieuHoaById(dieuhoaId);
		}

	}

	@Override
	public List<ProductFiles> findFilesByProductId(Long productId) {
		return dieuHoaFilesRepository.findFilesByDieuHoaId(productId);
	}

	@Override
	public Optional<DieuHoa> getOne(Long id) {
		return dieuHoaRepository.findById(id);
	}

	@Override
	public DieuHoa save(DieuHoa modelObject) {
		modelObject.setDateCreated(sdf.format(new Date()));
		if (modelObject.getFile() != null && modelObject.getFile().getSize() > 0) {
			modelObject.setFileImage(modelObject.getFile().getOriginalFilename());
			File storeFile = uploadpathService.getFilePath(modelObject.getFile().getOriginalFilename(), "images");
			if (storeFile != null) {
				try {
					FileUtils.writeByteArrayToFile(storeFile, modelObject.getFile().getBytes());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		DieuHoa dbDieuHoa = dieuHoaRepository.save(modelObject);

		if (dbDieuHoa != null && dbDieuHoa.getFiles() != null && dbDieuHoa.getFiles().size() > 0) {
			for (MultipartFile file : dbDieuHoa.getFiles()) {
				if (file != null && StringUtils.hasText(file.getOriginalFilename())) {
					String fileName = file.getOriginalFilename();
					String modifiedFileName = FilenameUtils.getBaseName(fileName) + "."
							+ FilenameUtils.getExtension(fileName);
					File storeFile = uploadpathService.getFilePath(modifiedFileName, "images");
					if (storeFile != null) {
						try {
							FileUtils.writeByteArrayToFile(storeFile, file.getBytes());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

					ProductFiles files = new ProductFiles();
					files.setFileName(fileName);
					files.setDateCreated(sdf.format(new Date()));
					files.setDieuHoa(dbDieuHoa);
					dieuHoaFilesRepository.save(files);
				}
			}
		}

		return dbDieuHoa;
	}

	@Override
	public DieuHoa update(DieuHoa modelObject) {
		modelObject.setLastUpdated(sdf.format(new Date()));
		if (modelObject.getFile() != null && modelObject.getFile().getSize() > 0) {
			modelObject.setFileImage(modelObject.getFile().getOriginalFilename());
			File storeFile = uploadpathService.getFilePath(modelObject.getFile().getOriginalFilename(), "images");
			if (storeFile != null) {
				try {
					FileUtils.writeByteArrayToFile(storeFile, modelObject.getFile().getBytes());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		DieuHoa dbDieuHoa = dieuHoaRepository.save(modelObject);
		
		if (dbDieuHoa != null && modelObject.getRemoveImage() != null && modelObject.getRemoveImage().size() > 0) {
			for (String fileName : modelObject.getRemoveImage()) {
				dieuHoaFilesRepository.deleteProductFilesByName(fileName);
				File dbStoreFile = new File(context.getRealPath("/images/" + File.separator + fileName));
				if (dbStoreFile.exists()) {
					dbStoreFile.delete();
				}
			}
			
		}

		if (dbDieuHoa != null && modelObject.getFiles() != null && modelObject.getFiles().size() > 0) {
			for (MultipartFile file : modelObject.getFiles()) {
				if (file != null && StringUtils.hasText(file.getOriginalFilename())) {
					String fileName = file.getOriginalFilename();
					String modifiedFileName = FilenameUtils.getBaseName(fileName) + "."
							+ FilenameUtils.getExtension(fileName);
					File storeFile = uploadpathService.getFilePath(modifiedFileName, "images");
					if (storeFile != null) {
						try {
							FileUtils.writeByteArrayToFile(storeFile, file.getBytes());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

					ProductFiles files = new ProductFiles();
					files.setFileName(fileName);
					files.setDateCreated(sdf.format(new Date()));
					files.setDieuHoa(dbDieuHoa);
					dieuHoaFilesRepository.save(files);
				}
			}
		}

		return dbDieuHoa;
	}

	@Override
	public void delete(Long id) {
		Optional<DieuHoa> dieuhoa = dieuHoaRepository.findById(id);
		if (dieuhoa.isPresent()) {
			File dbStoreFile = new File(
					context.getRealPath("/images/" + File.separator + dieuhoa.get().getFileImage()));
			if (dbStoreFile.exists()) {
				dbStoreFile.delete();
			}
		}
		List<ProductFiles> files = dieuHoaFilesRepository.findFilesByDieuHoaId(id);
		for (ProductFiles productFiles : files) {
			File dbStoreFile = new File(context.getRealPath("/images/" + File.separator + productFiles.getFileName()));
			if (dbStoreFile.exists()) {
				dbStoreFile.delete();
			}
		}

		dieuHoaRepository.deleteById(id);
		dieuHoaFilesRepository.deleteFileDieuHoaById(id);

	}

	@Override
	public List<DieuHoa> searchDieuHoa(String name) {
		return dieuHoaRepository.findByNameContaining(name);
	}

	@Override
	public DieuHoa getById(Long id) {
		Optional<DieuHoa> product = dieuHoaRepository.findById(id);
		if (product.isPresent()) {
			return product.get();
		}
		return null;
	}

	@Override
	public List<DieuHoa> hotDealsDieuHoa() {
		return dieuHoaRepository.findByQuangCaoContaining();
	}

	@Override
	public List<DieuHoa> findDieuHoaByThuongHieuId(Long id) {
		return dieuHoaRepository.findDieuHoaByThuongHieuId(id);
	}

	@Override
	public List<DieuHoa> listAllDieuHoaByStatus() {
		return dieuHoaRepository.findByStatusContaining();
	}






}
