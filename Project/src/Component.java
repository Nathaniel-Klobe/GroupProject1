import java.util.Objects;

public class Component {

	private int id;
	private String name;
	private int quantity; // maybe put this somewhere else

	public Component(String name) {
		this.setName(name);
		this.setId(this.generateId());
		this.quantity = 0;
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

	private int generateId() {
		int id = 0;
		// TODO: Generate the id
		return id;
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
		return this.getId() + " " + this.getName();
	}
}
