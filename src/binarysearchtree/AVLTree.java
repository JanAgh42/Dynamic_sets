package binarysearchtree;
public class AVLTree extends BinarySearchTree{
	
	public AVLTree() {
		super(null);
	}
	
	public void insert(int ID, String name) {
		if(this.mainNode != null) this.applyBalance(this.insertNewNode(ID, name, this.mainNode), ID, false);
		else this.mainNode = new TreeNode(ID, name);
	}
	
	public void delete(int ID, TreeNode node) {
		if(this.mainNode != null) this.applyBalance(this.deleteNode(ID, node), ID, true);
	}
	
	public TreeNode deleteNode(int ID, TreeNode node) {
		
		TreeNode delete = this.search(ID, node == null ? this.mainNode : node);
		
		if(delete == null) return null;
		
			TreeNode parent = delete.getParent();
			delete.setParent(null);
			
			if(delete.getLeft() == null && delete.getRight() == null) {			// no children
				if(parent != null) this.bypassNode(parent, delete, null);
				else this.mainNode = null;
			}
			else if(delete.getLeft() == null) {					// right child, no left child
				if(parent != null) this.bypassNode(parent, delete, delete.getRight());
				else {
					this.mainNode = delete.getRight();
					this.mainNode.setParent(null);
				}
				delete.setRight(null);
			}
			else if(delete.getRight() == null) {				// left child, no right child
				if(parent != null) this.bypassNode(parent, delete, delete.getLeft());
				else {
					this.mainNode = delete.getLeft();
					this.mainNode.setParent(null);
				}
				delete.setLeft(null);
			}
			else {																// 2 children
				TreeNode max = this.searchMax(delete.getLeft());
				delete.copyNode(max);
				this.deleteNode(max.getID(), delete.getLeft());
				delete.setParent(parent);
			}
		return parent;
	}
	
	public TreeNode search(int ID, TreeNode node) {
		while(node != null && node.getID() != ID) {
			if(node.getID() > ID) node = node.getLeft();
			else if(node.getID() < ID) node = node.getRight();
		}
		return node;
	}
	
	private void bypassNode(TreeNode parent, TreeNode delete, TreeNode child) {
		if(parent.getLeft() == delete) parent.setLeft(child);
		else if(parent.getRight() == delete) parent.setRight(child);
	}
	
	private void applyBalance(TreeNode node, int ID, boolean del) {
		if(node == null) return;
		while(node != null) {
			node.setHeight(this.getMaxHeight(node));
			
			int bal = this.nodeBalance(node);
			
			if(bal > 1 && (del ? this.nodeBalance(node.getLeft()) >= 0 : node.getLeft().getID() > ID)) this.rightRotate(node);
			else if(bal < -1 && (del ? this.nodeBalance(node.getRight()) <= 0 : node.getRight().getID() < ID)) this.leftRotate(node);
			else if(bal > 1 && (del ? this.nodeBalance(node.getLeft()) < 0 : node.getLeft().getID() < ID)) {
				this.leftRotate(node.getLeft());
				this.rightRotate(node);
			}
			else if(bal < -1 && (del ? this.nodeBalance(node.getRight()) > 0 : node.getRight().getID() > ID)) {
				this.rightRotate(node.getRight());
				this.leftRotate(node);
			}
			node = node.getParent();
		}
	}
	
	private int nodeHeight(TreeNode node) {
		if(node != null) return node.getHeight();
		return 0;
	}
	
	private int getMaxHeight(TreeNode node) {
		int leftHeight = this.nodeHeight(node.getLeft());
		int rightHeight = this.nodeHeight(node.getRight());
		return ((leftHeight > rightHeight ? leftHeight : rightHeight) + 1);
	}
	
	private int nodeBalance(TreeNode node) {
		if(node != null) return this.nodeHeight(node.getLeft()) - this.nodeHeight(node.getRight());
		return 0;
	}
	
	public void rightRotate(TreeNode node) {
		TreeNode left = node.getLeft();
		TreeNode par = node.getParent();
		TreeNode rChild = left.getRight();
		
		if(par != null && par.getLeft() == node) 
			par.setLeft(left);
		else if(par != null && par.getRight() == node) 
			par.setRight(left);
		else left.setParent(par);
		node.setLeft(rChild);
		left.setRight(node);
		
		if(this.mainNode == node) this.mainNode = left;
		
		node.setHeight(this.getMaxHeight(node));
		left.setHeight(this.getMaxHeight(left));
	}
	
	public void leftRotate(TreeNode node) {
		TreeNode right = node.getRight();
		TreeNode par = node.getParent();
		TreeNode lChild = right.getLeft();
		
		if(par != null && par.getRight() == node) 
			par.setRight(right);
		else if(par != null && par.getLeft() == node) 
			par.setLeft(right);
		else right.setParent(par);
		node.setRight(lChild);
		right.setLeft(node);
		
		if(this.mainNode == node) this.mainNode = right;
		
		node.setHeight(this.getMaxHeight(node));
		right.setHeight(this.getMaxHeight(right));
	}
}
