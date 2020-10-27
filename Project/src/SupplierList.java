import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class SupplierList {

	private ArrayList<Supplier> suppliers;

	public SupplierList() {
		this.suppliers = new ArrayList<Supplier>();
	}

	public void addSupplier(String name) {
		Supplier supplier = new Supplier(name, this.generateId());
		this.suppliers.add(supplier);
	}

	public Supplier getSupplier(int id) {
		Iterator<Supplier> iter = this.suppliers.iterator();

		while (iter.hasNext()) {
			if (iter.next().getId() == id) {
				return iter.next();
			}
		}

		return null;
	}

	public boolean removeSupplier(int id) {
		Iterator<Supplier> iter = this.suppliers.iterator();

		while (iter.hasNext()) {
			if (iter.next().getId() == id) {
				this.suppliers.remove(iter.next());
				return true;
			}
		}

		return false;
	}

	public boolean editSupplier(int id, Supplier supplier) {
		Iterator<Supplier> iter = this.suppliers.iterator();

		while (iter.hasNext()) {
			if (iter.next().getId() == id) {
				this.suppliers.remove(iter.next());
				this.suppliers.add(supplier);
				return true;
			}
		}

		return false;
	}

	public Iterator<Supplier> getSuppliers() {
		return this.suppliers.iterator();
	}

	private int generateId() {

		while (true) {
			boolean exists = false;
			Random rand = new Random();
			int id = rand.nextInt();

			Iterator<Supplier> iter = this.suppliers.iterator();
			while (iter.hasNext()) {
				if (iter.next().getId() == id) {
					exists = true;
				}
			}

			if (exists == false) {
				return id;
			}
		}

	}

}
