
/**
 * This class creates the list of suppliers associated with Business.
 * Methods: addSupplier, search, removeSupplier, and getSuppliers.
 * 
 * @authors Jonathan Bracamontes, Anou Keohavong, Nathaniel Klobe, and Christopher Pellegrino
 */

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

	/**
	 * Creates and returns the Singleton instance of SupplierList.
	 */
	public static SupplierList instance() {
		if (instance == null) {
			return (instance = new SupplierList());
		} else {
			return instance;
		}
	}

	/**
	 * This method adds the supplier to the ArrayList.
	 * 
	 * @param supplier
	 * @return boolean from add method of ArrayList.
	 */
	public boolean addSupplier(Supplier supplier) {
		return this.suppliers.add(supplier);
	}

	/**
	 * This method takes the id int for a supplier and searches through the
	 * ArrayList.
	 * 
	 * @param id, int for supplierID.
	 * @return supplier or null
	 */
	public Supplier search(int id) {
		for (Iterator<Supplier> iter = this.suppliers.iterator(); iter.hasNext();) {
			Supplier supplier = iter.next();
			if (supplier.getId() == id) {
				return supplier;
			}
		}
		return null;
	}

	/**
	 * This method removes the supplier from the ArrayList.
	 * 
	 * @param id, to find supplier using search method.
	 * @return boolean based on remove method from ArrayList.
	 */
	public boolean removeSupplier(int id) {
		Supplier supplier = this.search(id);

		if (supplier == null) {
			return false;
		} else {
			return this.suppliers.remove(supplier);
		}
	}

	/**
	 * This method returns the Iterator of suppliers.
	 */
	public Iterator<Supplier> getSuppliers() {
		return this.suppliers.iterator();
	}

}
