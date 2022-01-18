// imports
import java.util.ArrayList;

/**
 * This class creates a bookshelf to hold books and orders them like a Binary Search Tree
 */
public class BinaryBookshelf {
  // fields
  private TreeNode<Book> root; // root node of BST
  private int size; // current number of nodes in BST
  private Attribute[] sortList; // ordered array of attributes that will sort BST nodes
  
  // constructors
  /**
   * Single arg constructor to create new BinaryBookshelf
   *     Initializes root, size, and sortList
   * 
   * @param sortList an array of Attributes that will determine how books will be sorted
   */
  public BinaryBookshelf(Attribute[] sortList) {
    // validates sortList
    sortListValidity(sortList);
    this.root = null;
    this.size = 0;
    this.sortList = sortList;
  }
  
  // mutators
  /**
   * Clears the BinaryBookshelf
   */
  public void clear() {
    this.root = null;
    this.size = 0;
  }
  
  /**
   * Inserts a book into the BinaryBookshelf
   * 
   * @param book a Book object to be added
   */
  public void insertBook(Book book) {
    // if there is no root, adds book as root, else calls insetBookHelper
    if(this.root == null) {
      root = new TreeNode<Book>(book);
      this.size += 1;
    } else {
      insertBookHelper(book, root);
    }
  }
  
  /**
   * Helper method for inserting a book
   * 
   * @param book     a Book object to be added
   * @param current  TreeNode<Book> indicating the current node
   */
  protected void insertBookHelper(Book book, TreeNode<Book> current) {   
    // creates new TreeNode<Book> with the book
    TreeNode<Book> newBook = new TreeNode<Book>(book);
    
    // checks left if this book is smaller than current's book
    // else if this book is bigger than current, checks right
    if(compareToHelper(book, current.getData()) < 0) {
      // if current does has a left child, recursive call with left child as current
      // else sets this book as left child
      if(current.getLeft() != null) {
        insertBookHelper(book, current.getLeft());
      } else {
        current.setLeft(newBook);
        this.size += 1;
      }
    } else if(compareToHelper(book, current.getData()) > 0) {
      // if current does has a right child, recursive call with right child as current
      // else sets this book as right child
      if(current.getRight() != null) {
        insertBookHelper(book, current.getRight());
      } else {
        current.setRight(newBook);
        this.size += 1;
      }
    }
  }
  
  // accessors
  /**
   * Recursively checks for a Book to see if this BinaryBookshelf contains it
   * 
   * Complexity = O(logN)
   * 
   * @param book a Book object that is trying to be found
   * @return true if found, false otherwise
   */
  public boolean contains(Book book) {
    return containsHelper(book, getRoot());
  }
  
  /**
   * Helper method that does the searching and finding of the Book object
   * 
   * @param book     a Book object that is trying to be found
   * @param current  TreeNode<Book> indicating the current node
   * @return true if found, false otherwise
   */
  protected boolean containsHelper(Book book, TreeNode<Book> current) {
    // if current has no Book, it can't be the book
    if(current == null) 
      return false;
    
    // checks to see if this book is the right book
    // else compares this book to the book trying to be found
    // goes left or right depending on the value returned
    if(book.equals(current.getData()))
      return true;
    else if(compareToHelper(book, current.getData()) < 0)
      return containsHelper(book, current.getLeft());
    else
      return containsHelper(book, current.getRight());
  }

  /**
   * Returns the order in which books will be compared and placed
   * 
   * @return returnString a formatted String representing the order of the Attributes
   */
  public String getAttributeOrder() {
    String returnString = "";
    
    // iterates throught sortList and formats returnString
    for(int i = 0; i < sortList.length; ++i) {
      returnString = returnString + (i + 1) + ":" + sortList[i];
      
      if(i != 3)
        returnString = returnString + " ";
    }
    
    return returnString;
  }
  
  /**
   * Creates an ArrayList containing all books in the bookshelf that have the same author
   * 
   * @param authorName a String representing which author's books are being looked for
   * 
   * @return authorBooks an ArrayList containing all books by the same author
   */
  public ArrayList<Book> getBooksByAuthor(String authorName) {
    // returns an empty ArrayList if the bookshelf is empty
    if(isEmpty())
      return new ArrayList<Book>();
    
    return getBooksByAuthorHelper(authorName, root);
  }
  
  /**
   * Helper method that searches for the author's books and returns an ArrayList
   * 
   * @param authorName  String representing the name of the author's books being search
   * @param current     TreeNode<Book> representing the current book
   * @return            authorBooks an ArrayList with all books by the author
   */
  protected ArrayList<Book> getBooksByAuthorHelper(String authorName, TreeNode<Book> current) {
    // ArrayList that will hold all the author's books
    ArrayList<Book> authorBooks = new ArrayList<Book>();

    // this node's book's author is the same one as the one being searched, adds it to authorBooks
    if(current.getData().getAuthor().equals(authorName)) {
      authorBooks.add(current.getData());
    }
    
    // searches left if this node has a left child and adds all books that have the author
    if(current.getLeft() != null) {
      authorBooks.addAll(getBooksByAuthorHelper(authorName, current.getLeft()));
    }
    
    // searches right if this node has a right child and adds all books that have the author
    if(current.getRight() != null) {
      authorBooks.addAll(getBooksByAuthorHelper(authorName, current.getRight()));
    }
     
    return authorBooks;
  }
  
