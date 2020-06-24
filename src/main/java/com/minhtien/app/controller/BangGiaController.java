package com.minhtien.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.minhtien.app.model.BangGia;
import com.minhtien.app.service.BangGiaService;

@Controller
@RequestMapping("admin")
public class BangGiaController {

	@Autowired
	private BangGiaService bangGiaService;

	@GetMapping("/banggia")
	public String home(Model model) {
		List<BangGia> bangGias = bangGiaService.listAll();
		model.addAttribute("banggias", bangGias);
		return "admin/views/listbanggia";
	}

	@PostMapping(value = "/save/bg")
	public String save(@ModelAttribute BangGia bangGia, RedirectAttributes redirectAttributes, Model model) {
		BangGia dbBangGiae = bangGiaService.save(bangGia);
		if (dbBangGiae != null) {
			model.addAttribute("successmessage", "Thêm mới bảng giá thành công");
			return "redirect:/admin/banggia";
		} else {
			model.addAttribute("errormessage", "Thêm mới bảng giá thất bại");
			model.addAttribute("banggia", bangGia);
			return "redirect:/admin/banggia";
		}

	}

	@GetMapping("/banggia/edit")
	@ResponseBody
	public Optional<BangGia> editbanggia(Long id) {
		return bangGiaService.getOne(id);
	}

	@RequestMapping(value = "/update/banggia", method = { RequestMethod.PUT, RequestMethod.GET } )
	public String update(@ModelAttribute BangGia banggia, RedirectAttributes redirectAttributes, Model model) {
		BangGia dbBangGia = bangGiaService.update(banggia);
		if (dbBangGia != null) {
			redirectAttributes.addFlashAttribute("successmessage", "Cập nhật bảng giá thành công");
			return "redirect:/admin/banggia";
		} else {
			model.addAttribute("errormessage", "Cập nhật bảng giá thất bại");
			model.addAttribute("bangGia", banggia);
			return "redirect:/admin/banggia";
		}

	}

	@RequestMapping(value = "/deleteBG/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes, Model model) {
		bangGiaService.delete(id);
		redirectAttributes.addFlashAttribute("successmessage", "Xóa bảng giá thành công");
		return "redirect:/admin/banggia";

	}

}
