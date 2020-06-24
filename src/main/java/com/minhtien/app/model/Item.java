package com.minhtien.app.model;

public class Item {

	private DieuHoa product;
	private int quantity;

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public DieuHoa getProduct() {
		return product;
	}

	public void setProduct(DieuHoa product) {
		this.product = product;
	}

	public Item(DieuHoa product, int quantity) {
		super();
		this.product = product;
		this.quantity = quantity;
	}

	public Item() {
		super();
	}

}
