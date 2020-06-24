package com.minhtien.app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.minhtien.app.model.CongNghe;
import com.minhtien.app.model.DieuHoa;
import com.minhtien.app.model.Image880;
import com.minhtien.app.model.Item;
import com.minhtien.app.model.LoaiMay;
import com.minhtien.app.model.LoaiSanPham;
import com.minhtien.app.model.ThuongHieu;
import com.minhtien.app.service.CongNgheService;
import com.minhtien.app.service.DieuHoaService;
import com.minhtien.app.service.Image880Service;
import com.minhtien.app.service.LoaiMayService;
import com.minhtien.app.service.LoaiSanPhamService;
import com.minhtien.app.service.ThuongHieuService;

@Controller
public class AddtoCartController {

//	@Autowired
//	private CartService cartService;

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

	@Autowired
	private Image880Service imageService;

	@RequestMapping("/home")
	public String homex(Model model, HttpSession session) {
		List<DieuHoa> dieuHoas = dieuHoaService.listAllDieuHoaByStatus();
		List<CongNghe> congNghes = congNgheService.listAll();
		List<LoaiSanPham> loaiSanPhams = loaiSanPhamService.listAll();
		List<LoaiMay> loaiMays = loaiMayService.listAll();
		List<ThuongHieu> thuongHieus = thuongHieuService.listAll();
		List<DieuHoa> hotdeals = dieuHoaService.hotDealsDieuHoa();
		List<Image880> images = imageService.listAll();
		model.addAttribute("dieuhoas", dieuHoas);
		model.addAttribute("congnghes", congNghes);
		model.addAttribute("loaisanphams", loaiSanPhams);
		model.addAttribute("loaimays", loaiMays);
		model.addAttribute("thuonghieus", thuongHieus);
		model.addAttribute("hotdeals", hotdeals);
		model.addAttribute("images880", images);
		List<DieuHoa> dieuHoas2 = new ArrayList<>();
		for (ThuongHieu hieu : thuongHieus) {

			List<DieuHoa> dhs = dieuHoaService.findDieuHoaByThuongHieuId(hieu.getId());
			for (DieuHoa dieuHoa : dhs) {
				dieuHoas2.add(dieuHoa);
			}
			dhs.clear();
		}

		model.addAttribute("dieuHoas2", dieuHoas2);
		model.addAttribute("totalPrice", totalPrice(session));
		model.addAttribute("totalItem", totalItem(session));

		return "index";

	}

