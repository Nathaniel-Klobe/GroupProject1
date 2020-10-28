import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class OrderList implements Serializable {

	private static final long serialVersionUID = 1L;
	private ArrayList<Order> orders;
	private static OrderList instance = null;

	private OrderList() {
		this.orders = new ArrayList<Order>();
	}

	public static OrderList instance() {
		if (instance == null) {
			return (instance = new OrderList());
		} else {
			return instance;
		}
	}

	public boolean addOrder(Order order) {
		return this.orders.add(order);
	}

	public Order search(int orderId) {
		for (Iterator<Order> iter = this.orders.iterator(); iter.hasNext();) {
			Order order = iter.next();
			if (order.getOrderId() == orderId) {
				return order;
			}
		}
		return null;
	}

	public boolean removeOrder(int orderId) {
		Order order = this.search(orderId);
		if (order == null) {
			return false;
		} else {
			return this.orders.remove(order);
		}
	}

	public boolean fullfillOrder(int orderId) {
		Order order = this.search(orderId);
		if (order == null) {
			return false;
		} else {
			order.setFulfilled(true);
			return true;
		}
	}

	public Iterator<Order> getOutstandingOrders() {
		ArrayList<Order> outstandingOrders = new ArrayList<Order>();

		for (Iterator<Order> iter = this.orders.iterator(); iter.hasNext();) {
			Order order = iter.next();
			if (order.getFullfilled() == false) {
				outstandingOrders.add(order);
			}
		}

		return outstandingOrders.iterator();
	}

}
