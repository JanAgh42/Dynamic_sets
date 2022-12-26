package binarysearchtree;
public class SplayTree extends BinarySearchTree{
	
	public SplayTree() {
		super(null);
	}
	
	public void insert(int ID, String name) {
		if(this.mainNode != null) this.splay(this.insertNewNode(ID, name, this.mainNode));
		else this.mainNode = new TreeNode(ID, name);
	}
	
	public void delete(int ID, TreeNode node) {
		
		TreeNode del = this.search(ID, node);
		
		if(del == null || del.getID() != ID) return;
		
		TreeNode left = del.getLeft();
		TreeNode right = del.getRight();
		
		if(left != null) {
			del.setLeft(null);
			left.setParent(null);
		}
		if(right != null) {
			del.setRight(null);
			right.setParent(null);
		}
		if(left == null) {
			this.mainNode = right; return;
		}
		else {
			TreeNode max = this.searchMax(left);
			this.splay(max);
			max.setRight(right);
			this.mainNode = max;
		}
	}
	
	public TreeNode search(int ID, TreeNode node) {
		if(node == null) return node;
		while(node.getID() != ID) {
			if(node.getID() > ID) {
				if(node.getLeft() == null) break;
				else node = node.getLeft();
			}
			else if(node.getID() < ID) {
				if(node.getRight() == null) break;
				else node = node.getRight();
			}
		}
		TreeNode f = this.splay(node);
		return (f.getID() == ID ? f : null);
	}
	
	public void rightRotate(TreeNode node) {
		TreeNode left = node.getLeft();
		TreeNode parent = node.getParent();
		TreeNode rightChild = left.getRight();
		
		if(parent != null && parent.getLeft() == node) parent.setLeft(left);
		else if(parent != null && parent.getRight() == node) parent.setRight(left);
		else left.setParent(parent);
		node.setLeft(rightChild);
		left.setRight(node);
		
		if(this.mainNode == node) this.mainNode = left;
	}
	
	public void leftRotate(TreeNode node) {
		TreeNode right = node.getRight();
		TreeNode parent = node.getParent();
		TreeNode leftChild = right.getLeft();
		
		if(parent != null && parent.getRight() == node) parent.setRight(right);
		else if(parent != null && parent.getLeft() == node) parent.setLeft(right);
		else right.setParent(parent);
		node.setRight(leftChild);
		right.setLeft(node);
		
		if(this.mainNode == node) this.mainNode = right;
	}
	
	public TreeNode splay(TreeNode node) {
		if(node == null) return null;
		while(this.mainNode != node && node.getParent() != null) {
			TreeNode parent = node.getParent();
			TreeNode gParent = (parent == null ? null : parent.getParent());
			
			if(gParent != null && parent != null) {
				if(gParent.getLeft() == parent) {
					if(parent.getLeft() == node) {
						this.rightRotate(gParent);
						this.rightRotate(parent);
					}
					else {
						this.leftRotate(parent);
						this.rightRotate(gParent);
					}
				}
				else {
					if(parent.getLeft() == node) {
						this.rightRotate(parent);
						this.leftRotate(gParent);
					}
					else {
						this.leftRotate(gParent);
						this.leftRotate(parent);
					}
				}
			}
			else if(parent != null) {
				if(parent.getLeft() == node) this.rightRotate(parent);
				else this.leftRotate(parent);
			}
		}
		return node;
	}
}