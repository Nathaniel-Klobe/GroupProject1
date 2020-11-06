import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class IdServer implements Serializable {
	private static final long serialVersionUID = 1L;
	private int componentId;
	private int supplierId;
	private int orderId;

	private static IdServer server = null;

	private IdServer() {
		this.componentId = 1;
		this.supplierId = 1;
		this.orderId = 1;
	}

	/**
	 * Supports the singleton pattern
	 * 
	 * @return The instance of the id server
	 */
	public static IdServer instance() {
		if (server == null) {
			return (server = new IdServer());
		} else {
			return server;
		}
	}

	/**
	 * Get the next component id.
	 * 
	 * @return The int id.
	 */
	public int getComponentId() {
		return this.componentId++;
	}

	/**
	 * Get the next supplier id.
	 * 
	 * @return the int id.
	 */
	public int getSupplierId() {
		return this.supplierId++;
	}

	/**
	 * Get the next order id.
	 * 
	 * @return the int it
	 */
	public int getOrderId() {
		return this.orderId++;
	}

	/**
	 * Retrieve a saved copy of an instance id
	 * 
	 * @param input The objectstream of the saved idServer.
	 */
	public static void retrieve(ObjectInputStream input) {
		try {
			server = (IdServer) input.readObject();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (Exception cnfe) {
			cnfe.printStackTrace();
		}
	}
}
