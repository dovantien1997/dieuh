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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.minhtien.app.model.LoaiSanPham;
import com.minhtien.app.service.LoaiSanPhamService;

@Controller
@RequestMapping("admin")
public class LoaiSanPhamController {
	
	@Autowired
	private LoaiSanPhamService loaiSanPhamService;
	
	@GetMapping("/listLSP")
	public String home() {
		return "redirect:/admin/loaisanpham";
	}

	@GetMapping("/loaisanpham")
	public String loaiSanPham(Model model, HttpServletRequest request, RedirectAttributes redirect) {
		request.getSession().setAttribute("loaisanphamlist", null);

		if (model.asMap().get("success") != null)
			redirect.addFlashAttribute("success", model.asMap().get("success").toString());
		return "redirect:/admin/loaisanpham/page/1";
	}

	@GetMapping("/loaisanpham/page/{pageNumber}")
	public String showLoaiSPPage(HttpServletRequest request, @PathVariable int pageNumber, Model model) {
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("loaisanphamlist");
		int pagesize = 5;
		List<LoaiSanPham> list = (List<LoaiSanPham>) loaiSanPhamService.listAll();
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
		request.getSession().setAttribute("loaisanphamlist", pages);
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
		String baseUrl = "/admin/loaisanpham/page/";

		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("baseUrl", baseUrl);
		model.addAttribute("loaisanphams", pages);

		return "admin/views/listloaisanpham";
	}

	@PostMapping(value = "/save/lsp")
	public String save(@ModelAttribute LoaiSanPham loaiSanPham, RedirectAttributes redirectAttributes, Model model) {
		LoaiSanPham dbLoaiSanPham = loaiSanPhamService.save(loaiSanPham);
		if (dbLoaiSanPham != null) {
			model.addAttribute("successmessage", "Thêm mới thương hiệu thành công");
			return "redirect:/admin/listLSP";
		} else {
			model.addAttribute("errormessage", "Thêm mới thương hiệu thất bại");
			model.addAttribute("loaisanpham", loaiSanPham);
			return "redirect:/admin/listLSP";
		}

	}

	@GetMapping("/loaisanpham/page/edit")
	@ResponseBody
	public Optional<LoaiSanPham> editLoaiSanPham(Long id) {
		return loaiSanPhamService.getOne(id);
	}
	

	@RequestMapping(value = "/update/lsp", method = { RequestMethod.PUT, RequestMethod.GET })
	public String update(@ModelAttribute LoaiSanPham loaiSanPham, RedirectAttributes redirectAttributes, Model model) {
		LoaiSanPham dbLoaiSanPham = loaiSanPhamService.update(loaiSanPham);
		if (dbLoaiSanPham != null) {
			redirectAttributes.addFlashAttribute("successmessage", "Cập nhật thương hiệu thành công");
			return "redirect:/admin/listLSP";
		} else {
			model.addAttribute("errormessage", "Cập nhật thương hiệu thất bại");
			model.addAttribute("product", loaiSanPham);
			return "redirect:/admin/listLSP";
		}

	}

	@RequestMapping(value = "/deleteLSP/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes, Model model) {
		loaiSanPhamService.delete(id);
		redirectAttributes.addFlashAttribute("successmessage", "Xóa thương hiệu thành công");
		return "redirect:/admin/listLSP";

	}
	

}
