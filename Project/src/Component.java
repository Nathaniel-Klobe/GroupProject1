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

	public boolean addSupplier(Supplier supplier) {
		if (!this.suppliers.contains(supplier)) {
			this.suppliers.add(supplier);
			return true;
		}

		return false;
	}

	public boolean removeSupplier(Supplier supplier) {
		if (this.suppliers.contains(supplier)) {
			this.suppliers.remove(supplier);
			return true;
		}

		return false;
	}

	public Iterator<Supplier> getSuppliers() {
		return suppliers.iterator();
	}

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

	@Override
	public String toString() {
		return "ID:" + this.getId() + " " + this.getName() + " Quantity: " + this.getQuantity() + "\n";
	}

}