  /**
   * Gets the root of this bookshelf
   * 
   * @return this.root the root of this bookshelf
   */
  protected TreeNode<Book> getRoot() {
    return this.root;
  }
 
  /**
   * Checks whether this bookshelf is empty or not
   * 
   * Complexity = O(1)
   * 
   * @return size == 0 && root == null, true if bookshelf is empty, false otherwise
   */
  public boolean isEmpty() {
    return size == 0 && root == null;
  }
  
  /**
   * Returns how many books are in the bookshelf
   * 
   * Complexity = O(1)
   * 
   * @return this.size an int representing how many books are in the bookshelf
   */
  public int size() {
    return this.size;
  }
  
  /**
   * Helper method that helps compare to books
   * 
   * @param one a Book (the one trying to be added) 
   * @param two a Book (the current node's book)
   * @throws    IllegalArgumentException if the books are the same
   * @return    val an int representing how the books compare to each other
   */
  public int compareToHelper(Book one, Book two) throws IllegalArgumentException {
    int val = 0;
    
    // compares book's author, if they are the same, goes through sortList and compares the books
    // if all Attributes in sortList are the same, then the books are the same
    if(one.getAuthor().equals(two.getAuthor())) {
      if(one.compareTo(two, sortList[1]) == 0) {
        if(one.compareTo(two, sortList[2]) == 0) {
          if(one.compareTo(two, sortList[3]) == 0) {
            throw new IllegalArgumentException("Cannot insert the same book multiple times");
          }
        } else {
          val = one.compareTo(two, sortList[2]);
        }
      } else {
        val = one.compareTo(two, sortList[1]);
      }
    } else {
      val = one.compareTo(two, Attribute.AUTHOR);
    }
    
    return val;
  }
  
  /**
   * Creates a string representation of the bookshelf
   * 
   * Complexity = O(N)
   * 
   * @return a String representing the bookshelf
   */
  public String toString() {
    // if the bookshelf is empty, returns an empty String
    if(isEmpty())
      return "";
    
    return toStringHelper(root);
  }
  
  /**
   * Helper method to build and return the String representation of the bookshelf
   * 
   * @param current TreeNode<Book> representing the current book
   * @return returnString a String representing the bookshelf
   */
  protected String toStringHelper(TreeNode<Book> current) {
    String returnString = "";

    // if the current node has no book, returns current returnString
    if(current.getData() == null) 
      return returnString;
    
    // left tree recursive searching
    if(current.getLeft() != null) {
      returnString = returnString + toStringHelper(current.getLeft());
    } 
    
    // current node's data added to returnString
    returnString = returnString + "\n" + current.getData();
    
    // right tree recursive searching
    if(current.getRight() != null) {
      returnString = returnString + toStringHelper(current.getRight());
    } 
    
    return returnString;
  }
  
  /**
   * Checks the validity of the sortList
   * 
   * @param sortList an array of Attributes that determines how the books will be ordered
   * @throws IllegalArgumentException if the sortList is invalid
   */
  private void sortListValidity(Attribute[] sortList) {
    // if the sortList does not have a length of 4, it is invalid
    if(sortList.length != 4)
      throw new IllegalArgumentException("sortList must have 4 elements");
    
    // if any of the elements in the sortList are null, it is invalid
    for(int i = 0; i < sortList.length; ++i) {
      if(sortList[i] == null)
        throw new IllegalArgumentException("sortList is missing elements");
    }
    
    // if the first element isn't Attribute.AUTHOR, then it is invalid
    if(!sortList[0].equals(Attribute.AUTHOR))
      throw new IllegalArgumentException("First element in sortList must be AUTHOR");
    
    // counters to count how many times an element shows up in sortList
    int aCount = 0; // Attribute.AUTHOR counter
    int tCount = 0; // Attribute.TITLE counter
    int pCount = 0; // Attribute.PAGECOUNT counter
    int iCount = 0; // Attribute.ID counter
    
    // iterates through sortList to see if it has duplicate elements
    for(int i = 0; i < sortList.length; ++i) { 
      if(sortList[i].equals(Attribute.AUTHOR))
        aCount++;
      if(sortList[i].equals(Attribute.TITLE))
        tCount++;
      if(sortList[i].equals(Attribute.PAGECOUNT))
        pCount++;
      if(sortList[i].equals(Attribute.ID))
        iCount++;
    }
    
    // if any of the element are not equal to one, sortList is invalid
    if(aCount != 1 || tCount != 1 || pCount != 1 || iCount != 1) {
      throw new IllegalArgumentException("Two or more elements are not unique");
    }
  }
}

