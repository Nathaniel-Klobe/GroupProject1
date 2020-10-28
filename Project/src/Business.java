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

	public static Business instance() {
		if (business == null) {
			IdServer.instance(); // instantiate all singletons
			return (business = new Business());
		}

		return business;
	}

	// add Component
	public Component addComponent(String name) {
		Component component = new Component(name);
		if (componentCatalog.addComponent(component)) {
			return component;
		}
		return null;
	}

	// add Supplier
	public Supplier addSupplier(String name) {
		Supplier supplier = new Supplier(name);
		if (supplierCatalog.addSupplier(supplier)) {
			return supplier;
		}
		return null;
	}

	// add Component Supplier
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

	// assign component to product
	public int assignComponent(int componentId, int quantity) {
		Component component = this.componentCatalog.search(componentId);

		if (quantity <= 0) {
			return QUANTITY_ERROR;
		}

		if (component == null) {
			return COMPONENT_NOT_FOUND;
		}

		component.setQuantity(component.getQuantity() - quantity);

		return OPERATION_COMPLETED;
	}

	// order components
	public Order placeOrder(int componentId, int supplierId, int quantity) {
		Order order = new Order(componentId, supplierId, quantity);
		if (orderCatalog.addOrder(order)) {
			return order;
		}
		return null;

	}

	// order fulfilled
	public int fulfillOrder(int orderId) {
		Order order = orderCatalog.search(orderId);

		if (order == null) {
			return ORDER_ID_ERROR;
		}

		if (order.getFullfilled() == true) {
			return ORDER_ALREADY_FULFILLED;
		}

		order.setFulfilled(true);

		return OPERATION_COMPLETED;
	}

	// list component with its suppliers
	public int listComponent(int componentId) {
		Component component = this.componentCatalog.search(componentId);

		if (component == null) {
			return COMPONENT_ID_ERROR;
		}

		Iterator<Supplier> iter = component.getSuppliers();
		String suppliersString = iter.toString();

		System.out.println(component.toString() + suppliersString);

		return OPERATION_COMPLETED;
	}

	// list supplier with it components
	public int listSupplier(int supplierId) {
		Supplier supplier = this.supplierCatalog.search(supplierId);

		if (supplier == null) {
			return SUPPLIER_ID_ERROR;
		}

		Iterator<Component> iter = supplier.getComponents();
		String componentsString = iter.toString();

		System.out.println(supplier.toString() + componentsString);

		return OPERATION_COMPLETED;
	}

	// display outstanding orders
	public int displayOutstandingOrders() {
		Iterator<Order> iter = this.orderCatalog.getOutstandingOrders();

		String outstandingString = iter.toString();

		System.out.println(outstandingString);

		return OPERATION_COMPLETED;
	}

	// display all components
	public int displayComponents() {
		Iterator<Component> iter = this.componentCatalog.getComponents();

		String componentsString = iter.toString();

		System.out.println(componentsString);

		return OPERATION_COMPLETED;
	}

	// display all suppliers
	public int displaySuppliers() {

		Iterator<Supplier> iter = this.supplierCatalog.getSuppliers();

		String suppliersString = iter.toString();

		System.out.println(suppliersString);

		return OPERATION_COMPLETED;
	}

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
