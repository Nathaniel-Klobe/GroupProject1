import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class ComponentList {

	private ArrayList<Component> components;

	public ComponentList() {
		this.components = new ArrayList<Component>();
	}

	public boolean addComponent(Component component) {

		if (component != null && this.components.contains(component) != true) {
			this.components.add(component);
			return true;
		}

		return false;
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

	public boolean editComponent(int id, Component component) {
		Iterator<Component> iter = this.components.iterator();

		while (iter.hasNext()) {
			if (iter.next().getId() == id) {
				this.components.remove(iter.next());
				this.components.add(component);
				return true;
			}
		}

		return false;
	}
	
	public Iterator<Component> getComponents(){
		return this.components.iterator();
	}
	
	private int generateId() {
		
		while(true) {
			boolean exists = false;
			Random rand = new Random();
			int id = rand.nextInt();
			
			Iterator<Component> iter = this.components.iterator();
			while(iter.hasNext()) {
				if(iter.next().getId() == id) {
					exists = true;
				}
			}
			
			if(exists == false) {
				return id;
			}
		}
	
	}
}
