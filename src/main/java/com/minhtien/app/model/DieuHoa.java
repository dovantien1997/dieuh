package com.minhtien.app.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
public class DieuHoa extends ModelClass implements Serializable {

	private static final long serialVersionUID = 1L;

	@OneToOne
	private ThuongHieu thuongHieu;

	@OneToOne
	private LoaiSanPham loaiSanPham;

	@OneToOne
	private LoaiMay loaiMay;

	@OneToOne
	private CongNghe congNghe;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private int price;

	private int sale;

	private String fileImage;

	@Transient
	private MultipartFile file;

	@Transient
	private List<MultipartFile> files = new ArrayList<MultipartFile>();

	@Transient
	private List<String> removeImage = new ArrayList<String>();

	public List<String> getRemoveImage() {
		return removeImage;
	}

	public void setRemoveImage(List<String> removeImage) {
		this.removeImage = removeImage;
	}

	@Column(nullable = false)
	private String codeSp;

	private int status;
	private int quangcao;

	private String description;

	private String timeSale;

	private int quantity;
	private String congsuatlamlanh;
	private String congsuatlamnong;
	private String phamvilamlanh;
	private String congsuattieuthu;
	private String tienich;
	private String tietkiemdien;
	private String locbui;
	private String lamlanhnhanh;
	private String chedogio;
	private String ttcuclanh;
	private String ttcucnong;
	private String doon;
	private String chatlieudantannhiet;
	private String loaigas;
	private String chieudaiongdong;
	private String chieucaomax;
	private String noilaprap;
	private String namsanxuat;
	private String baohanh;

	public String getTimeSale() {
		return timeSale;
	}

	public void setTimeSale(String timeSale) {
		this.timeSale = timeSale;
	}

	public int getQuangcao() {
		return quangcao;
	}

	public void setQuangcao(int quancao) {
		this.quangcao = quancao;
	}

	public String getBaohanh() {
		return baohanh;
	}

	public void setBaohanh(String baohanh) {
		this.baohanh = baohanh;
	}

	public String getCodeSp() {
		return codeSp;
	}

	public void setCodeSp(String codeSp) {
		this.codeSp = codeSp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public ThuongHieu getThuongHieu() {
		return thuongHieu;
	}

	public void setThuongHieu(ThuongHieu thuongHieu) {
		this.thuongHieu = thuongHieu;
	}

	public LoaiSanPham getLoaiSanPham() {
		return loaiSanPham;
	}

	public void setLoaiSanPham(LoaiSanPham loaiSanPham) {
		this.loaiSanPham = loaiSanPham;
	}

	public LoaiMay getLoaiMay() {
		return loaiMay;
	}

	public void setLoaiMay(LoaiMay loaiMay) {
		this.loaiMay = loaiMay;
	}

	public CongNghe getCongNghe() {
		return congNghe;
	}

	public void setCongNghe(CongNghe congNghe) {
		this.congNghe = congNghe;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getSale() {
		return sale;
	}

	public void setSale(int sale) {
		this.sale = sale;
	}

	public String getFileImage() {
		return fileImage;
	}

	public void setFileImage(String fileImage) {
		this.fileImage = fileImage;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public List<MultipartFile> getFiles() {
		return files;
	}

	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getCongsuatlamlanh() {
		return congsuatlamlanh;
	}

	public void setCongsuatlamlanh(String congsuatlamlanh) {
		this.congsuatlamlanh = congsuatlamlanh;
	}

	public String getCongsuatlamnong() {
		return congsuatlamnong;
	}

	public void setCongsuatlamnong(String congsuatlamnong) {
		this.congsuatlamnong = congsuatlamnong;
	}

	public String getPhamvilamlanh() {
		return phamvilamlanh;
	}

	public void setPhamvilamlanh(String phamvilamlanh) {
		this.phamvilamlanh = phamvilamlanh;
	}

	public String getCongsuattieuthu() {
		return congsuattieuthu;
	}

	public void setCongsuattieuthu(String congsuattieuthu) {
		this.congsuattieuthu = congsuattieuthu;
	}

	public String getTienich() {
		return tienich;
	}

	public void setTienich(String tienich) {
		this.tienich = tienich;
	}

	public String getTietkiemdien() {
		return tietkiemdien;
	}

	public void setTietkiemdien(String tietkiemdien) {
		this.tietkiemdien = tietkiemdien;
	}

	public String getLocbui() {
		return locbui;
	}

	public void setLocbui(String locbui) {
		this.locbui = locbui;
	}

	public String getLamlanhnhanh() {
		return lamlanhnhanh;
	}

	public void setLamlanhnhanh(String lamlanhnhanh) {
		this.lamlanhnhanh = lamlanhnhanh;
	}

	public String getChedogio() {
		return chedogio;
	}

	public void setChedogio(String chedogio) {
		this.chedogio = chedogio;
	}

	public String getTtcuclanh() {
		return ttcuclanh;
	}

	public void setTtcuclanh(String ttcuclanh) {
		this.ttcuclanh = ttcuclanh;
	}

	public String getTtcucnong() {
		return ttcucnong;
	}

	public void setTtcucnong(String ttcucnong) {
		this.ttcucnong = ttcucnong;
	}

	public String getDoon() {
		return doon;
	}

	public void setDoon(String doon) {
		this.doon = doon;
	}

	public String getChatlieudantannhiet() {
		return chatlieudantannhiet;
	}

	public void setChatlieudantannhiet(String chatlieudantannhiet) {
		this.chatlieudantannhiet = chatlieudantannhiet;
	}

	public String getLoaigas() {
		return loaigas;
	}

	public void setLoaigas(String loaigas) {
		this.loaigas = loaigas;
	}

	public String getChieudaiongdong() {
		return chieudaiongdong;
	}

	public void setChieudaiongdong(String chieudaiongdong) {
		this.chieudaiongdong = chieudaiongdong;
	}

	public String getChieucaomax() {
		return chieucaomax;
	}

	public void setChieucaomax(String chieucaomax) {
		this.chieucaomax = chieucaomax;
	}

	public String getNoilaprap() {
		return noilaprap;
	}

	public void setNoilaprap(String noilaprap) {
		this.noilaprap = noilaprap;
	}

	public String getNamsanxuat() {
		return namsanxuat;
	}

	public void setNamsanxuat(String namsanxuat) {
		this.namsanxuat = namsanxuat;
	}

}
