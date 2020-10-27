import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class ComponentList {

	private ArrayList<Component> components;

	public ComponentList() {
		this.components = new ArrayList<Component>();
	}

	public void addComponent(String name) {

		Component component = new Component(name, this.generateId());
		this.components.add(component);

	}

	public Component getComponent(int id) {
		Iterator<Component> iter = this.components.iterator();

		while (iter.hasNext()) {
			if (iter.next().getId() == id) {
				return iter.next();
			}
		}

		return null;
	}

	public boolean removeComponent(int id) {
		Iterator<Component> iter = this.components.iterator();

		while (iter.hasNext()) {
			if (iter.next().getId() == id) {
				this.components.remove(iter.next());
				return true;
			}
		}

		return false;
	}

	public Iterator<Component> getComponents() {
		return this.components.iterator();
	}

	private int generateId() {

		while (true) {
			boolean exists = false;
			Random rand = new Random();
			int id = rand.nextInt();

			Iterator<Component> iter = this.components.iterator();
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
