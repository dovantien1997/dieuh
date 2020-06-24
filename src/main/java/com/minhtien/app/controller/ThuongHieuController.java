package com.minhtien.app.controller;

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

import com.minhtien.app.model.ThuongHieu;
import com.minhtien.app.service.ThuongHieuService;

@Controller
@RequestMapping("admin")
public class ThuongHieuController {

	@Autowired
	private ThuongHieuService thuongHieuService;

	@GetMapping("/listTH")
	public String home() {
		return "redirect:/admin/thuonghieu";
	}

	@GetMapping("/thuonghieu")
	public String thuonghieu(Model model, HttpServletRequest request, RedirectAttributes redirect) {
		request.getSession().setAttribute("thuonghieulist", null);

		if (model.asMap().get("success") != null)
			redirect.addFlashAttribute("success", model.asMap().get("success").toString());
		return "redirect:/admin/thuonghieu/page/1";
	}

	@GetMapping("/thuonghieu/page/{pageNumber}")
	public String showThuongHieuPage(HttpServletRequest request, @PathVariable int pageNumber, Model model) {
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("thuonghieulist");
		int pagesize = 5;
		List<ThuongHieu> list = (List<ThuongHieu>) thuongHieuService.listAll();
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
		request.getSession().setAttribute("thuonghieulist", pages);
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
		String baseUrl = "/admin/thuonghieu/page/";

		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("baseUrl", baseUrl);
		model.addAttribute("thuonghieus", pages);

		return "admin/views/listThuongHieu";
	}

	@PostMapping(value = "/save/th")
	public String save(@ModelAttribute ThuongHieu thuongHieu, RedirectAttributes redirectAttributes, Model model) {
		ThuongHieu dbThuongHieu = thuongHieuService.save(thuongHieu);
		if (dbThuongHieu != null) {
			model.addAttribute("successmessage", "Thêm mới thương hiệu thành công");
			return "redirect:/admin/listTH";
		} else {
			model.addAttribute("errormessage", "Thêm mới thương hiệu thất bại");
			model.addAttribute("thuonghieu", thuongHieu);
			return "redirect:/admin/listTH";
		}

	}

	@GetMapping("/thuonghieu/page/edit")
	@ResponseBody
	public Optional<ThuongHieu> editthuonghieu(Long id) {
		return thuongHieuService.getOne(id);
	}
	
	@RequestMapping(path = "/home", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public List<ThuongHieu> listTH(){
		return thuongHieuService.listAll();
	}
	
	@GetMapping("thuonghieu/search/edit")
	@ResponseBody
	public Optional<ThuongHieu> editthuonghieus(Long id) {
		return thuongHieuService.getOne(id);
	}

	@RequestMapping(value = "/update/th", method = { RequestMethod.PUT, RequestMethod.GET })
	public String update(@ModelAttribute ThuongHieu thuongHieu, RedirectAttributes redirectAttributes, Model model) {
		ThuongHieu dbThuongHieu = thuongHieuService.update(thuongHieu);
		if (dbThuongHieu != null) {
			redirectAttributes.addFlashAttribute("successmessage", "Cập nhật thương hiệu thành công");
			return "redirect:/admin/listTH";
		} else {
			model.addAttribute("errormessage", "Cập nhật thương hiệu thất bại");
			model.addAttribute("product", thuongHieu);
			return "redirect:/admin/listTH";
		}

	}

	@RequestMapping(value = "/deleteTH/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes, Model model) {
		thuongHieuService.delete(id);
		redirectAttributes.addFlashAttribute("successmessage", "Xóa thương hiệu thành công");
		return "redirect:/admin/listTH";

	}

	@GetMapping("/thuonghieu/search/{pageNumber}")
	public String search(@RequestParam("keyword") String keyword, Model model, HttpServletRequest request,
			@PathVariable int pageNumber) {
		if (keyword.equals("")) {
			return "redirect:/admin/thuonghieu";
		}
		List<ThuongHieu> list = thuongHieuService.searchThuongHieu(keyword);
		if (list == null) {
			return "redirect:/admin/thuonghieu";
		}
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("thuonghieulist");
		int pagesize = 3;
		pages = new PagedListHolder<>(list);
		pages.setPageSize(pagesize);

		final int goToPage = pageNumber - 1;
		if (goToPage <= pages.getPageCount() && goToPage >= 0) {
			pages.setPage(goToPage);
		}
		request.getSession().setAttribute("thuonghieulist", pages);
		int current = pages.getPage() + 1;
		int begin = Math.max(1, current - list.size());
		int end = Math.min(begin + 5, pages.getPageCount());
		int totalPageCount = pages.getPageCount();
		String baseUrl = "/admin/thuonghieu/page/";
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("baseUrl", baseUrl);
		model.addAttribute("thuonghieus", pages);
		return "admin/views/listThuongHieu";
	}

}
