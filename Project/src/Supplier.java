import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class Supplier {

	private int id;
	private String name;
	private ArrayList<Integer> components;

	public Supplier(String name) {
		this.setName(name);
		this.components = new ArrayList<Integer>();
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
		if (id != 0) {
			this.id = id;
		}
	}

	public boolean addComponent(int componentId) {
		if (!this.components.contains(componentId)) {
			this.components.add(componentId);
			return true;
		}

		return false;
	}

	public boolean removeComponent(int componentId) {
		if (this.components.contains(componentId)) {
			this.components.remove(componentId);
			return true;
		}

		return false;
	}

	public Iterator<Integer> getComponents() {
		return this.components.iterator();
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
		Supplier supplier = (Supplier) obj;

		return Objects.equals(this.name, supplier.getName()) && Objects.equals(this.id, supplier.getId());
	}

	@Override
	public String toString() {
		return this.getId() + " " + this.getName();
	}
}
