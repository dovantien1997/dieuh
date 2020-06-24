//package com.minhtien.app.model;
//
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToMany;
//import javax.persistence.OneToMany;
//import javax.persistence.OneToOne;
//import javax.persistence.Transient;
//
//@Entity
//public class AddtoCart extends ModelClass {
//
//	private DieuHoa product_id;
//
//	private int qty;
//	private int price;
//
//	@OneToOne
//	private User user_id;
//
//	@Transient
//	private String productName;
//
//	private String nameImage;
//
//	public DieuHoa getProduct_id() {
//		return product_id;
//	}
//
//	public void setProduct_id(DieuHoa product_id) {
//		this.product_id = product_id;
//	}
//
//	public int getQty() {
//		return qty;
//	}
//
//	public void setQty(int qty) {
//		this.qty = qty;
//	}
//
//	public int getPrice() {
//		return price;
//	}
//
//	public void setPrice(int price) {
//		this.price = price;
//	}
//
//	public User getUser_id() {
//		return user_id;
//	}
//
//	public void setUser_id(User user_id) {
//		this.user_id = user_id;
//	}
//
//	public String getProductName() {
//		return productName;
//	}
//
//	public void setProductName(String productName) {
//		this.productName = productName;
//	}
//
//	public String getNameImage() {
//		return nameImage;
//	}
//
//	public void setNameImage(String nameImage) {
//		this.nameImage = nameImage;
//	}
//
//}