	@GetMapping("/deleteItem/{productId}")
	public String deleteItem(@PathVariable Long productId, HttpSession session) {
		List<Item> items = (List<Item>) session.getAttribute("cart");
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getProduct().getId().equals(productId)) {
				items.remove(items.get(i));
				session.setAttribute("cart", items);
				break;
			}
		}

		return "redirect:/home";
	}

	@GetMapping("/addProduct/{productId}")
	public String addCartwithProduct(@PathVariable Long productId, HttpSession session, Model model) {

		Item item = new Item();
		DieuHoa dieuHoa = dieuHoaService.getById(productId);
		if (session.getAttribute("cart") == null) {
			List<Item> cart = new ArrayList<Item>();
			item.setProduct(dieuHoa);
			item.setQuantity(1);
			cart.add(item);
			session.setAttribute("cart", cart);
		} else {
			List<Item> cart = (List<Item>) session.getAttribute("cart");
			int index = checkProductInCart(productId, cart);
			if (index == -1) {
				item.setProduct(dieuHoa);
				item.setQuantity(1);
				cart.add(item);
				session.setAttribute("cart", cart);
			} else {
				int quantity = cart.get(index).getQuantity() + 1;
				cart.get(index).setQuantity(quantity);
			}
			session.setAttribute("cart", cart);
		}

		return "redirect:/home";

	}

	@GetMapping("/addProduct/")
	public String addCartOrProduct(@RequestParam Long id,@RequestParam int quantity, HttpSession session, Model model) {

		Item item = new Item();
		DieuHoa dieuHoa = dieuHoaService.getById(id);
		if (session.getAttribute("cart") == null) {
			List<Item> cart = new ArrayList<Item>();
			item.setProduct(dieuHoa);
			item.setQuantity(quantity);
			cart.add(item);
			session.setAttribute("cart", cart);
		} else {
			List<Item> cart = (List<Item>) session.getAttribute("cart");
			int index = checkProductInCart(id, cart);
			if (index == -1) {
				item.setProduct(dieuHoa);
				item.setQuantity(quantity);
				cart.add(item);
				session.setAttribute("cart", cart);
			} else {
				int qty = cart.get(index).getQuantity() + quantity;
				cart.get(index).setQuantity(qty);
			}
			session.setAttribute("cart", cart);
		}

		return "redirect:/home";

	}
	
	@RequestMapping("/addCheckout/{id}")
	public String addCheckoutProduct(@PathVariable Long id, HttpSession session, Model model) {

		Item item = new Item();
		DieuHoa dieuHoa = dieuHoaService.getById(id);
		if (session.getAttribute("cart") == null) {
			List<Item> cart = new ArrayList<Item>();
			item.setProduct(dieuHoa);
			item.setQuantity(1);
			cart.add(item);
			session.setAttribute("cart", cart);
		} else {
			List<Item> cart = (List<Item>) session.getAttribute("cart");
			int index = checkProductInCart(id, cart);
			if (index == -1) {
				item.setProduct(dieuHoa);
				item.setQuantity(1);
				cart.add(item);
				session.setAttribute("cart", cart);
			} else {
				int qty = cart.get(index).getQuantity() + 1;
				cart.get(index).setQuantity(qty);
			}
			session.setAttribute("cart", cart);
		}

		return "redirect:/shopping";

	}

	@RequestMapping("/shopping")
	public String shoppingCart(HttpSession session, Model model) {
		List<ThuongHieu> thuongHieus = thuongHieuService.listAll();
		model.addAttribute("thuonghieus", thuongHieus);
		model.addAttribute("totalPrice", totalPrice(session));
		model.addAttribute("totalItem", totalItem(session));
		session.setAttribute("cart", session.getAttribute("cart"));
		return "views/shopping-cart";
	}

	@PostMapping("/updateCart")
	public String updateCart(Model model, HttpSession session, HttpServletRequest request) {
		List<Item> listItems = (List<Item>) session.getAttribute("cart");
		String[] qty = request.getParameterValues("quantity");
		int length = listItems.size();
		for (int i = 0; i < length; i++) {
			listItems.get(i).setQuantity(Integer.parseInt(qty[i]));
		}
		session.setAttribute("cart", listItems);

		return "redirect:/shopping";

	}

	@RequestMapping("/clearCart")
	public String removeCart(HttpSession session) {
		session.removeAttribute("cart");
		session.removeAttribute("totalPrice");
		session.removeAttribute("totalItem");
		return "redirect:/shopping";
	}
//	
//	@RequestMapping("getCartsByUserId")
//  	public ResponseEntity<?> getCartsByUserId(@RequestBody HashMap<String,String> getCartRequest) {
//		try {
//			String keys[] = {"userId"};
//			if(ShoppingConfiguration.validationWithHashMap(keys, getCartRequest)) {
//			}
//			List<AddtoCart> obj = cartService.getCartByUserId(Long.parseLong(getCartRequest.get("userId")));
//			return ResponseEntity.ok(obj);
//		}catch(Exception e) {
//				return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
//		}	
//   }

	public int checkProductInCart(long productId, List<Item> items) {

		int length = items.size();

		for (int i = 0; i < length; i++) {
			if (items.get(i).getProduct().getId().equals(productId)) {
				return i;
			}
		}
		return -1;

	}

	public int totalPrice(HttpSession session) {
		int total = 0;
		if (session.getAttribute("cart") != null) {
			List<Item> items = (List<Item>) session.getAttribute("cart");
			for (Item item : items) {
				total += (item.getProduct().getPrice()
						- item.getProduct().getPrice() * item.getProduct().getSale() / 100) * item.getQuantity();
			}
		}
		return total;

	}

	public int totalItem(HttpSession session) {
		int total = 0;
		if (session.getAttribute("cart") != null) {
			List<Item> items = (List<Item>) session.getAttribute("cart");
			for (Item item : items) {
				total += item.getQuantity();
			}
		}

		return total;

	}
}
