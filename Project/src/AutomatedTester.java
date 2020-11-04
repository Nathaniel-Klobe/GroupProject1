
public class AutomatedTester {
	private Business business;

	public AutomatedTester(Business business) {
		this.business = business;
		test();

	}

	public void test() {
		String[] componentNames = { "c1", "c2", "c3", "c4", "c5", "c6", "c7", "c8", "c9", "c10", "c11", "c12", "c13",
				"c14", "c15", "c16", "c17", "c18", "c19", "c20" };
		String[] supplierNames = { "s1", "s2", "s3", "s4", "s5" };
		Component[] components = new Component[20];
		Supplier[] suppliers = new Supplier[5];
		Order[] orders = new Order[5];

		try {
			// Process 1
			for (int i = 0; i < componentNames.length; i++) {
				components[i] = business.addComponent(componentNames[i]);
				assert components[i].getName().equals(componentNames[i]);
			}

			// Process 2
			for (int i = 0; i < supplierNames.length; i++) {
				suppliers[i] = business.addSupplier(supplierNames[i]);
				assert suppliers[i].getName().equals(supplierNames[i]);
			}

			// Process 3
			assert business.addComponentSupplier(components[1].getId(), suppliers[1].getId()) == 6;
			assert business.addComponentSupplier(components[3].getId(), suppliers[2].getId()) == 6;

			// Process 4
			components[1].setQuantity(20);
			components[2].setQuantity(10);
			assert business.assignComponent(components[1].getId(), 5) == 6;
			assert business.assignComponent(components[2].getId(), 10) == 6;

			// Process 5
			components[3].setQuantity(20);
			components[4].setQuantity(20);
			orders[0] = business.placeOrder(components[3].getId(), suppliers[1].getId(), 10);
			assert orders[0].getComponentId() == components[3].getId();
			assert orders[0].getSupplierId() == suppliers[1].getId();

			// Process 6
			assert business.fulfillOrder(orders[0].getOrderId()) == 6;

			System.out.println("All automated tests passed.");
		} catch (Exception e) {
			System.out.println("One or more automated tests failed.");
			e.printStackTrace();
		}

	}

}
