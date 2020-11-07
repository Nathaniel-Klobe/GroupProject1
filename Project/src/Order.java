
/**
 * This class creates the Order object of the Business.
 * Methods: getOrderId, getSupplierId, getComponentId, getQuantity, getFullfilled, setOrderId, 
 * setSupplierId, setComponentId, setQuantity, setFulfilled, equals, and toString.
 * 
 * @authors Jonathan Bracamontes, Anou Keohavong, Nathaniel Klobe, and Christopher Pellegrino
 */

import java.io.Serializable;
import java.util.Objects;

public class Order implements Serializable {

	private static final long serialVersionUID = 1L;
	private int orderId;
	private int supplierId;
	private int componentId;
	private int quantity;
	private boolean fulfilled;

	public Order(int componentId, int supplierId, int quantity) {
		this.setSupplierId(supplierId);
		this.setComponentId(componentId);
		this.setQuantity(quantity);
		this.setFulfilled(false);
		this.setOrderId(IdServer.instance().getOrderId());
	}

	public int getOrderId() {
		return this.orderId;
	}

	public int getSupplierId() {
		return this.supplierId;
	}

	public int getComponentId() {
		return this.componentId;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public boolean getFullfilled() {
		return this.fulfilled;
	}

	public void setOrderId(int id) {
		this.orderId = id;
	}

	public void setSupplierId(int id) {
		this.supplierId = id;
	}

	public void setComponentId(int id) {
		this.componentId = id;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setFulfilled(boolean fulfilled) {
		this.fulfilled = fulfilled;
	}

	/**
	 * equals method for the order class.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		Order order = (Order) obj;

		return Objects.equals(this.orderId, order.getOrderId())
				&& Objects.equals(this.componentId, order.getComponentId())
				&& Objects.equals(this.supplierId, order.getSupplierId())
				&& Objects.equals(this.quantity, order.getQuantity())
				&& Objects.equals(this.fulfilled, order.getFullfilled());
	}

	/**
	 * toString method for the order class.
	 */
	public String toString() {

		return "ComponentId: " + this.componentId + " SupplierId: " + this.supplierId + " OrderId: " + this.orderId
				+ " Quantity: " + this.quantity + "\n";
	}

}
