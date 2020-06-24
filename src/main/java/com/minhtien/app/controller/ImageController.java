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

import com.minhtien.app.model.Image166;
import com.minhtien.app.model.Image570;
import com.minhtien.app.model.Image880;
import com.minhtien.app.model.ImageLogo;
import com.minhtien.app.service.Image166Service;
import com.minhtien.app.service.Image570Service;
import com.minhtien.app.service.Image880Service;
import com.minhtien.app.service.ImageLogoService;

@Controller
@RequestMapping("admin")
public class ImageController {

	@Autowired
	private Image880Service imageService;
	
	@Autowired
	private ImageLogoService imageLogoSv;
	
	@Autowired
	private Image570Service image570Sv;
	
	@Autowired
	private Image166Service image166Sv;
	
	@RequestMapping("/image")
	public String image880(Model model) {
		
		List<Image880> image880s = imageService.listAll();
		
		List<ImageLogo> imageLogo = imageLogoSv.listAll();
		
		List<Image570> image570s = image570Sv.listAll();
		
		List<Image166> image166s = image166Sv.listAll();
		
		model.addAttribute("images880", image880s);
		
		model.addAttribute("imageslogo", imageLogo);
		
		model.addAttribute("images570", image570s);
		
		model.addAttribute("images166", image166s);
		
		return "admin/views/listimage";
	}
	
	@PostMapping(value = "/save/image880")
	public String save(@ModelAttribute Image880 image, RedirectAttributes redirectAttributes, Model model) {
		Image880 dbImage880 = imageService.save(image);
		if (dbImage880 != null) {
			model.addAttribute("successmessage", "Thêm mới ảnh thành công");
			return "redirect:/admin/image";
		} else {
			model.addAttribute("errormessage", "Thêm mới bảng giá thất bại");
			model.addAttribute("image", image);
			return "redirect:/admin/image";
		}

	}

	@GetMapping("/image880/edit")
	@ResponseBody
	public Optional<Image880> editimage(Long id) {
		return imageService.getOne(id);
	}

	@RequestMapping(value = "/update/image880", method = { RequestMethod.PUT, RequestMethod.GET } )
	public String update(@ModelAttribute Image880 image, RedirectAttributes redirectAttributes, Model model) {
		Image880 dbImage880 = imageService.update(image);
		if (dbImage880 != null) {
			redirectAttributes.addFlashAttribute("successmessage", "Cập nhật ảnh thành công");
			return "redirect:/admin/image";
		} else {
			model.addAttribute("errormessage", "Cập nhật ảnh thất bại");
			model.addAttribute("image", image);
			return "redirect:/admin/image";
		}

	}

	@RequestMapping(value = "/deleteImage880/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes, Model model) {
		imageService.delete(id);
		redirectAttributes.addFlashAttribute("successmessage", "Xóa ảnh thành công");
		return "redirect:/admin/image";

	}
	
	@RequestMapping("/newimagelogo")
	public String newImageLogo(Model model) {
		model.addAttribute("imagelogo", new ImageLogo());
		model.addAttribute("isAdd", true);
		return "admin/views/imagelogo";
	}
	
	@PostMapping(value = "/save/imagelogo")
	public String save(@ModelAttribute ImageLogo imageLogo, RedirectAttributes redirectAttributes, Model model) {
		ImageLogo dbImageLogo = imageLogoSv.save(imageLogo);
		if (dbImageLogo != null) {
			model.addAttribute("successmessage", "Thêm mới ảnh thành công");
			return "redirect:/admin/image";
		} else {
			model.addAttribute("errormessage", "Thêm mới ảnh thất bại");
			model.addAttribute("imageLogo", imageLogo);
			return "redirect:/admin/imagelogo";
		}

	}
	
	@GetMapping("/imagelogo/edit/{id}")
	public String editfiles(@PathVariable Long id , Model model) {
		ImageLogo imageLogo = imageLogoSv.getById(id);
		model.addAttribute("imagelogo", imageLogo);
		model.addAttribute("isAdd", false);
		return "admin/views/imagelogo";
	}
	
	@RequestMapping(value = "/update/imagelogo", method = RequestMethod.POST)
	public String update(@ModelAttribute ImageLogo imageLogo, RedirectAttributes redirectAttributes, Model model) {
		ImageLogo dbImageLogo = imageLogoSv.update(imageLogo);
		if (dbImageLogo != null) {
			redirectAttributes.addFlashAttribute("successmessage", "Cập nhật ảnh thành công");
			return "redirect:/admin/image";
		} else {
			model.addAttribute("errormessage", "Cập nhật ảnh thất bại");
			model.addAttribute("imageLogo", imageLogo);
			return "admin/views/imagelogo";
		}

	}
	
