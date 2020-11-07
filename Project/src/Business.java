
/**
 * This class implements the Singleton Business.
 * Methods: addComponent, addSupplier, addComponentSupplier, assignComponent, 
 * placeOrder, fulfillOrder, listComponent, listSupplier, 
 * displayOutstandingOrders, displayComponents, displaySuppliers, save, and retrieve.
 * 
 * @authors Jonathan Bracamontes, Anou Keohavong, Nathaniel Klobe, and Christopher Pellegrino
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;

public class Business implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final int COMPONENT_ID_ERROR = 1;
	public static final int SUPPLIER_ID_ERROR = 2;
	public static final int ORDER_ID_ERROR = 3;
	public static final int ORDER_ALREADY_FULFILLED = 4;
	public static final int QUANTITY_ERROR = 5;
	public static final int OPERATION_COMPLETED = 6;
	public static final int OPERATION_FAILED = 7;
	public static final int COMPONENT_NOT_FOUND = 8;
	private ComponentList componentCatalog;
	private SupplierList supplierCatalog;
	private OrderList orderCatalog;

	private static Business business = null;

	private Business() {
		componentCatalog = ComponentList.instance();
		supplierCatalog = SupplierList.instance();
		orderCatalog = OrderList.instance();
	}

	/**
	 * Creates and returns the Singleton instance of Business and instantiates all
	 * singletons.
	 */
	public static Business instance() {
		if (business == null) {
			IdServer.instance(); // instantiate all singletons
			return (business = new Business());
		}

		return business;
	}

	/**
	 * This method calls upon the componentList method addComponent to receive a
	 * boolean value and add the new component.
	 * 
	 * @param name, a String variable for the component.
	 * @return component or null
	 */
	public Component addComponent(String name) {
		Component component = new Component(name);
		if (componentCatalog.addComponent(component)) {
			return component;
		}
		return null;
	}

	/**
	 * This method calls upon the supplierList method addSupplier to receive a
	 * boolean value and add the new supplier.
	 * 
	 * @param name, a String variable for the supplier.
	 * @return supplier or null
	 */

	public Supplier addSupplier(String name) {
		Supplier supplier = new Supplier(name);
		if (supplierCatalog.addSupplier(supplier)) {
			return supplier;
		}
		return null;
	}

	/**
	 * This method associates a component and supplier object.
	 * 
	 * @param componentId, to find the component.
	 * @param supplierId,  to find the supplier.
	 * @return int associated message.
	 */
	public int addComponentSupplier(int componentId, int supplierId) {
		Component component = componentCatalog.search(componentId);
		Supplier supplier = supplierCatalog.search(supplierId);

		if (component == null) {
			return COMPONENT_ID_ERROR;
		}

		if (supplier == null) {
			return SUPPLIER_ID_ERROR;
		}

		// Create the relationship
		component.addSupplier(supplier);
		supplier.addComponent(component);

		return OPERATION_COMPLETED;
	}

	/**
	 * This method assigns a quantity of components to a product.
	 * 
	 * @param componentId, to find component.
	 * @param quantity
	 * @return int associated message.
	 */
	public int assignComponent(int componentId, int quantity) {
		Component component = this.componentCatalog.search(componentId);

		if (component.getQuantity() <= 0) {
			return QUANTITY_ERROR;
		}

		component.setQuantity(component.getQuantity() - quantity);

		return OPERATION_COMPLETED;
	}

	/**
	 * This method creates a new order and places it in the OrderList.
	 * 
	 * @param componentId, to create new order.
	 * @param supplierId,  to create new order.
	 * @param quantity,    to create new order.
	 * @return order or null
	 */
	public Order placeOrder(int componentId, int supplierId, int quantity) {
		Order order = new Order(componentId, supplierId, quantity);
		if (orderCatalog.addOrder(order)) {
			return order;
		}

		return null;

	}

	/**
	 * This method sets a existing order as fullfilled.
	 * 
	 * @param orderId, to find order.
	 * @return int associated message.
	 */
	public int fulfillOrder(int orderId) {
		Order order = orderCatalog.search(orderId);

		if (order == null) {
			return ORDER_ID_ERROR;
		}

		if (order.getFullfilled() == true) {
			return ORDER_ALREADY_FULFILLED;
		}

		Component component = componentCatalog.search(order.getComponentId());
		component.setQuantity(component.getQuantity() - order.getQuantity());
		order.setFulfilled(true);

		return OPERATION_COMPLETED;
	}

	/**
	 * This method lists a component with its associated suppliers.
	 * 
	 * @param componentId, to find component.
	 * @return int associated message.
	 */
	public int listComponent(int componentId) {
		Component component = this.componentCatalog.search(componentId);

		if (component == null) {
			return COMPONENT_ID_ERROR;
		}

		Iterator<Supplier> iter = component.getSuppliers();
		System.out.println(component.getName() + " suppliers");

		while (iter.hasNext()) {
			System.out.println(iter.next());
		}

		return OPERATION_COMPLETED;
	}

	/**
	 * This method lists a supplier with their associated components.
	 * 
	 * @param supplierId, to find supplier.
	 * @return int associated message.
	 */
	public int listSupplier(int supplierId) {
		Supplier supplier = this.supplierCatalog.search(supplierId);

		if (supplier == null) {
			return SUPPLIER_ID_ERROR;
		}

		Iterator<Component> iter = supplier.getComponents();
		System.out.println(supplier.getName() + " components");

		while (iter.hasNext()) {
			System.out.println(iter.next());
		}

		return OPERATION_COMPLETED;
	}

	/**
	 * This method retrieves and returns the iterator from the method
	 * getOutstandingOrders in OrderList.
	 * 
	 * @return iter
	 */
	public Iterator displayOutstandingOrders() {
		Iterator<Order> iter = this.orderCatalog.getOutstandingOrders();
		System.out.println("Outstanding Orders\n");
		return iter;
	}

	/**
	 * This method retrieves and returns the iterator from the method getComponents
	 * in ComponentList.
	 * 
	 * @return iter
	 */
	public Iterator displayComponents() {
		Iterator<Component> iter = this.componentCatalog.getComponents();
		System.out.println("Components\n");
		return iter;
	}

	/**
	 * This method retrieves and returns the iterator from the method getSuppliers
	 * in SupplierList.
	 * 
	 * @return iter
	 */
	public Iterator displaySuppliers() {
		Iterator<Supplier> iter = this.supplierCatalog.getSuppliers();
		System.out.println("Suppliers\n");

		return iter;
	}

	/**
	 * This method saves the current instance of business.
	 * 
	 * @return boolean based on success.
	 */
	public static boolean save() {

		try {
			FileOutputStream file = new FileOutputStream("BusinessData");

			ObjectOutputStream out = new ObjectOutputStream(file);

			out.writeObject(business);
			out.writeObject(IdServer.instance());
			out.close();
			file.close();
			return true;
		} catch (IOException i) {
			i.printStackTrace();
			return false;
		}

	}

	/**
	 * This method retrieves a saved instance of business.
	 * 
	 * @return business or null
	 */
	public static Business retreive() {

		try {
			FileInputStream file = new FileInputStream("BusinessData");
			ObjectInputStream in = new ObjectInputStream(file);

			business = (Business) in.readObject();
			IdServer.retrieve(in);

			in.close();
			file.close();

			return business;

		} catch (ClassNotFoundException | IOException c) {
			c.printStackTrace();
			return null;
		}

	}

}
