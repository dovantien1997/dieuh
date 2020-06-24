package com.minhtien.app.controller;

import java.util.List;
import java.util.stream.Stream;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.minhtien.app.model.DieuHoa;
import com.minhtien.app.model.ProductFiles;
import com.minhtien.app.model.ThuongHieu;
import com.minhtien.app.service.CongNgheService;
import com.minhtien.app.service.DieuHoaService;
import com.minhtien.app.service.LoaiMayService;
import com.minhtien.app.service.LoaiSanPhamService;
import com.minhtien.app.service.ThuongHieuService;

@Controller
public class ProductDetailController {

	@Autowired
	private DieuHoaService dieuHoaService;

	@Autowired
	private CongNgheService congNgheService;

	@Autowired
	private LoaiSanPhamService loaiSanPhamService;

	@Autowired
	private LoaiMayService loaiMayService;

	@Autowired
	private ThuongHieuService thuongHieuService;

	@GetMapping("/product/{id}")
	public String detailProduct(@PathVariable Long id, Model model, HttpSession session) {

		List<DieuHoa> dieuHoas = dieuHoaService.listAll();
		List<ThuongHieu> thuongHieus = thuongHieuService.listAll();
		Stream<DieuHoa> hotdeals = (Stream<DieuHoa>) dieuHoaService.hotDealsDieuHoa().stream().limit(8);
		model.addAttribute("dieuhoas", dieuHoas);
		model.addAttribute("thuonghieus", thuongHieus);
		model.addAttribute("hotdeals", hotdeals.iterator());
		DieuHoa dieuHoa = dieuHoaService.getById(id);
		Stream<DieuHoa> streamGetByTH = (Stream<DieuHoa>) dieuHoaService
				.findDieuHoaByThuongHieuId(dieuHoa.getThuongHieu().getId()).stream().limit(7);
		List<ProductFiles> productFiles = dieuHoaService.findFilesByProductId(id);
		model.addAttribute("dieuhoa", dieuHoa);
		model.addAttribute("productfiles", productFiles);
		model.addAttribute("dieuhoalienquans", streamGetByTH.iterator());

		AddtoCartController cartController = new AddtoCartController();
		model.addAttribute("totalPrice", cartController.totalPrice(session));
		model.addAttribute("totalItem", cartController.totalItem(session));

		return "views/product-detail";
	}

}
