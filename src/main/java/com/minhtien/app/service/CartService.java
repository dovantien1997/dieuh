//package com.minhtien.app.service;
//
//import java.util.List;
//
//import org.springframework.stereotype.Service;
//
//import com.minhtien.app.model.AddtoCart;
//import com.minhtien.app.model.CheckoutCart;
//
//@Service
//public interface CartService {
//	
//	List<AddtoCart> addCartbyUserIdAndProductId(long productId, long userId, int qty, int price,String nameImage) throws Exception;
//
//	void updateQtyByCartId(long cartId, int qty, int price) throws Exception;
//
//	List<AddtoCart> getCartByUserId(long userId);
//
//	List<AddtoCart> removeCartByUserId(long cartId, long userId);
//
//	List<AddtoCart> removeAllCartByUserId(long userId);
//
//	Boolean checkTotalAmountAgainstCart(int totalAmount, long userId);
//
//	List<CheckoutCart> getAllCheckoutByUserId(long userId);
//
//	List<CheckoutCart> saveProductsForCheckout(List<CheckoutCart> tmp) throws Exception;
//
//	// CheckOutCart
//}
