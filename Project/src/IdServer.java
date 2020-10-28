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

	public static IdServer instance() {
		if (server == null) {
			return (server = new IdServer());
		} else {
			return server;
		}
	}

	public int getComponentId() {
		return this.componentId++;
	}

	public int getSupplierId() {
		return this.supplierId++;
	}

	public int getOrderId() {
		return this.orderId++;
	}

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
