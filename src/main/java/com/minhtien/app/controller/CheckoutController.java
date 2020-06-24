package com.minhtien.app.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.minhtien.app.model.Address;
import com.minhtien.app.model.Item;
import com.minhtien.app.model.Order;
import com.minhtien.app.model.OrderDetail;
import com.minhtien.app.model.ThuongHieu;
import com.minhtien.app.service.AddressService;
import com.minhtien.app.service.OrderDetailService;
import com.minhtien.app.service.OrderService;
import com.minhtien.app.service.ThuongHieuService;

@Controller
public class CheckoutController {

	@Autowired
	private ThuongHieuService thuongHieuService;
	
	@Autowired	
	private AddressService addressSv;
	
	@Autowired	
	private OrderService orderSv;
	
	@Autowired	
	private OrderDetailService orderDetailSv;
	
	@RequestMapping("/checkout")
	public String checkout(Model model,HttpSession session) {
		List<ThuongHieu> thuongHieus = thuongHieuService.listAll();
		model.addAttribute("thuonghieus", thuongHieus);
		AddtoCartController cartController = new AddtoCartController();
		model.addAttribute("totalPrice", cartController.totalPrice(session));
		model.addAttribute("totalItem", cartController.totalItem(session));
		return "views/checkout";
	}
	
	@PostMapping("/checkoutorder")
	public String checkoutOder(@ModelAttribute Address address,Model model,HttpSession session) {
		Address dbAddress = addressSv.save(address);
		if (dbAddress != null) {
			Order order = new Order();
			order.setAddress(dbAddress);
			order.setStatus(0);
			orderSv.save(order);
			
			List<Item> items = (List<Item>) session.getAttribute("cart");
			for (Item item : items) {
				OrderDetail detail = new OrderDetail();
				detail.setDieuHoa(item.getProduct());
				detail.setOrder(order);
				detail.setQuantity(item.getQuantity());
				orderDetailSv.save(detail);
			}
			
			
			model.addAttribute("successmessage", "Mua thành công");
			return "redirect:/home";
		} else {
			model.addAttribute("errormessage", "Thêm mới thương hiệu thất bại");
			model.addAttribute("address", address);
			return "redirect:/checkout";
		}
		
	}
	
}
