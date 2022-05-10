package hashtable;

public class TableElement {
	
	private String ID;
	private String name;
	private TableElement next;
	
	public TableElement(String name, String ID) {
		this.name = name;
		this.ID = ID;
		this.next = null;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setNext(TableElement next) {
		this.next = next;
	}
	
	public TableElement getNext() {
		return this.next;
	}
	
	public String getID() {
		return this.ID;
	}
}
