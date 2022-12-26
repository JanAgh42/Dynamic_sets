import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import binarysearchtree.*;
import hashtable.*;

public class Main {
	
	static BinarySearchTree tree = null;
	static HashTable table = null;
	static Scanner s = null;
	
	public static void main(String[] args) throws IOException {
		
		s = new Scanner(System.in);

		int decision;
		
		do {
			System.out.println("--------------------------------------");
			System.out.println("1 - Sequential testing");
			System.out.println("2 - Random values from file");
			System.out.println("3 - Manual testing");
			System.out.println("0 - Exit");
			System.out.print("Your choice: ");
			decision = s.nextInt();
			
			switch(decision) {
			case 1: sequential(); break;				
			case 2: fileTest(); break;
			case 3: manual(); break;
			}
		}while(decision != 0);
		s.close();
	}
	
	static void sequential() {
		System.out.print("Choose amount of data (max 10 000 000): ");
		int data = s.nextInt();
		
		tree = new AVLTree();
		sTreeTest(data, "AVL", null, null);
		tree = new SplayTree();
		sTreeTest(data, "Splay", null, null);
		table = new ChainTable(10000);
		sTableTest(data, "Chain", null, null);
		table = new OpenAddTable(10000);
		sTableTest(data, "Open Adressing", null, null);
	}
	
	static void fileTest() throws IOException {
		System.out.print("Choose amount of data (max 1 000 000): ");
		int data = s.nextInt();
		
		generateFile(data);
	}
	
	static void manual() {
		System.out.println("Choose data structure");
		System.out.print("1 - AVL tree, 2 - Splay tree, 3 - Chaining table, 4 - Open Add. table: ");
		int type = s.nextInt();
		
		switch(type) {
		case 1: manualTree("AVL"); break;
		case 2: manualTree("Splay"); break;
		case 3: manualTable("Chain"); break;
		case 4: manualTable("Open"); break;
		}
	}
	
	static void sTreeTest(int data, String type, String[] keys, String[] values) {
		System.out.println("--------------------------------------");
		System.out.println("Inserting " + data + " entries into " + type +  " tree...");
		treeInsert(data, keys, values);
		System.out.println("Searching " + data + " entries in " + type +  " tree...");
		treeSearch(data, keys, values);
		System.out.println("Deleting " + data + " entries from " + type +  " tree...");
		treeDelete(data, keys, values);
	}
	
	static void sTableTest(int data, String type, String[] keys, String[] values) {
		System.out.println("--------------------------------------");
		System.out.println("Inserting " + data + " entries into " + type +  " hash table...");
		tableInsert(data, keys, values);
		System.out.println("Searching " + data + " entries in " + type +  " hash table...");
		tableSearch(data, keys, values);
		System.out.println("Deleting " + data + " entries from " + type +  " hash table...");
		tableDelete(data, keys, values);
	}
	
	static void treeInsert(int data, String[] keys, String[] values) {
		long begin = System.nanoTime();
		if(keys == null || values == null) {
			for(int i = 0; i < data; i++) tree.insert(i + 1, "testname");
		}
		else {
			for(int i = 0; i < data; i++) tree.insert(Integer.parseInt(keys[i]), values[i]);
		}
		long end = System.nanoTime();
		System.out.println("Execution time: " + (end - begin) / 1000000 + "ms");
	}
	
	static void treeSearch(int data, String[] keys, String[] values) {
		long begin = System.nanoTime();
		if(keys == null || values == null) {
			for(int i = 0; i < data; i++) tree.search(i + 1, tree.getMainNode());
		}
		else {
			for(int i = 0; i < data; i++) tree.search(Integer.parseInt(keys[i]), tree.getMainNode());
		}
		long end = System.nanoTime();
		System.out.println("Execution time: " + (end - begin) / 1000000 + "ms");
	}
	
	static void treeDelete(int data, String[] keys, String[] values) {
		long begin = System.nanoTime();
		if(keys == null || values == null) {
			for(int i = 0; i < data; i++) tree.delete(i + 1, tree.getMainNode());
		}
		else {
			for(int i = 0; i < data; i++) tree.delete(Integer.parseInt(keys[i]), tree.getMainNode());
		}
		long end = System.nanoTime();
		System.out.println("Execution time: " + (end - begin) / 1000000 + "ms");
	}
	
