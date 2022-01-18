/**
 * This class creates nodes to contain data and used in a Binary Search Tree
 */
public class TreeNode <T> {
    // fields
    private T data; // data contained in node
    private TreeNode<T> left; // left child
    private TreeNode<T> right; // right child
    
    // constructors
    /**
     * Single argument constructor to create new TreeNode<T>. Initializes data, left, and right
     * 
     * @param data T the data to be put into the node
     */
    public TreeNode(T data) {
      this.data = data;
      left = null;
      right = null;
    }
    
    /**
     * Three argument constructor to create new TreeNode<T> with left and right children
     * 
     * @param data  T data to be put into the node
     * @param left  TreeNode<T> the left child of the new TreeNode
     * @param right TreeNode<T> the right child of the new TreeNode
     */
    public TreeNode(T data, TreeNode<T> left, TreeNode<T> right) {
      this.data = data;
      this.left = left;
      this.right = right;
    }
    
    // mutators
    /**
     * Sets the left child of this TreeNode
     * 
     * @param left TreeNode<T> the new left child of this TreeNode
     */
    public void setLeft(TreeNode<T> left) {
      this.left = left;
    }
    
    /**
     * Sets the right child of this TreeNode
     * 
     * @param right TreeNode<T> the new right child of this TreeNode
     */
    public void setRight(TreeNode<T> right) {
      this.right = right;
    }
    
    // accessors
    /**
     * Gets the data currently within this TreeNode
     * 
     * @return this.data the data within this TreeNode
     */
    public T getData() {
      return this.data;
    }
    
    /**
     * Gets the left child of this TreeNode
     * 
     * @return this.left the left child TreeNode
     */
    public TreeNode<T> getLeft() {
      return this.left;
    }
    
    /**
     * Gets the right child of this TreeNode
     * 
     * @return this.right the right child of this TreeNode
     */
    public TreeNode<T> getRight() {
      return this.right;
    }
    
    /**
     * Returns String representation of this TreeNode's data
     * 
     * @return this.data.toString() String representation of this TreeNode's data
     */
    public String toString() {
      return this.data.toString();
    }
  }
  