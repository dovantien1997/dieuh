package com.minhtien.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.minhtien.app.model.DieuHoa;
import com.minhtien.app.model.ProductFiles;
import com.minhtien.app.service.CongNgheService;
import com.minhtien.app.service.DieuHoaService;
import com.minhtien.app.service.LoaiMayService;
import com.minhtien.app.service.LoaiSanPhamService;
import com.minhtien.app.service.ThuongHieuService;

@Controller
@RequestMapping("admin")
public class DieuHoaController {

	@Autowired
	private ThuongHieuService thuongHieuService;
	
	@Autowired
	private LoaiSanPhamService loaiSanPhamService;
	
	@Autowired
	private LoaiMayService loaiMayService;
	
	@Autowired
	private CongNgheService congNgheService;
	
	@Autowired
	private DieuHoaService dieuHoaService;

	@GetMapping("/listDH")
	public String home() {
		return "redirect:/admin/dieuhoa";
	}

	@GetMapping("/dieuhoa")
	public String thuonghieu(Model model, HttpServletRequest request, RedirectAttributes redirect) {
		request.getSession().setAttribute("dieuhoalist", null);

		if (model.asMap().get("success") != null)
			redirect.addFlashAttribute("success", model.asMap().get("success").toString());
		return "redirect:/admin/dieuhoa/page/1";
	}

	@GetMapping("/dieuhoa/page/{pageNumber}")
	public String showThuongHieuPage(HttpServletRequest request, @PathVariable int pageNumber, Model model) {
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("dieuhoalist");
		int pagesize = 20;
		List<DieuHoa> list = (List<DieuHoa>) dieuHoaService.listAll();
		System.out.println(list.size());
		if (pages == null) {
			pages = new PagedListHolder<>(list);
			pages.setPageSize(pagesize);
		} else {
			final int goToPage = pageNumber - 1;
			if (goToPage <= pages.getPageCount() && goToPage >= 0) {
				pages.setPage(goToPage);
			}
		}
		request.getSession().setAttribute("dieuhoalist", pages);
		int current = pages.getPage() + 1;
		int begin = Math.max(1, current - list.size());
		if (pageNumber < 1) {
			pageNumber = 1;
		}

		int end = Math.min(begin + 5, pages.getPageCount());
		if (pageNumber > current) {
			pageNumber = current;
		}
		int totalPageCount = pages.getPageCount();
		String baseUrl = "/admin/dieuhoa/page/";

		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("baseUrl", baseUrl);
		model.addAttribute("dieuhoas", pages);
		

		return "admin/views/listdieuhoa";
	}
	
	@RequestMapping("/newDH")
	public String newdh(Model model) {
		model.addAttribute("dieuhoa", new DieuHoa());
		model.addAttribute("productfiles", new ArrayList<ProductFiles>());
		model.addAttribute("isAdd", true);
		model.addAttribute("thuonghieus",thuongHieuService.listAll());
		model.addAttribute("loaisanphams",loaiSanPhamService.listAll());
		model.addAttribute("loaimays",loaiMayService.listAll());
		model.addAttribute("congnghes",congNgheService.listAll());
		return "admin/views/updatesanpham";
	}

	@PostMapping(value = "/save/dh")
	public String save(@ModelAttribute DieuHoa dieuhoa, RedirectAttributes redirectAttributes, Model model) {
		DieuHoa dbDieuHoa = dieuHoaService.save(dieuhoa);
		if (dbDieuHoa != null) {
			model.addAttribute("successmessage", "Thêm mới thương hiệu thành công");
			return "redirect:/admin/listDH";
		} else {
			model.addAttribute("errormessage", "Thêm mới thương hiệu thất bại");
			model.addAttribute("dieuhoa", dieuhoa);
			return "redirect:/admin/listDH";
		}

	}

//	@GetMapping("/dieuhoa/page/edit")
//	@ResponseBody
//	public Optional<DieuHoa> editdieuhoa(Long id , Model model) {
//		List<ProductFiles> productFiles = dieuHoaService.findFilesByProductId(id);
//		model.addAttribute("productfiles", productFiles);
//		return dieuHoaService.getOne(id);
//	}
	
	@GetMapping("/dieuhoa/edit/{id}")
	public String editfiles(@PathVariable Long id , Model model) {
		List<ProductFiles> productFiles = dieuHoaService.findFilesByProductId(id);
		DieuHoa dieuhoa = dieuHoaService.getById(id);
		model.addAttribute("productfiles", productFiles);
		model.addAttribute("dieuhoa", dieuhoa);
		model.addAttribute("thuonghieus",thuongHieuService.listAll());
		model.addAttribute("loaisanphams",loaiSanPhamService.listAll());
		model.addAttribute("loaimays",loaiMayService.listAll());
		model.addAttribute("congnghes",congNgheService.listAll());
		model.addAttribute("isAdd", false);
		return "admin/views/updatesanpham";
	}
	
	@GetMapping("/dieuhoa/detail/{id}")
	public String detailProduct(@PathVariable Long id , Model model) {
		List<ProductFiles> productFiles = dieuHoaService.findFilesByProductId(id);
		DieuHoa dieuhoa = dieuHoaService.getById(id);
		model.addAttribute("productfiles", productFiles);
		model.addAttribute("dieuhoa", dieuhoa);
		return "admin/views/detailproduct";
	}
	
	@GetMapping("dieuhoa/search/edit")
	@ResponseBody
	public Optional<DieuHoa> edittdieuhoas(Long id) {
		return dieuHoaService.getOne(id);
	}

	@RequestMapping(value = "/update/dh", method = RequestMethod.POST)
	public String update(@ModelAttribute DieuHoa dieuhoa, RedirectAttributes redirectAttributes, Model model) {
		DieuHoa dbDieuHoa = dieuHoaService.update(dieuhoa);
		if (dbDieuHoa != null) {
			redirectAttributes.addFlashAttribute("successmessage", "Cập nhật thương hiệu thành công");
			return "redirect:/admin/listDH";
		} else {
			model.addAttribute("errormessage", "Cập nhật thương hiệu thất bại");
			model.addAttribute("dieuhoa", dieuhoa);
			return "admin/views/updatesanpham";
		}

	}

	@RequestMapping(value = "/deleteDH/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes, Model model) {
		dieuHoaService.delete(id);
		redirectAttributes.addFlashAttribute("successmessage", "Xóa thương hiệu thành công");
		return "redirect:/admin/listDH";

	}

	@GetMapping("/dieuhoa/search/{pageNumber}")
	public String search(@RequestParam("keyword") String keyword, Model model, HttpServletRequest request,
			@PathVariable int pageNumber) {
		if (keyword.equals("")) {
			return "redirect:/admin/dieuhoa";
		}
		List<DieuHoa> list = dieuHoaService.searchDieuHoa(keyword);
		if (list == null) {
			return "redirect:/admin/dieuhoa";
		}
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("dieuhoalist");
		int pagesize = 3;
		pages = new PagedListHolder<>(list);
		pages.setPageSize(pagesize);

		final int goToPage = pageNumber - 1;
		if (goToPage <= pages.getPageCount() && goToPage >= 0) {
			pages.setPage(goToPage);
		}
		request.getSession().setAttribute("dieuhoalist", pages);
		int current = pages.getPage() + 1;
		int begin = Math.max(1, current - list.size());
		int end = Math.min(begin + 5, pages.getPageCount());
		int totalPageCount = pages.getPageCount();
		String baseUrl = "/admin/dieuhoa/page/";
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("baseUrl", baseUrl);
		model.addAttribute("thuonghieus", pages);
		return "admin/views/listdieuhoa";
	}
}
