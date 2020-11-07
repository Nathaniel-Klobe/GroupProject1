
/**
 * This class creates and stores the ArrayList containing Components. 
 * Methods: addComponent, search, removeComponent, getComponents, and toString.
 * 
 * @authors Jonathan Bracamontes, Anou Keohavong, Nathaniel Klobe, and Christopher Pellegrino
 */

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

	/**
	 * Creates and returns the Singleton instance of ComponentList.
	 */
	public static ComponentList instance() {
		if (componentList == null) {
			return (componentList = new ComponentList());
		} else {
			return componentList;
		}
	}

	/**
	 * This method adds the component to the ArrayList.
	 * 
	 * @param component
	 * @return boolean from the add method of ArrayList.
	 */
	public boolean addComponent(Component component) {
		return this.components.add(component);
	}

	/**
	 * This method takes the id int for a component and searches through the
	 * ArrayList.
	 * 
	 * @param id, int for componentID.
	 * @return component or null
	 */
	public Component search(int id) {
		for (Iterator<Component> iter = this.components.iterator(); iter.hasNext();) {
			Component component = (Component) iter.next();
			if (component.getId() == id) {
				return component;
			}
		}

		return null;
	}

	/**
	 * This method removes the component from the ArrayList.
	 * 
	 * @param id, to find component using search method.
	 * @return boolean based on remove method from ArrayList.
	 */
	public boolean removeComponent(int id) {
		Component component = this.search(id);
		if (component == null) {
			return false;
		} else {
			return this.components.remove(component);

		}

	}

	/**
	 * This method returns the Iterator of components.
	 */
	public Iterator<Component> getComponents() {
		return this.components.iterator();
	}

	/**
	 * toString method for ComponentList class.
	 */
	@Override
	public String toString() {
		return this.components.toString();
	}

}
