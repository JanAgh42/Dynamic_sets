package binarysearchtree;

abstract public class BinarySearchTree {
	
	public TreeNode mainNode;
	
	public BinarySearchTree(TreeNode node) {
		this.mainNode = node;
	}
	
	abstract public void insert(int ID, String name);
	abstract public void delete(int ID, TreeNode node);
	abstract public TreeNode search(int ID, TreeNode node);
	abstract public void rightRotate(TreeNode node);
	abstract public void leftRotate(TreeNode node);
	
	public TreeNode insertNewNode(int ID, String name, TreeNode node) {
		while(node != null) {
			if(ID > node.getID()) {
				if(node.getRight() == null) {
					node.setRight(new TreeNode(ID, name));
					return node.getRight();
				}
				node = node.getRight(); continue;
			}
			else if(ID < node.getID()) {
				if(node.getLeft() == null) {
					node.setLeft(new TreeNode(ID, name));
					return node.getLeft();			
				}
				node = node.getLeft(); continue;
			}
			node = null;
		}
		return node;
	}
	
	public TreeNode searchMax(TreeNode node) {
		while(node.getRight() != null) node = node.getRight();
		return node;
	}
	
	public TreeNode getMainNode() {
		return this.mainNode;
	}
}
