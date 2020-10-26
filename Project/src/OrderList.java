import java.util.ArrayList;
import java.util.Iterator;

public class OrderList {

	private ArrayList<Order> orders;

	public OrderList() {
		this.orders = new ArrayList<Order>();
	}

	public boolean addOrder(Order order) {
		if (order != null && this.orders.contains(order) != true) {
			this.orders.add(order);
			return true;
		}

		return false;
	}

	public Order getOrder(int orderId) {
		Iterator<Order> iter = this.orders.iterator();

		while (iter.hasNext()) {
			if (iter.next().getOrderId() == orderId) {
				return iter.next();
			}
		}

		return null;
	}

	public boolean removeOrder(int orderId) {
		Iterator<Order> iter = this.orders.iterator();

		while (iter.hasNext()) {
			if (iter.next().getOrderId() == orderId) {
				this.orders.remove(iter.next());
				return true;
			}
		}

		return false;
	}

	public boolean editOrder(int orderId, Order order) {
		Iterator<Order> iter = this.orders.iterator();

		while (iter.hasNext()) {
			if (iter.next().getOrderId() == orderId) {
				this.orders.remove(iter.next());
				this.orders.add(order);
				return true;
			}
		}

		return false;
	}

	public boolean fullfillOrder(int orderId) {
		Iterator<Order> iter = this.orders.iterator();

		while (iter.hasNext()) {
			if (iter.next().getOrderId() == orderId) {
				iter.next().setFullfilled(true);
				return true;
			}
		}

		return false;
	}
}