	static void tableInsert(int data, String[] keys, String[] values) {
		long begin = System.nanoTime();
		if(keys == null || values == null) {
			for(int i = 0; i < data; i++) table.insert(Integer.toString(i + 1), "testname", table.hashTable);
		}
		else {
			for(int i = 0; i < data; i++) table.insert(keys[i], values[i], table.hashTable);
		}
		long end = System.nanoTime();
		System.out.println("Execution time: " + (end - begin) / 1000000 + "ms");
	}
	
	static void tableSearch(int data, String[] keys, String[] values) {
		long begin = System.nanoTime();
		if(keys == null || values == null) {
			for(int i = 0; i < data; i++) table.search(Integer.toString(i + 1), table.hashTable, -1);
		}
		else {
			for(int i = 0; i < data; i++) table.search(keys[i], table.hashTable, -1);
		}
		long end = System.nanoTime();
		System.out.println("Execution time: " + (end - begin) / 1000000 + "ms");
	}
	
	static void tableDelete(int data, String[] keys, String[] values) {
		long begin = System.nanoTime();
		if(keys == null || values == null) {
			for(int i = 0; i < data; i++) table.delete(Integer.toString(i + 1));
		}
		else {
			for(int i = 0; i < data; i++) table.delete(keys[i]);
		}
		long end = System.nanoTime();
		System.out.println("Execution time: " + (end - begin) / 1000000 + "ms");
	}
	
	static void manualTree(String t) {
		tree = (t.equals("AVL") ? new AVLTree() : new SplayTree());
		int decision;
		
		do {
			System.out.print("1 - insert, 2 - delete, 3 - search, 0 - end: ");
			decision = s.nextInt();
			
			switch(decision) {
			case 1: 
				System.out.print("Enter key (int) and value (string) to be inserted: ");
				tree.insert(s.nextInt(), s.nextLine()); break;
			case 2:
				System.out.print("Enter key (int) to be deleted: ");
				tree.delete(s.nextInt(), tree.getMainNode()); break;
			case 3:
				System.out.print("Enter key (int) to be searched: ");
				TreeNode node = tree.search(s.nextInt(), tree.getMainNode()); 
				if(node != null) System.out.println("Found value:" + node.getName());
				else  System.out.println("Value not found.");
				break;
			case 4:
			}
		} while(decision != 0);
	}
	
	static void manualTable(String t) {
		table = (t.equals("Chain") ? new ChainTable(10) : new OpenAddTable(10));
		int decision;
		
		do {
			System.out.print("1 - insert, 2 - delete, 3 - search, 0 - end: ");
			decision = s.nextInt();
			
			switch(decision) {
			case 1: 
				System.out.print("Enter key (int) and value (string) to be inserted: ");
				table.insert(Integer.toString(s.nextInt()), s.nextLine(), table.hashTable); break;
			case 2:
				System.out.print("Enter key (int) to be deleted: ");
				table.delete(Integer.toString(s.nextInt())); break;
			case 3:
				System.out.print("Enter key (int) to be searched: ");
				TableElement node = table.search(Integer.toString(s.nextInt()), table.hashTable, -1); 
				if(node != null) System.out.println("Found value:" + node.getName());
				else  System.out.println("Value not found.");
				break;
			case 4:
			}
		} while(decision != 0);
	}
	
	static void generateFile(int length) throws IOException {
		File f = new File("./src/data.txt");
		String[] keys = new String[length], values = new String[length];

			RandomAccessFile s2 = new RandomAccessFile(f, "r");
			for(int x = 0; x < length; x++) {
				long loc = (long) (Math.random() * f.length());
				s2.seek(loc);
				s2.readLine();
				String line = s2.readLine();
				if(line == null) s2.seek(0);
				keys[x] = line.substring(0, 6);
				values[x] = line.substring(7);
		}
		tree = new AVLTree();
		sTreeTest(length, "AVL", keys, values);
		tree = new SplayTree();
		sTreeTest(length, "Splay", keys, values);
		table = new ChainTable(1000);
		sTableTest(length, "Chain", keys, values);
		table = new OpenAddTable(1000);
		sTableTest(length, "Open Adressing", keys, values);
	}
}
