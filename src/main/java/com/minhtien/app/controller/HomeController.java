//package com.minhtien.app.controller;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.servlet.http.HttpSession;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import com.minhtien.app.model.CongNghe;
//import com.minhtien.app.model.DieuHoa;
//import com.minhtien.app.model.LoaiMay;
//import com.minhtien.app.model.LoaiSanPham;
//import com.minhtien.app.model.ThuongHieu;
//import com.minhtien.app.service.CongNgheService;
//import com.minhtien.app.service.DieuHoaService;
//import com.minhtien.app.service.LoaiMayService;
//import com.minhtien.app.service.LoaiSanPhamService;
//import com.minhtien.app.service.ThuongHieuService;
//
//@Controller
//@RequestMapping("")
//public class HomeController {
//
//	@Autowired
//	private DieuHoaService dieuHoaService;
//
//	@Autowired
//	private CongNgheService congNgheService;
//
//	@Autowired
//	private LoaiSanPhamService loaiSanPhamService;
//
//	@Autowired
//	private LoaiMayService loaiMayService;
//
//	@Autowired
//	private ThuongHieuService thuongHieuService;
//
//	@GetMapping("/")
//	public String home(Model model) {
//		List<DieuHoa> dieuHoas = dieuHoaService.listAll();
//		List<CongNghe> congNghes = congNgheService.listAll();
//		List<LoaiSanPham> loaiSanPhams = loaiSanPhamService.listAll();
//		List<LoaiMay> loaiMays = loaiMayService.listAll();
//		List<ThuongHieu> thuongHieus = thuongHieuService.listAll();
//		List<DieuHoa> hotdeals = dieuHoaService.hotDealsDieuHoa();
//		model.addAttribute("dieuhoas", dieuHoas);
//		model.addAttribute("congnghes", congNghes);
//		model.addAttribute("loaisanphams", loaiSanPhams);
//		model.addAttribute("loaimays", loaiMays);
//		model.addAttribute("thuonghieus", thuongHieus);
//		model.addAttribute("hotdeals", hotdeals);
//		List<DieuHoa> dieuHoas2 = new ArrayList<>();
//		for (ThuongHieu hieu : thuongHieus) {
//
//			List<DieuHoa> dhs = dieuHoaService.findDieuHoaByThuongHieuId(hieu.getId());
//			for (DieuHoa dieuHoa : dhs) {
//				dieuHoas2.add(dieuHoa);
//			}
//			dhs.clear();
//		}
//		
//		AddtoCartController cartController = new AddtoCartController();
//
//		model.addAttribute("dieuHoas2", dieuHoas2);
//		return "index";
//
//	}
//
//}
