package hashtable;

public class ChainTable extends HashTable{
	
	public ChainTable(int size) {
		super(size);
	}
	
	public void insert(String IDCardNum, String name, TableElement[] table) {
		
		int pos = this.hash(IDCardNum);
		if(this.search(IDCardNum, table, pos) != null) return;
		
		if(table[pos] == null) table[pos] = new TableElement(name, IDCardNum);
		else {
			TableElement current = table[pos];
			while(current.getNext() != null) current = current.getNext();
			current.setNext(new TableElement(name, IDCardNum));
		}
		if(table != this.hashTable) return; 
		this.entries++;
		if((this.entries / this.tableSize) >= 0.5) this.resizeTable();
	}
	
	public void delete(String IDCardNum) {
		
		int pos = this.hash(IDCardNum);
		TableElement current = this.hashTable[pos], parent = null;
		
		while(current != null && !current.getID().equals(IDCardNum)) {
			parent = current;
			current = current.getNext();
		}
		if(current == null) return;
		
		if(this.hashTable[pos] == current) {
			if(current.getNext() == null) this.hashTable[pos] = null;
			else {
				this.hashTable[pos] = current.getNext();
				current.setNext(null);
			}
		}
		else if(parent != null && current.getNext() != null) {
			parent.setNext(current.getNext());
			current.setNext(null);
		}
		else parent.setNext(null);
		this.entries--;
	}
	
	public TableElement search(String IDCardNum, TableElement[] table, int p) {
		
		int pos = (p == -1 ? this.hash(IDCardNum) : p);
		TableElement current = table[pos];
		
		while(current != null && !current.getID().equals(IDCardNum)) current = current.getNext();
		return current;
	}
	
	public void resizeTable() {
		this.tableSize *= 2;
		TableElement[] newTable = new TableElement[this.tableSize];

		for(int i = 0; i < this.hashTable.length; i++) {
			if(this.hashTable[i] != null) {
				while(this.hashTable[i] != null) {
					String ID = this.hashTable[i].getID();
					String name = this.hashTable[i].getName();
					this.insert(ID, name, newTable);
					this.hashTable[i] = this.hashTable[i].getNext();
				}
			}
		}
		this.hashTable = newTable;
	}
}
