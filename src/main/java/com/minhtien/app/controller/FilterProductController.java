package com.minhtien.app.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.minhtien.app.model.CongNghe;
import com.minhtien.app.model.DieuHoa;
import com.minhtien.app.model.LoaiMay;
import com.minhtien.app.model.LoaiSanPham;
import com.minhtien.app.model.ThuongHieu;
import com.minhtien.app.service.CongNgheService;
import com.minhtien.app.service.DieuHoaService;
import com.minhtien.app.service.LoaiMayService;
import com.minhtien.app.service.LoaiSanPhamService;
import com.minhtien.app.service.ThuongHieuService;

@Controller
public class FilterProductController {

	@Autowired
	private DieuHoaService dieuHoaSv;

	@Autowired
	private CongNgheService congNgheService;

	@Autowired
	private LoaiSanPhamService loaiSanPhamService;

	@Autowired
	private LoaiMayService loaiMayService;

	@Autowired
	private ThuongHieuService thuongHieuService;

	@Autowired
	private EntityManager em;
	
	private int page;

	@GetMapping("/filter")
	public String home() {
		return "redirect:/products";
	}

	@GetMapping("/products")
	public String congnghe(Model model, HttpServletRequest request, RedirectAttributes redirect) {
		request.getSession().setAttribute("productlist", null);

		if (model.asMap().get("success") != null)
			redirect.addFlashAttribute("success", model.asMap().get("success").toString());
		return "redirect:/products/page/1";
	}

	@GetMapping("/products/page/{pageNumber}")
	public String showcongnghePage(HttpServletRequest request, @PathVariable int pageNumber, Model model,
			HttpSession session) {
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("productlist");
		int pagesize = 16;
		List<DieuHoa> list = (List<DieuHoa>) dieuHoaSv.listAllDieuHoaByStatus();
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
		request.getSession().setAttribute("productlist", pages);
		int current = pages.getPage() + 1;
		int begin = Math.max(1, current - list.size());
		if (pageNumber < 1) {
			pageNumber = 1;
		}

		int end = Math.min(begin + 16, pages.getPageCount());
		if (pageNumber > current) {
			pageNumber = current;
		}
		
		if(pageNumber != 1) {
			page = (pageNumber-1)*2;
		}else {
			page = 0;
		}
		int totalPageCount = pages.getPageCount();
		String baseUrl = "/products/page/";
		List<CongNghe> congNghes = congNgheService.listAll();
		List<LoaiSanPham> loaiSanPhams = loaiSanPhamService.listAll();
		List<LoaiMay> loaiMays = loaiMayService.listAll();
		List<ThuongHieu> thuongHieus = thuongHieuService.listAll();
		AddtoCartController cartController = new AddtoCartController();
		model.addAttribute("totalPrice", cartController.totalPrice(session));
		model.addAttribute("totalItem", cartController.totalItem(session));
		model.addAttribute("congnghes", congNghes);
		model.addAttribute("loaisanphams", loaiSanPhams);
		model.addAttribute("loaimays", loaiMays);
		model.addAttribute("thuonghieus", thuongHieus);

		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("baseUrl", baseUrl);
		model.addAttribute("products", pages);

		return "views/categories-grid";
	}

	public StringBuilder query = new StringBuilder();

	@RequestMapping(value = "search/{min}/{max}/{lm}/{lsp}/{th}/{cn}", method = RequestMethod.GET, produces = {
			MimeTypeUtils.APPLICATION_JSON_VALUE }, headers = { "Accept=application/json" })
	public ResponseEntity<List<DieuHoa>> search(@PathVariable("min") String min, @PathVariable("max") String max,
			@PathVariable("lm") List<String> lm, @PathVariable("lsp") List<String> lsp,
			@PathVariable("th") List<String> th, @PathVariable("cn") List<String> cn, HttpSession session) {
//		Comparator<DieuHoa> comparator = Comparator.comparing(DieuHoa::getPrice);
//		List<DieuHoa> dieuHoas = dieuHoaService.listAll();
//		DieuHoa maxprice = dieuHoas.stream().max(comparator).get();
//
//		if(Integer.parseInt(min) == 0) {
//			max = String.valueOf(maxprice.getPrice());
//		}else {
//			
//		}

		query.append("select d from DieuHoa d where price between ");
		query.append(min);
		query.append(" and ");
		query.append(max);

		int lenglm = lm.size();
		for (int i = 0; i < lenglm; i++) {
			if (i == 0 && Integer.parseInt(lm.get(i)) > 0) {
				query.append(" and d.loaiMay.id = ");
				query.append(lm.get(i));
			}
			if (i >= 1) {
				query.append(" or d.loaiMay.id = ");
				query.append(lm.get(i));
			}
		}
		int lenglsp = lsp.size();
		for (int i = 0; i < lenglsp; i++) {
			if (i == 0 && Integer.parseInt(lsp.get(i)) > 0) {
				query.append(" and d.loaiSanPham.id = ");
				query.append(lsp.get(i));
			}
			if (i >= 1) {
				query.append(" or d.loaiSanPham.id = ");
				query.append(lsp.get(i));
			}
		}

		int length = th.size();
		for (int i = 0; i < length; i++) {
			if (i == 0 && Integer.parseInt(th.get(i)) > 0) {
				query.append(" and d.thuongHieu.id = ");
				query.append(th.get(i));
			}
			if (i >= 1) {
				query.append(" or d.thuongHieu.id = ");
				query.append(th.get(i));
			}
		}

		int lengcn = cn.size();
		for (int i = 0; i < lengcn; i++) {
			if (i == 0 && Integer.parseInt(cn.get(i)) > 0) {
				query.append(" and d.congNghe.id = ");
				query.append(cn.get(i));
			}
			if (i >= 1) {
				query.append(" or d.congNghe.id = ");
				query.append(cn.get(i));
			}
		}
		query.append(" and d.status = 1");

		try {
			TypedQuery<DieuHoa> listQuery = em.createQuery(query.toString(), DieuHoa.class).setFirstResult(page).setMaxResults(16);
			query.delete(0, query.length());
			return new ResponseEntity<List<DieuHoa>>(listQuery.getResultList(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<DieuHoa>>(HttpStatus.BAD_REQUEST);
		}
	}

}
