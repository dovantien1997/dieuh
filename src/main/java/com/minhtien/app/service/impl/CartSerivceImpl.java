//package com.minhtien.app.service.impl;
//
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.minhtien.app.model.AddtoCart;
//import com.minhtien.app.model.CheckoutCart;
//import com.minhtien.app.model.DieuHoa;
//import com.minhtien.app.model.User;
//import com.minhtien.app.repository.AddToCartRepo;
//import com.minhtien.app.repository.CheckoutRepo;
//import com.minhtien.app.service.CartService;
//import com.minhtien.app.service.DieuHoaService;
//import com.minhtien.app.service.UserService;
//
//@Service
//public class CartSerivceImpl implements CartService {
//
//	@Autowired
//	private AddToCartRepo addCartRepo;
//	
//	@Autowired
//	private CheckoutRepo checkOutRepo;
//	
//	@Autowired
//	private DieuHoaService dieuHoaService;
//	
//	@Autowired 
//	private UserService userService;
//
//	private static final Logger logger = LoggerFactory.getLogger(CartSerivceImpl.class);
//
//	@Override
//	public List<AddtoCart> addCartbyUserIdAndProductId(long productId, long userId, int qty, int price,
//			String nameImage) throws Exception {
//		try {
//			if (addCartRepo.getCartByProductIdAnduserId(userId, productId).isPresent()) {
//				throw new Exception("Product is already exist.");
//			}
//			AddtoCart obj = new AddtoCart();
//			obj.setQty(qty);
//			User user = userService.getUserDetailById(userId);
//			obj.setUser_id(user);
//			DieuHoa pro = dieuHoaService.getById(productId);
//			obj.setProduct_id(pro);
//			obj.setPrice(price);
//			addCartRepo.save(obj);
//			return this.getCartByUserId(userId);
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error("" + e.getMessage());
//			throw new Exception(e.getMessage());
//		}
//
//	}
//
//	@Override
//	public List<AddtoCart> getCartByUserId(long userId) {
//		return addCartRepo.getCartByuserId(userId);
//	}
//
//	@Override
//	public List<AddtoCart> removeCartByUserId(long cartId, long userId) {
//		addCartRepo.deleteCartByIdAndUserId(userId, cartId);
//		return this.getCartByUserId(userId);
//	}
//
//	@Override
//	public void updateQtyByCartId(long cartId, int qty, int price) throws Exception {
//		addCartRepo.updateQtyByCartId(cartId, price, qty);
//	}
//
//	@Override
//	public Boolean checkTotalAmountAgainstCart(int totalAmount, long userId) {
//		double total_amount = addCartRepo.getTotalAmountByUserId(userId);
//		if (total_amount == totalAmount) {
//			return true;
//		}
//		System.out.print("Error from request " + total_amount + " --db-- " + totalAmount);
//		return false;
//	}
//
//	@Override
//	public List<CheckoutCart> getAllCheckoutByUserId(long userId) {
//		return checkOutRepo.getByuserId(userId);
//	}
//
//	@Override
//	public List<CheckoutCart> saveProductsForCheckout(List<CheckoutCart> tmp) throws Exception {
//		try {
//			long user_id = tmp.get(0).getId();
//			if (tmp.size() > 0) {
//				checkOutRepo.saveAll(tmp);
//				this.removeAllCartByUserId(user_id);
//				return this.getAllCheckoutByUserId(user_id);
//			} else {
//				throw new Exception("Should not be empty");
//			}
//		} catch (Exception e) {
//			throw new Exception("Error while checkout " + e.getMessage());
//		}
//
//	}
//
//	@Override
//	public List<AddtoCart> removeAllCartByUserId(long userId) {
//		addCartRepo.deleteAllCartByUserId(userId);
//		return null;
//	}
//
//}
