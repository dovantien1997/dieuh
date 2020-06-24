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

import com.minhtien.app.model.CongNghe;
import com.minhtien.app.service.CongNgheService;

@Controller
@RequestMapping("admin")
public class CongNgheController {
	
	@Autowired
	private CongNgheService congNgheService;

	@GetMapping("/listCN")
	public String home() {
		return "redirect:/admin/congnghe";
	}

	@GetMapping("/congnghe")
	public String congnghe(Model model, HttpServletRequest request, RedirectAttributes redirect) {
		request.getSession().setAttribute("congnghelist", null);

		if (model.asMap().get("success") != null)
			redirect.addFlashAttribute("success", model.asMap().get("success").toString());
		return "redirect:/admin/congnghe/page/1";
	}

	@GetMapping("/congnghe/page/{pageNumber}")
	public String showcongnghePage(HttpServletRequest request, @PathVariable int pageNumber, Model model) {
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("congnghelist");
		int pagesize = 5;
		List<CongNghe> list = (List<CongNghe>) congNgheService.listAll();
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
		request.getSession().setAttribute("congnghelist", pages);
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
		String baseUrl = "/admin/congnghe/page/";

		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("baseUrl", baseUrl);
		model.addAttribute("congnghes", pages);

		return "admin/views/listcongnghe";
	}

	@PostMapping(value = "/save/cn")
	public String save(@ModelAttribute CongNghe congnghe, RedirectAttributes redirectAttributes, Model model) {
		CongNghe dbCongNghe = congNgheService.save(congnghe);
		if (dbCongNghe != null) {
			model.addAttribute("successmessage", "Thêm mới thương hiệu thành công");
			return "redirect:/admin/listCN";
		} else {
			model.addAttribute("errormessage", "Thêm mới thương hiệu thất bại");
			model.addAttribute("loaimay", congnghe);
			return "redirect:/admin/listCN";
		}

	}

	@GetMapping("/congnghe/page/edit")
	@ResponseBody
	public Optional<CongNghe> editcongnghe(Long id) {
		return congNgheService.getOne(id);
	}
	

	@RequestMapping(value = "/update/cn", method = { RequestMethod.PUT, RequestMethod.GET })
	public String update(@ModelAttribute CongNghe congnghe, RedirectAttributes redirectAttributes, Model model) {
		CongNghe dbCongNghe = congNgheService.update(congnghe);
		if (dbCongNghe != null) {
			redirectAttributes.addFlashAttribute("successmessage", "Cập nhật công nghệ thành công");
			return "redirect:/admin/listCN";
		} else {
			model.addAttribute("errormessage", "Cập nhật công nghệ thất bại");
			model.addAttribute("congnghe", congnghe);
			return "redirect:/admin/listCN";
		}

	}

	@RequestMapping(value = "/deleteCN/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes, Model model) {
		congNgheService.delete(id);
		redirectAttributes.addFlashAttribute("successmessage", "Xóa công nghệ thành công");
		return "redirect:/admin/listCN";

	}
}
