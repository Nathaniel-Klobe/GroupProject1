import java.util.Objects;

public class Order {

	private int orderId;
	private int supplierId;
	private int componentId;
	private int quantity;
	private boolean fulfilled;

	public Order(int supplierId, int componentId, int quantity, int orderId) {
		this.setSupplierId(supplierId);
		this.setComponentId(componentId);
		this.setQuantity(quantity);
		this.setFulfilled(false);
		this.setOrderId(orderId);
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
		if (quantity > 0) {
			this.quantity = quantity;
		}
		// throw error
	}

	public void setFulfilled(boolean fulfilled) {
		this.fulfilled = fulfilled;
	}

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

	public String toString() {

		return "ComponentId: " + this.componentId + " SupplierId: " + this.supplierId + " OrderId: " + this.orderId
				+ " Quantity: " + this.quantity + "\n";
	}

}
