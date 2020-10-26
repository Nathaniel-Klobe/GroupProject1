import java.util.ArrayList;
import java.util.Iterator;

public class SupplierList {

	private ArrayList<Supplier> suppliers;

	public SupplierList() {
		this.suppliers = new ArrayList<Supplier>();
	}

	public boolean addSupplier(Supplier supplier) {
		if (supplier != null && this.suppliers.contains(supplier) != true) {
			this.suppliers.add(supplier);
			return true;
		}

		return false;
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

}
