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

import com.minhtien.app.model.LoaiMay;
import com.minhtien.app.service.LoaiMayService;

@Controller
@RequestMapping("admin")
public class LoaiMayController {

	@Autowired
	private LoaiMayService loaiMayService;
	
	@GetMapping("/listLM")
	public String home() {
		return "redirect:/admin/loaimay";
	}

	@GetMapping("/loaimay")
	public String loaimay(Model model, HttpServletRequest request, RedirectAttributes redirect) {
		request.getSession().setAttribute("loaimaylist", null);

		if (model.asMap().get("success") != null)
			redirect.addFlashAttribute("success", model.asMap().get("success").toString());
		return "redirect:/admin/loaimay/page/1";
	}

	@GetMapping("/loaimay/page/{pageNumber}")
	public String showLoaiMayPage(HttpServletRequest request, @PathVariable int pageNumber, Model model) {
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("loaimaylist");
		int pagesize = 5;
		List<LoaiMay> list = (List<LoaiMay>) loaiMayService.listAll();
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
		request.getSession().setAttribute("loaimaylist", pages);
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
		String baseUrl = "/admin/loaimay/page/";

		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("baseUrl", baseUrl);
		model.addAttribute("loaimays", pages);

		return "admin/views/listloaimay";
	}

	@PostMapping(value = "/save/lm")
	public String save(@ModelAttribute LoaiMay loaimay, RedirectAttributes redirectAttributes, Model model) {
		LoaiMay dbLoaiMay = loaiMayService.save(loaimay);
		if (dbLoaiMay != null) {
			model.addAttribute("successmessage", "Thêm mới thương hiệu thành công");
			return "redirect:/admin/listLM";
		} else {
			model.addAttribute("errormessage", "Thêm mới thương hiệu thất bại");
			return "redirect:/admin/listLM";
		}

	}

	@GetMapping("/loaimay/page/edit")
	@ResponseBody
	public Optional<LoaiMay> editloaimay(Long id) {
		return loaiMayService.getOne(id);
	}
	

	@RequestMapping(value = "/update/lm", method = { RequestMethod.PUT, RequestMethod.GET })
	public String update(@ModelAttribute LoaiMay loaimay, RedirectAttributes redirectAttributes, Model model) {
		LoaiMay dbLoaiMay = loaiMayService.update(loaimay);
		if (dbLoaiMay != null) {
			redirectAttributes.addFlashAttribute("successmessage", "Cập nhật loại máy thành công");
			return "redirect:/admin/listLM";
		} else {
			model.addAttribute("errormessage", "Cập nhật loại máy thất bại");
			model.addAttribute("product", loaimay);
			return "redirect:/admin/listLM";
		}

	}

	@RequestMapping(value = "/deleteLM/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes, Model model) {
		loaiMayService.delete(id);
		redirectAttributes.addFlashAttribute("successmessage", "Xóa thương hiệu thành công");
		return "redirect:/admin/listLM";

	}
}
