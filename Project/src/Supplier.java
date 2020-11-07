
/**
 * This class creates the supplier object of the Business.
 * Methods: getId, getName, setId, addComponent, removeComponent, getComponents, 
 * equals, and toString.
 * 
 * @authors Jonathan Bracamontes, Anou Keohavong, Nathaniel Klobe, and Christopher Pellegrino
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class Supplier implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private ArrayList<Component> components;

	public Supplier(String name) {
		this.setName(name);
		this.components = new ArrayList<Component>();
		this.setId(IdServer.instance().getSupplierId());
	}

	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * This method adds a component to the ArrayList if not already contained.
	 * 
	 * @param component
	 * @return boolean
	 */
	public boolean addComponent(Component component) {
		if (!this.components.contains(component)) {
			this.components.add(component);
			return true;
		}

		return false;
	}

	/**
	 * This method removes the component as log as it's included in ArrayList.
	 * 
	 * @param component
	 * @return boolean
	 */
	public boolean removeComponent(Component component) {
		if (this.components.contains(component)) {
			this.components.remove(component);
			return true;
		}

		return false;
	}

	/**
	 * This method returns an iterator of components from ArrayList.
	 */
	public Iterator<Component> getComponents() {
		return this.components.iterator();
	}

	/**
	 * equals method for supplier class.
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
		Supplier supplier = (Supplier) obj;

		return Objects.equals(this.name, supplier.getName()) && Objects.equals(this.id, supplier.getId());
	}

	/**
	 * toString method for supplier class.
	 */
	@Override
	public String toString() {
		return "ID:" + this.getId() + " " + this.getName() + "\n";
	}
}
