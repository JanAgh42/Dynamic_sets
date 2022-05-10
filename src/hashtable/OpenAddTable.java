package hashtable;

public class OpenAddTable extends HashTable{
	
	public OpenAddTable(int size) {
		super(size);
	}
	
	public void insert(String IDCardNum, String name, TableElement[] table) {
		
		int pos = this.hash(IDCardNum), quad = 1;
		
		if(this.search(IDCardNum, table, pos) != null) return;
		
		if(table[pos] == null || table[pos].getID().equals("-1")) table[pos] = new TableElement(name, IDCardNum);
		else {
			while(table[pos] != null && !table[pos].getID().equals("-1")) {
				pos += quad * quad;
				if(pos >= this.tableSize) pos = 0;
				quad++;
			}
			table[pos] = new TableElement(name, IDCardNum);
		}
		if(table != this.hashTable) return; 
		this.entries++;
		if((this.entries / this.tableSize) >= 0.5) this.resizeTable();
	}
	
	public void delete(String IDCardNum) {
		
		int pos = this.hash(IDCardNum), quad = 1;
		
		while(this.hashTable[pos] != null && !this.hashTable[pos].getID().equals(IDCardNum)) {
			pos += quad * quad;
			if(pos >= this.tableSize) pos = 0;
			quad++;
		}
		if(this.hashTable[pos] == null) return;
		this.hashTable[pos] = new TableElement("null", "-1");
		this.entries--;
	}
	
	public TableElement search(String IDCardNum, TableElement[] table, int p) {
		
		int pos = (p == -1 ? this.hash(IDCardNum) : p), quad = 1;
		
		while(table[pos] != null && !table[pos].getID().equals(IDCardNum)) {
			pos += quad * quad;
			if(pos >= this.tableSize) pos = 0;
			quad++;
		}	
		return table[pos];
	}
	
	public void resizeTable() {
		this.tableSize *= 2;
		TableElement[] newTable = new TableElement[this.tableSize];

		for(int i = 0; i < this.hashTable.length; i++) {
			if(this.hashTable[i] != null && !this.hashTable[i].getID().equals("-1")) {
				String ID = this.hashTable[i].getID();
				String name = this.hashTable[i].getName();
				this.insert(ID, name, newTable);
			}
		}
		this.hashTable = newTable;
	}
}
