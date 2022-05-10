package binarysearchtree;


public class TreeNode {
	
	private int ID;
	private int height;
	private String name = null;
	private TreeNode leftNode = null;
	private TreeNode rightNode = null;
	private TreeNode parentNode = null;

	public TreeNode(int ID, String name) {
		this.ID = ID;
		this.name = name;
		this.height = 1;
	}
	
	public int getID() {
		return this.ID;
	}
	
	public String getName() {
		return this.name;
	}
	
	public TreeNode getLeft() {
		return this.leftNode;
	}
	
	public TreeNode getRight() {
		return this.rightNode;
	}
	
	public TreeNode getParent() {
		return this.parentNode;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public void setID(int ID) {
		this.ID = ID;
	}
	
	public void setLeft(TreeNode node) {
		if(node != null) node.setParent(this);
		this.leftNode = node;
	}
	
	public void setRight(TreeNode node) {
		if(node != null) node.setParent(this);
		this.rightNode = node;
	}
	
	public void setParent(TreeNode node) {
		this.parentNode = node;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public void copyNode(TreeNode node) {
		this.ID = node.getID();
		this.name = node.getName();
	}
}
