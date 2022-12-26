package hashtable;

abstract public class HashTable {
	
	public TableElement[] hashTable;
	public int tableSize = 0;
	public float entries = 0;
	
	public HashTable(int size) {
		this.tableSize = size;
		this.hashTable = new TableElement[this.tableSize];
	}

	abstract public void insert(String IDCardNum, String name, TableElement[] table);
	abstract public void delete(String IDCardNum);
	abstract public TableElement search(String IDCardNum, TableElement[] table, int p);
	abstract public void resizeTable();
	
	public int hash(String key) {
		
		long sum = 0;
		
		for(int i = 0; i < key.length(); i++) sum += ((int) key.charAt(i)) * (sum + 1) + 31 * i;
		return Math.abs((int)(23 * sum + 197) % this.tableSize);
	}
}
