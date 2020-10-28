import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class ComponentList implements Serializable {

	private static final long serialVersionUID = 1L;
	private ArrayList<Component> components;
	private static ComponentList componentList = null;

	private ComponentList() {
		this.components = new ArrayList<Component>();
	}

	public static ComponentList instance() {
		if (componentList == null) {
			return (componentList = new ComponentList());
		} else {
			return componentList;
		}
	}

	public boolean addComponent(Component component) {
		return this.components.add(component);
	}

	public Component search(int id) {
		for (Iterator<Component> iter = this.components.iterator(); iter.hasNext();) {
			Component component = (Component) iter.next();
			if (component.getId() == id) {
				return component;
			}
		}

		return null;
	}

	public boolean removeComponent(int id) {
		Component component = this.search(id);
		if (component == null) {
			return false;
		} else {
			return this.components.remove(component);

		}

	}

	public Iterator<Component> getComponents() {
		return this.components.iterator();
	}

	@Override
	public String toString() {
		return this.components.toString();
	}

}
