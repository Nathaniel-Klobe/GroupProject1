import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class SupplierList implements Serializable {

	private static final long serialVersionUID = 1L;
	private ArrayList<Supplier> suppliers;
	private static SupplierList instance = null;

	private SupplierList() {
		this.suppliers = new ArrayList<Supplier>();
	}

	public static SupplierList instance() {
		if (instance == null) {
			return (instance = new SupplierList());
		} else {
			return instance;
		}
	}

	public boolean addSupplier(Supplier supplier) {
		return this.suppliers.add(supplier);
	}

	public Supplier search(int id) {
		for (Iterator<Supplier> iter = this.suppliers.iterator(); iter.hasNext();) {
			Supplier supplier = iter.next();
			if (supplier.getId() == id) {
				return supplier;
			}
		}
		return null;
	}

	public boolean removeSupplier(int id) {
		Supplier supplier = this.search(id);

		if (supplier == null) {
			return false;
		} else {
			return this.suppliers.remove(supplier);
		}
	}

	public Iterator<Supplier> getSuppliers() {
		return this.suppliers.iterator();
	}

}
