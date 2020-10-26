
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

	// add Supplier

	// add Component Supplier

	// assign component to product

	// order components

	// order fullfilled

	// list component with its suppliers

	// list supplier with it components

	// display outstanding orders

	// display all components

	// display all suppliers

	// save to disk? possibly place elsewhere
}
