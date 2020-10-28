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

	public boolean addComponent(Component component) {
		if (!this.components.contains(component)) {
			this.components.add(component);
			return true;
		}

		return false;
	}

	public boolean removeComponent(Component component) {
		if (this.components.contains(component)) {
			this.components.remove(component);
			return true;
		}

		return false;
	}

	public Iterator<Component> getComponents() {
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
		return this.getId() + " " + this.getName() + "\n";
	}
}