	@RequestMapping(value = "/deleteimagelogo/{id}")
	public String deleteImageLogo(@PathVariable Long id, RedirectAttributes redirectAttributes, Model model) {
		imageLogoSv.delete(id);
		redirectAttributes.addFlashAttribute("successmessage", "Xóa ảnh thành công");
		return "redirect:/admin/image";

	}
	
	@RequestMapping("/newimage570")
	public String newImage570(Model model) {
		model.addAttribute("image570", new Image570());
		model.addAttribute("isAdd", true);
		return "admin/views/image570";
	}
	
	@PostMapping(value = "/save/image570")
	public String saveImage570(@ModelAttribute Image570 image, RedirectAttributes redirectAttributes, Model model) {
		Image570 dbImage570 = image570Sv.save(image);
		if (dbImage570 != null) {
			model.addAttribute("successmessage", "Thêm mới ảnh thành công");
			return "redirect:/admin/image";
		} else {
			model.addAttribute("errormessage", "Thêm mới ảnh thất bại");
			model.addAttribute("imageLogo", image);
			return "redirect:/admin/image570";
		}

	}
	
	@GetMapping("/image570/edit/{id}")
	public String editImage570(@PathVariable Long id , Model model) {
		Image570 image = image570Sv.getById(id);
		model.addAttribute("image570", image);
		model.addAttribute("isAdd", false);
		return "admin/views/image570";
	}
	
	@RequestMapping(value = "/update/image570", method = RequestMethod.POST)
	public String updateImage570(@ModelAttribute Image570 image, RedirectAttributes redirectAttributes, Model model) {
		Image570 dbImage570 = image570Sv.update(image);
		if (dbImage570 != null) {
			redirectAttributes.addFlashAttribute("successmessage", "Cập nhật ảnh thành công");
			return "redirect:/admin/image";
		} else {
			model.addAttribute("errormessage", "Cập nhật ảnh thất bại");
			model.addAttribute("image570", image);
			return "admin/views/image570";
		}

	}
	
	@RequestMapping(value = "/deleteimage570/{id}")
	public String deleteImage570(@PathVariable Long id, RedirectAttributes redirectAttributes, Model model) {
		image570Sv.delete(id);
		redirectAttributes.addFlashAttribute("successmessage", "Xóa ảnh thành công");
		return "redirect:/admin/image";

	}
	
	@RequestMapping("/newimage166")
	public String newImage166(Model model) {
		model.addAttribute("image166", new Image166());
		model.addAttribute("isAdd", true);
		return "admin/views/image166";
	}
	
	@PostMapping(value = "/save/image166")
	public String saveImage166(@ModelAttribute Image166 image, RedirectAttributes redirectAttributes, Model model) {
		Image166 dbImage166 = image166Sv.save(image);
		if (dbImage166 != null) {
			model.addAttribute("successmessage", "Thêm mới ảnh thành công");
			return "redirect:/admin/image";
		} else {
			model.addAttribute("errormessage", "Thêm mới ảnh thất bại");
			model.addAttribute("imageLogo", image);
			return "redirect:/admin/image166";
		}

	}
	
	@GetMapping("/image166/edit/{id}")
	public String editImage166(@PathVariable Long id , Model model) {
		Image166 image = image166Sv.getById(id);
		model.addAttribute("image166", image);
		model.addAttribute("isAdd", false);
		return "admin/views/image166";
	}
	
	@RequestMapping(value = "/update/image166", method = RequestMethod.POST)
	public String updateImage166(@ModelAttribute Image166 image, RedirectAttributes redirectAttributes, Model model) {
		Image166 dbImage166 = image166Sv.update(image);
		if (dbImage166 != null) {
			redirectAttributes.addFlashAttribute("successmessage", "Cập nhật ảnh thành công");
			return "redirect:/admin/image";
		} else {
			model.addAttribute("errormessage", "Cập nhật ảnh thất bại");
			model.addAttribute("image166", image);
			return "admin/views/image166";
		}

	}
	
	@RequestMapping(value = "/deleteimage166/{id}")
	public String deleteImage166(@PathVariable Long id, RedirectAttributes redirectAttributes, Model model) {
		image166Sv.delete(id);
		redirectAttributes.addFlashAttribute("successmessage", "Xóa ảnh thành công");
		return "redirect:/admin/image";

	}


	
}
