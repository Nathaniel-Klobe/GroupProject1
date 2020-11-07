
/**
 * This class creates and stores the ArrayList containing Orders.
 * Methods:addOrder, search, removeOrder, fullfillOrder, and getOutstandingOrders.
 * 
 * @authors Jonathan Bracamontes, Anou Keohavong, Nathaniel Klobe, and Christopher Pellegrino
 */

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

	/**
	 * Creates and returns the Singleton instance of OrderList.
	 */
	public static OrderList instance() {
		if (instance == null) {
			return (instance = new OrderList());
		} else {
			return instance;
		}
	}

	/**
	 * This method adds the order to the ArrayList.
	 * 
	 * @param order
	 * @return boolean from the add method of ArrayList.
	 */
	public boolean addOrder(Order order) {
		return this.orders.add(order);
	}

	/**
	 * This method takes the id for an order and searches through the ArrayList.
	 * 
	 * @param orderId
	 * @return component or null
	 */
	public Order search(int orderId) {
		for (Iterator<Order> iter = this.orders.iterator(); iter.hasNext();) {
			Order order = iter.next();
			if (order.getOrderId() == orderId) {
				return order;
			}
		}
		return null;
	}

	/**
	 * This method removes the order from the ArrayList.
	 * 
	 * @param orderId
	 * @return boolean based on remove method from ArrayList.
	 */
	public boolean removeOrder(int orderId) {
		Order order = this.search(orderId);
		if (order == null) {
			return false;
		} else {
			return this.orders.remove(order);
		}
	}

	/**
	 * This method sets order as fulfilled.
	 * 
	 * @param orderId, for search.
	 * @return boolean based on success.
	 */
	public boolean fullfillOrder(int orderId) {
		Order order = this.search(orderId);
		if (order == null) {
			return false;
		} else {
			order.setFulfilled(true);
			return true;
		}
	}

	/**
	 * This method returns an iterator of outstanding orders.
	 * 
	 * @return outstandingOrders iterator
	 */
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
