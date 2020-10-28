/*
 * Group Project 1
 * Nate Klobe
 * Christopher Pellegrino
 * Jonathan Bracamontes
 * Anou Keohavong
 * 
 * User Interface
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.StringTokenizer;

public class UserInterface {
	private static UserInterface userInterface;
	private BufferedReader reader = new BufferedReader(new InputStream(System.in));
	private static Business business;
	private static final int EXIT = 0;
	private static final int ADD_COMPONENT = 1;
	private static final int ADD_SUPPLIER = 2;
	private static final int ADD_COMPONENT_SUPPLIER = 3;
	private static final int ASSIGN_COMPONENT = 4;
	private static final int PLACE_ORDER = 5;
	private static final int FULFILL_ORDER = 6;
	private static final int LIST_COMPONENT = 7;
	private static final int LIST_SUPPLIER = 8;
	private static final int DISPLAY_OUTSTANDING_ORDERS = 9;
	private static final int DISPLAY_COMPONENTS = 10;
	private static final int DISPLAY_SUPPLIERS = 11;
	private static final int SAVE = 12;
	private static final int HELP = 13;
	
	/*
	 * Private for singleton pattern. Conditionally looks for any saved data.
	 * Otherwise, it gets a singleton Business object.
	 */
	private UserInterface() {
		if(yesOrNo("Look for saved data and use it?") ) {
			retrieve();
		} else {
			business = Business.instance();
			if(yesOrNo("Do you want to generate a test bed an invooked the functionality using asserts?")) {
				new AutomatedTester(business);
			}
		}
	}
	
	/*
	 * Supports the singleton pattern
	 * 
	 * @return the singleton object
	 */
	public static UserInterface instance() {
		if(userInterface == null) {
			retrun userInterface = new UserInterface();
		} else {
			return userInterface;
		}
	}
	/**
	 * Gets a token after prompting
	 * 
	 * @param prompt - whatever the user wants as prompt
	 * @return - the token from the keyboard
	 * 
	 */
	public String getToken(String prompt) {
		do {
			try {
				System.out.println(prompt);
				String line = reader.readLine();
				StringTokenizer tokenizer = new StringTokenizer(line, "\n\r\f");
				if (tokenizer.hasMoreTokens()) {
					return tokenizer.nextToken();
				}
			} catch (IOException ioe) {
				System.exit(0);
			}
		} while (true);
	}
	
	/**
	 * Queries for a yes or no and returns true for yes and false for no
	 * 
	 * @param prompt The string to be prepended to the yes/no prompt
	 * @return true for yes and false for no
	 * 
	 */
	private boolean yesOrNo(String prompt) {
		String more = getToken(prompt + " (Y|y)[es] or anything else for no");
		if (more.charAt(0) != 'y' && more.charAt(0) != 'Y') {
			return false;
		}
		return true;
	}
	
	/**
	 * Converts the string to a number
	 * 
	 * @param prompt the string for prompting
	 * @return the integer corresponding to the string
	 * 
	 */
	public int getNumber(String prompt) {
		do {
			try {
				String item = getToken(prompt);
				Integer number = Integer.valueOf(item);
				return number.intValue();
			} catch (NumberFormatException nfe) {
				System.out.println("Please input a number ");
			}
		} while (true);
	}
	
	/**
	 * Prompts for a date and gets a date object
	 * 
	 * @param prompt the prompt
	 * @return the data as a Calendar object
	 */
	public Calendar getDate(String prompt) {
		do {
			try {
				Calendar date = new GregorianCalendar();
				String item = getToken(prompt);
				DateFormat dateFormat = SimpleDateFormat.getDateInstance(DateFormat.SHORT);
				date.setTime(dateFormat.parse(item));
				return date;
			} catch (Exception fe) {
				System.out.println("Please input a date as mm/dd/yy");
			}
		} while (true);
	}
	
	/**
	 * Prompts for a command from the keyboard
	 * 
	 * @return a valid command
	 * 
	 */
	public int getCommand() {
		do {
			try {
				int value = Integer.parseInt(getToken("Enter command:" + HELP + " for help"));
				if (value >= EXIT && value <= HELP) {
					return value;
				}
			} catch (NumberFormatException nfe) {
				System.out.println("Enter a number");
			}
		} while (true);
	}
	
	/**
	 * Displays the help screen
	 */
	public void help() {
		System.out.println("Enter a number between 0 and 12 as explained below:");
		System.out.println(EXIT + " to Exit\n");
		System.out.println(ADD_COMPONENT + " to add components");
		System.out.println(ADD_SUPPLIER + " to add supplier");
		System.out.println(ADD_COMPONENT_SUPPLIER + " to add component supplier");
		System.out.println(ASSIGN_COMPONENT + " to assign component to product");
		System.out.println(PLACE_ORDER + " to order components");
		System.out.println(FULFILL_ORDER + " to fulfill an order");
		System.out.println(LIST_COMPONENT + " to list a component with its suppliers");
		System.out.println(LIST_SUPPLIER + " to list a supplier with its components");
		System.out.println(DISPLAY_OUTSTANDING_ORDERS + " to display outstanding orders");
		System.out.println(DISPLAY_COMPONENTS + " to display all components");
		System.out.println(DISPLAY_SUPPLIERS + " to display all supplierss");
		System.out.println(SAVE + " to save data");
		System.out.println(HELP + " for help");
	}
	
	/*
	 * Method for adding components
	 */
	public void addComponent() {
		String name = getToken("Enter component name");
		Component result;
		result = Business.instance().addComponent(name);
		if(result == null) {
			System.out.println("Could not add component");
		}
		System.out.println(result);
	}
	
	/*
	 * method for adding suppliers
	 */
	
	public void addSupplier() {
		String name = getToken("Enter supplier name");
		Supplier result;
		result = Business.instance.addSupplier(name);
		if(result == null ) {
			System.out.println("Could not add supplier");
		}
		System.out.println(result);
	}
	
	/*
	 * method to add Compnent Supplier. Prompts user for Component id
	 * and supplier id.
	 */
	
	public void addComponentSupplier() {
		int componentID = getToken("Enter Compnent ID");
		int supplierID = getToken("Enter Supplier ID");
		int result = business.addComponentSupplier(componenID, supplierID);
		Switch(result){
			case Business.COMPONENT_ID_ERROR:
				System.out.println("No such component");
				break;
			case Business.SUPPLIER_ID_ERROR:
				System.out.println("No such supplier");
				break;
			case Business.OPERATION_COMPLETED:
				System.out.println("Compnent Supplier added");
				break;
			default:
				System.out.println("An error has occured");
		}
	}
	
	/*
	
	/*
	 * 
	 *
	 * ADD METHODS:
	 * 
	 * assignComponent();
	 * placeOrder();
	 * fulfillOrder();
	 * listComponent();
	 * listSupplier();
	 * displayOutstandingOrders();
	 * displayComponents();
	 * displaySuppliers();
	 * 
	 */
	
	
	
	
	/**
	 * Method to be called for saving the Business object. Uses the appropriate
	 * Business method for saving.
	 * 
	 */
	private void save() {
		if (business.save()) {
			System.out.println(" The Business has been successfully saved in the file BusinessData \n");
		} else {
			System.out.println(" There has been an error in saving \n");
		}
	}
	
	/**
	 * Method to be called for retrieving saved data. Uses the appropriate Business
	 * method for retrieval.
	 * 
	 */
	private void retrieve() {
		try {
			if (business == null) {
				Business.retrieve();
				if (business != null) {
					System.out.println(" The business has been successfully retrieved from the file BusinessData \n");
				} else {
					System.out.println("File doesnt exist; creating new library");
					business = Business.instance();
				}
			}
		} catch (Exception cnfe) {
			cnfe.printStackTrace();
		}
	}
	
	/**
	 * Orchestrates the whole process. Calls the appropriate method for the
	 * different functionalities.
	 * 
	 */
	public void process() {
		int command;
		help();
		while ((command = getCommand()) != EXIT) {
			switch (command) {
			case ADD_COMPONENT:
				addComponent();
				break;
			case ADD_SUPPLIER:
				addSupplier();
				break;
			case ADD_COMPONENT_SUPPLIER:
				addComponentSupplier();
				break;
			case ASSIGN_COMPONENT:
				assignComponent();
				break;
			case PLACE_ORDER:
				placeOrder();
				break;
			case FULFILL_ORDER:
				fulfillOrder();
				break;
			case LIST_COMPONENT:
				listComponent();
				break;
			case LIST_SUPPLIER:
				listSupplier();
				break;
			case DISPLAY_OUTSTANDING_ORDERS:
				displayOutStandingOrders();
				break;
			case DISPLAY_COMPONENTS:
				displayComponents();
				break;
			case DISPLAY_SUPPLIERS:
				displaySuppliers();
				break;
			case SAVE:
				save();
				break;
			case HELP:
				help();
				break;
			}
		}
	}
	
	/**
	 * The method to start the application. Simply calls process().
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {
		UserInterface.instance().process();
	}

}
