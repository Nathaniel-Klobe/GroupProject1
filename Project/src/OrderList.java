import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class OrderList {

	private ArrayList<Order> orders;

	public OrderList() {
		this.orders = new ArrayList<Order>();
	}

	public void addOrder(int componentId, int supplierId, int quantity) {
		Order order = new Order(componentId, supplierId, quantity, this.generateId());
		this.orders.add(order);
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
				iter.next().setFulfilled(true);
				return true;
			}
		}

		return false;
	}

	public Iterator<Order> getOutstandingOrders() {
		ArrayList<Order> outstandingOrders = new ArrayList<Order>();

		Iterator<Order> iter = this.orders.iterator();

		while (iter.hasNext()) {
			if (iter.next().getFullfilled() == false) {
				outstandingOrders.add(iter.next());
			}
		}

		return outstandingOrders.iterator();
	}

	private int generateId() {

		while (true) {
			boolean exists = false;
			Random rand = new Random();
			int id = rand.nextInt();

			Iterator<Order> iter = this.orders.iterator();
			while (iter.hasNext()) {
				if (iter.next().getOrderId() == id) {
					exists = true;
				}
			}

			if (exists == false) {
				return id;
			}
		}

	}
}
