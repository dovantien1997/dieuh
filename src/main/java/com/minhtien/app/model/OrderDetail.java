package com.minhtien.app.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class OrderDetail extends ModelClass {

	@ManyToOne
	private Order order;

	@OneToOne
	private DieuHoa dieuHoa;

	private int quantity;

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public DieuHoa getDieuHoa() {
		return dieuHoa;
	}

	public void setDieuHoa(DieuHoa dieuHoa) {
		this.dieuHoa = dieuHoa;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	

}
