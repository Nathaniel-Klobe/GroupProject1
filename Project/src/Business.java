import java.util.Iterator;

public class Business {

	private ComponentList componentCatalog;
	private SupplierList supplierCatalog;
	private OrderList orderCatalog;

	private static Business instance = null;

	private Business() {
		this.componentCatalog = new ComponentList();
		this.supplierCatalog = new SupplierList();
		this.orderCatalog = new OrderList();
	}

	public static Business getInstance() {
		if (instance == null) {
			instance = new Business();
		}

		return instance;
	}

	// add Component
	public String addComponent(String name) {
		if(name.isEmpty() || name.isBlank()) {
			return "Name is blank";
		}
		Component comp = new Component(name, generateId());
		this.componentCatalog.addComponent(comp);
		
		return "Component Added";
		
	}

	// add Supplier
	public String addSupplier(String name) {
		if(name.isEmpty() || name.isBlank()) {
			return "Name is blank";
		}
		Supplier supplier = new Supplier(name, generateId());
		this.supplierCatalog.addSupplier(supplier);
		
		return "Supplier Added";
	}
	
	// add Component Supplier
	public String addComponentSupplier(int componentId, int supplierId) {
		Component component = this.componentCatalog.getComponent(componentId);
		Supplier supplier = this.supplierCatalog.getSupplier(supplierId);
		
		if(component == null) {
			return "Component Id is bad.";
		}
		
		if(supplier == null) {
			return "Supplier Id is bad.";
		}
		
		//Create the relationship
		component.addSupplier(supplier);
		supplier.addComponent(component);
			
		return "Component/Supplier Relationship Created.";
	}

	// assign component to product
	public String assignComponent(int componentId, int quantity) {
		Component component = this.componentCatalog.getComponent(componentId);
		
		if(quantity <= 0) {
			return "Quantity is zero or less.";
		}
		
		if(component == null) {
			return "Component Id is bad.";
		}
		
		component.setQuantity(component.getQuantity() - quantity);
		
		return component.toString();
	}

	// order components
	public String placeOrder(int componentId, int supplierId, int quantity) {
		
		if(this.componentCatalog.getComponent(componentId) == null) {
			return "The Component Id is bad.";
		}
		
		if(this.supplierCatalog.getSupplier(supplierId) == null) {
			return "The Supplier Id is bad.";
		}
		
		if(quantity <= 0) {
			return "The quantity is zero or lower.";
		}
		
		Order order = new Order(componentId, supplierId, quantity);
		this.orderCatalog.addOrder(order);
		
		return "Order successfully created.";
		
	}

	// order fulfilled
	public String fulfillOrder(int orderId) {
		Order order = this.orderCatalog.getOrder(orderId);
		
		if(order == null) {
			return "The Order Id is bad.";
		}
		
		if(order.getFullfilled() == true) {
			return "That Order is already fulfilled.";
		}
		
		order.setFulfilled(true);
		
		return "The Order was marked as fulfilled.";
	}

	// list component with its suppliers
	public String listComponent(int componentId) {
		Component component = this.componentCatalog.getComponent(componentId);
		
		if(component == null) {
			return "The Component Id is bad.";
		}
		
		Iterator<Supplier> iter = component.getSuppliers();
		String suppliersString = "";
		
		while(iter.hasNext()) {
			suppliersString += iter.next().toString();
		}
		
		return component.toString() + suppliersString;
	}

	// list supplier with it components
	public String listSupplier(int supplierId) {
		Supplier supplier = this.supplierCatalog.getSupplier(supplierId);
		
		if(supplier == null) {
			return "The Supplier Id is bad.";
		}
		
		Iterator<Component> iter = supplier.getComponents();
		String componentsString = "";
		
		while(iter.hasNext()) {
			componentsString += iter.next().toString();
		}
		
		return supplier.toString() + componentsString;
	}

	// display outstanding orders
	public String displayOutstandingOrders() {
		String outstandingString = "";
		
		Iterator<Order> iter = this.orderCatalog.getOutstandingOrders();
		
		while(iter.hasNext()) {
			outstandingString += iter.next().toString();
		}
		
		return outstandingString;
	}

	// display all components
	public String displayComponents() {
		String componentsString = "";
		
		Iterator<Component> iter = this.componentCatalog.getComponents();
		
		while(iter.hasNext()) {
			componentsString += iter.next().toString();
		}
		
		return componentsString;
	}

	// display all suppliers
	public String displaySuppliers() {
		String suppliersString = "";
		
		Iterator<Supplier> iter = this.supplierCatalog.getSuppliers();
		
		while(iter.hasNext()) {
			suppliersString += iter.next().toString();
		}
		
		return suppliersString;
	}

	// save to disk? possibly place elsewhere
	
	//generateId
	private int generateId() {
		return 1;
	}
}
