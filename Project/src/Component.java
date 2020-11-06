
/**
 * This class creates the Component object of the Business.
 * Methods: getId, getName, setName, setId, getQuantity, setQuantity, addSupplier, 
 * removeSupplier, getSuppliers, equals, and toString.
 * 
 * @authors Jonathan Bracamontes, Anou Keohavong, Nathaniel Klobe, and Christopher Pellegrino
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class Component implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private int quantity;
	private ArrayList<Supplier> suppliers;

	public Component(String name) {
		this.setName(name);
		this.setId(IdServer.instance().getComponentId());
		this.setQuantity(0);
		this.suppliers = new ArrayList<Supplier>();
	}

	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		if (name != null) {
			this.name = name;
		}
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * This method adds a supplier to the ArrayList if not already contained.
	 * 
	 * @param supplier
	 * @return boolean
	 */
	public boolean addSupplier(Supplier supplier) {
		if (!this.suppliers.contains(supplier)) {
			this.suppliers.add(supplier);
			return true;
		}

		return false;
	}

	/**
	 * This method removes the supplier as long as it's included in ArrayList.
	 * 
	 * @param supplier
	 * @return boolean
	 */
	public boolean removeSupplier(Supplier supplier) {
		if (this.suppliers.contains(supplier)) {
			this.suppliers.remove(supplier);
			return true;
		}

		return false;
	}

	/**
	 * This method returns the iterator for suppliers.
	 */
	public Iterator<Supplier> getSuppliers() {
		return suppliers.iterator();
	}

	/**
	 * Equals method for the component class.
	 * 
	 * @return boolean
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		Component component = (Component) obj;

		return Objects.equals(this.name, component.getName()) && Objects.equals(this.id, component.getId());
	}

	/**
	 * toString for the component class.
	 */
	@Override
	public String toString() {
		return "ID:" + this.getId() + " " + this.getName() + " Quantity: " + this.getQuantity() + "\n";
	}

}
