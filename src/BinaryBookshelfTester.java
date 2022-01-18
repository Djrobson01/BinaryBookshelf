// imports
import java.util.ArrayList;

/**
 * This class tests BinaryBookshelf.java and TreeNode.java to ensure they works properly
 */
public class BinaryBookshelfTester {

  /**
   * Main method that runs all tests
   */
  public static void main(String[] args) {
    System.out.println(testTreeNode());
    System.out.println(testEmptyTree());
    System.out.println(testInsertBook());
    System.out.println(testContains());
    System.out.println(testGetBooksByAuthor());
  }
  
  /**
   * Tests TreeNode to ensure it works properly. Tests multiple scenarios
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean testTreeNode() {
    // scenario 1: a single TreeNode with no children
    TreeNode<Integer> tn1 = new TreeNode<Integer>(5);

    try {
      // ensure accessors for children return null
      if(tn1.getLeft() != null || tn1.getRight() != null) {
        System.out.println("ERROR: tn1 SHOULD HAVE NULL CHILDREN");
        return false;
      }
      
      // ensures correct data is within the node
      if(tn1.getData() != 5) {
        System.out.println("ERROR: tn1 SHOULD RETURN THE VALUE 5");
        return false;
      }
    } catch(Exception e) {
        System.out.println("An unexpected error has occured");
        return false;
    }
    
    // scenario 2: a simple collection of TreeNodes
    TreeNode<Integer> tn2 = new TreeNode<Integer>(2);
    TreeNode<Integer> tn3 = new TreeNode<Integer>(3);
    TreeNode<Integer> tn4 = new TreeNode<Integer>(4);
    TreeNode<Integer> tn5 = new TreeNode<Integer>(5);
    
    // should create a tree with 4 as the root, 3 and 2 on the left and 5 on the right
    tn4.setLeft(tn3);
    tn4.setRight(tn5);
    tn3.setLeft(tn2);
    
    // verify child accessor methods return proper values
    try {
      // ensures tn3 is the left child of tn4
      if(!tn4.getLeft().equals(tn3)) {
        System.out.println("ERROR: tn4's LEFT CHILD SHOULD BE tn3");
        return false;
      }
      
      // ensures tn5 is the right child of tn4
      if(!tn4.getRight().equals(tn5)) {
        System.out.println("ERROR: tn4's LEFT CHILD SHOULD BE tn3");
        return false;
      }
      
      // ensures tn2 is the left child of tn3
      if(!tn3.getLeft().equals(tn2)) {
        System.out.println("ERROR: tn3's LEFT CHILD SHOULD BE tn2");
        return false;
      }
      
      // ensures tn5 has no children
      if(tn5.getLeft() != null || tn5.getRight() != null) {
        System.out.println("ERROR: tn5 SHOULD HAVE NULL CHILDREN");
        return false;
      }
    } catch(Exception e) {
      System.out.println("An unexpected error has occured");
      return false;
    }
    
    // sets children of tn3 and tn4 to null
    tn4.setLeft(null);
    tn4.setRight(null);
    tn3.setLeft(null);
    
    // verifies setting left and right to null works properly
    try {
      // ensures tn4 no longer has children
      if(tn4.getLeft() != null || tn4.getRight() != null) {
        System.out.println("ERROR: tn4 SHOULD HAVE NULL CHILDREN");
        return false;
      }
      
      // ensures tn3 no longer has children
      if(tn3.getLeft() != null || tn3.getRight() != null) {
        System.out.println("ERROR: tn3 SHOULD HAVE NULL CHILDREN");
      }
    } catch(Exception e) {
      System.out.println("An unexpected error has occured");
      return false;
    }
    
    // scenario 3: create two TreeNodes with different data to represent the leaves
    // leaf nodes
    TreeNode<Integer> tn6 = new TreeNode<Integer>(6);
    TreeNode<Integer> tn8 = new TreeNode<Integer>(8);
    
    // three arg node as parent
    TreeNode<Integer> tn7 = new TreeNode<Integer>(7, tn6, tn8);
    
    // verifies tn7 is the root with tn6 as left child and tn8 as the right child
    // verifies tn6 and tn8 have no children
    try {
      // ensures tn6 is the left child of tn7
      if(!tn7.getLeft().equals(tn6)) {
        System.out.println("ERROR: tn7's LEFT CHILD SHOULD BE tn6");
        return false;
      }
      
      // ensures tn8 is the right child of tn7
      if(!tn7.getRight().equals(tn8)) {
        System.out.println("ERROR: tn7's RIGHT CHILD SHOULD BE tn8");
        return false;
      }
      
      // verifies tn6 has no children
      if(tn6.getLeft() != null || tn6.getRight() != null) {
        System.out.println("ERROR: tn6 SHOULD HAVE NULL CHILDREN");
        return false;
      }
      
      // verifies tn8 has no children
      if(tn8.getLeft() != null || tn8.getRight() != null) {
        System.out.println("ERROR: tn8 SHOULD HAVE NULL CHILDREN");
        return false;
      }
    } catch(Exception e) {
      System.out.println("An unexpected error has occured");
      return false;
    }
        
    return true;
  }
  
  /**
   * Tests emptyTree method in BinaryBookshelf.java to ensure it works properly
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean testEmptyTree() {
    // scenario 1: invalid constructor inputs
    // empty array
    Attribute[] a1 = {};
    
    // ensures that an empty Attribute array throws an IllegalArgumentException
    try {
      BinaryBookshelf b1 = new BinaryBookshelf(a1);
      System.out.println("ERROR: EMPTY ATTRIBUTE ARRAY SHOULD THROW AN EXCEPTION");
      return false;
    } catch(IllegalArgumentException iae) {
      //expected
    }
    
    // create empty bookshelf with an Attribute array with length > 4
    Attribute[] a2 = new Attribute[5];
    
    // ensures that an Attribute array not containing exactly 4 elements 
    // throws an IllegalArgumentException
    try {
      BinaryBookshelf b2 = new BinaryBookshelf(a2);
      System.out.println("ERROR: ARRAY WITH MORE THAN 4 ELEMENTS SHOULD THROW AN EXCEPTION");
      return false;
    } catch(IllegalArgumentException iae) {
      // expected
    }
    
    // create empty bookshelf with Attribute array with 2 or more similar elements
    Attribute[] a3 = {Attribute.AUTHOR, Attribute.PAGECOUNT, Attribute.TITLE, Attribute.AUTHOR};
    
    // ensures Attribute arrays with non-unique elements throws IllegalArgumentException
    try {
      BinaryBookshelf b3 = new BinaryBookshelf(a3);
      System.out.println("ERROR: DUPLICATE ELEMENT ATTRIBUTE ARRAY SHOULD THROW AN EXCEPTION");
      return false;
    } catch(IllegalArgumentException iae) {
      //expected
    }
    
    // creates a bookshelf with Attribute array with 4 unique, but author isn't first 
    Attribute[] a4 = {Attribute.PAGECOUNT, Attribute.AUTHOR, Attribute.TITLE, Attribute.ID};
    
    // ensures Attribute arrays without AUTHOR first throws an exception
    try {
      BinaryBookshelf b4 = new BinaryBookshelf(a4);
      System.out.println("ERROR: ATTRIBUTE ARRAY W/O AUTHOR FIRST SHOULD THROW AN EXCEPTION");
      return false;
    } catch(IllegalArgumentException iae) {
      // expected
    }
    
    // scenario 2: valid constructor input
    // bookshelf to test
    BinaryBookshelf b5;
    
    // valid Attribute array
    Attribute[] a5 = {Attribute.AUTHOR, Attribute.PAGECOUNT, Attribute.ID, Attribute.TITLE};
    
    // creates bookshelf and ensures valid Attribute arrays don't throw an IllegalArgumentException
    try { 
      b5 = new BinaryBookshelf(a5);
    } catch(IllegalArgumentException iae) {
      System.out.println("ERROR: VALID b5 SHOULD NOT THROW AN IllegalArgumentException");
      return false;
    } catch(Exception e) {
      System.out.println("An unexpected error has occured");
      return false;
    }
    
    // verifies attributes of being empty return true
    try {
      // verifies size is 0
      if(b5.size() != 0) {
        System.out.println("ERROR: SIZE SHOULD BE 0");
        return false;
      } 
      
      // verifies isEmpty() works properly
      if(!b5.isEmpty()) {
        System.out.println("ERROR: b5 SHOULD BE EMPTY");
        return false;
      }
      
      // verifies toString() returns an empty String
      if(!b5.toString().equals("")) {
        System.out.println("ERROR: b5.toString() SHOULD BE AN EMPTY STRING");
        return false;
      }
    } catch(Exception e) {
      System.out.println("An unexpected error has occured");
      return false;
    }
    
    // verifies getAttributeOrder() returns string containing attributes provided in order
    try {
      String expectedString = "1:" + a5[0].name() + " 2:" + a5[1].name() + " 3:" + a5[2].name() 
          + " 4:" + a5[3].name();
      String returnedString = b5.getAttributeOrder();
      
      if(!returnedString.equals(expectedString)) {
        System.out.println("ERROR: returnedString does not match expectedString");
        System.out.println("expectedString: " + expectedString);
        System.out.println("returnedString: " + returnedString);
        return false;
      }
    } catch(Exception e) {
      System.out.println("An unexpected error has occured");
      return false;
    }
    
    // verifies contains returns false and doesn't throw an exception
    try {
      Book book0 = new Book("Good Omens", 288, "Gaiman", "Neil");
      
      if(b5.contains(book0)) {
        System.out.println("ERROR: b5 SHOULD NOT CONTAIN ANYTHING");
        return false;
      }
    } catch(Exception e) {
      System.out.println("An unexpected error has occured");
      return false;
    }
    
    // verifies getBooksByAuthor() method returns empty and doesn't throw an exception
    try {
      // an ArrayList to hold books, should be empty
      ArrayList<Book> al1 = b5.getBooksByAuthor("Gaiman, Neil");
      
      if(!al1.isEmpty()) {
        System.out.println("ERROR: THE RETURNED ArrayList SHOULD BE EMPTY");
        return false;
      }
    } catch(Exception e) {
      System.out.println("An unexpected error has occured");
      return false;
    }
    
    // resets book IDGenerator
    Book.resetGenerator();
    
    return true;
  }
  
  /**
   * Tests insertBook method in BinaryBookshelf.java to ensure it works properly
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean testInsertBook() {
    // valid Attribute array
    Attribute[] a = {Attribute.AUTHOR, Attribute.TITLE, Attribute.PAGECOUNT, Attribute.ID};
    
    // new bookshelf to insert books into
    BinaryBookshelf bookshelf = new BinaryBookshelf(a);
    
    // test to make sure bookshelf is empty
    try {
      // verifies size is 0
      if(bookshelf.size() != 0) {
        System.out.println("ERROR: SIZE SHOULD BE 0");
        return false;
      }
      
      // verifies isEmpty() works 
      if(!bookshelf.isEmpty()) {
        System.out.println("ERROR: bookshelf SHOULD BE EMPTY");
        return false;
      }

      // ensures toString() returns an empty String
      if(!bookshelf.toString().equals("")) {
        System.out.println("ERROR: bookshelf.toString() SHOULD BE AN EMPTY STRING");
        return false;
      }       
    } catch(Exception e) {
      System.out.println("An unexpected error has occured");
      return false;
    }
    
    // creates new book to add
    Book book0 = new Book("Good Omens", 288, "Gaiman", "Neil");
    
    // adds book if it does not exist
    // catch used as a failsafe for a faulty contains
    try {
      // ensures book does not exist
      // returns false if it does exist
      if(!bookshelf.contains(book0)) {
        bookshelf.insertBook(book0);
      } else {
        System.out.println("ERROR: bookshelf should not contain this book");
        return false;
      }
    } catch(IllegalArgumentException iae) {
      System.out.println("Book already exists");
      return false;
    }
    
    try {
      // test to make sure it is no longer empty
      if(bookshelf.size() != 1) {
        System.out.println("ERROR: SIZE SHOULD BE 1");
        System.out.println("size = " + bookshelf.size());
        return false;
      } else if(bookshelf.isEmpty()) {
        System.out.println("ERROR: bookshelf SHOULD NOT BE EMPTY");
        return false;
      } else if(bookshelf.toString().equals("0: \"Good Omens\", Gaiman, Neil (288)\n")) {
        System.out.println("ERROR: bookshelf.toString() SHOULD BE"
            + " \"0: \"Good Omens\", Gaiman, Neil (288)\n\"");
        return false;
      }       

      // ensures book0 is placed as the root
      if(!bookshelf.getRoot().getData().equals(book0)) {
        System.out.println("ERROR: THE ROOT OF bookshelf SHOULD BE BOOK 0");
        return false;
      }
      
      // ensures book0 is the book that was added
      if(!bookshelf.contains(book0)) {
        System.out.println("ERROR: THE BOOKSHELF SHOULD NOW CONTAIN book0");
        return false;
      }
    } catch(Exception e) {
      System.out.println("An unexpected error has occured");
      return false;
    }
    
    // creates new book to be left child of book 0
    Book book1 = new Book("2001", 296, "Clarke", "Arthur C");
    
    // adds book if it does not exist
    // catch used as a failsafe for a faulty contains
    try {
      // ensures book does not exist
      // returns false if it does exist
      if(!bookshelf.contains(book1)) {
        bookshelf.insertBook(book1);
      } else {
        System.out.println("ERROR: bookshelf should not contain this book");
        return false;
      }
    } catch(IllegalArgumentException iae) {
      System.out.println("Book already exists");
      return false;
    }
    
    try {
      // makes sure it was added in the right spot
      if(!bookshelf.getRoot().getLeft().getData().equals(book1)) {
        System.out.println("ERROR: THE ROOT'S LEFT CHILD SHOULD BE book3");
        return false;
      }
      
      // ensures size increases
      if(bookshelf.size() != 2) {
        System.out.println("ERROR: SIZE SHOULD BE 2");
        System.out.println("size = " + bookshelf.size());
        return false;
      }
      
      // makes sure this book was actually added
      if(!bookshelf.contains(book1)) {
        System.out.println("ERROR: THE BOOKSHELF SHOULD NOW CONTAIN book1");
        return false;
      }
    } catch(Exception e) {
      System.out.println("An unexpected error has occured");
      return false;
    }
    
    // creates new book with the same author to be the right child
    Book book2 = new Book("The Ocean at the End of the Lane", 178, "Gaiman", "Neil");
    
    // adds book if it does not exist
    // catch used as a failsafe for a faulty contains
    try {
      // ensures book does not exist
      // returns false if it does exist
      if(!bookshelf.contains(book2)) {
        bookshelf.insertBook(book2);
      } else {
        System.out.println("ERROR: bookshelf should not contain this book");
        return false;
      }
    } catch(IllegalArgumentException iae) {
      System.out.println("Book already exists");
      return false;
    }
    
    try {
      // ensures book2 was placed in the right location
      if(!bookshelf.getRoot().getRight().getData().equals(book2)) {
        System.out.println("ERROR: bookshelf's ROOT's LEFT CHILD SHOULD BE book2");
        return false;
      }
      
      // ensures size is increasing
      if(bookshelf.size() != 3) {
        System.out.println("ERROR: SIZE SHOULD BE 3");
        System.out.println("size = " + bookshelf.size());
        return false;
      }
      
      // ensures book2 is actually in the bookshelf
      if(!bookshelf.contains(book2)) {
        System.out.println("ERROR: THE BOOKSHELF SHOULD NOW CONTAIN book2");
        return false;
      }
    } catch(Exception e) {
      System.out.println("An unexpected error has occured");
      return false;
    }
    
    // should throw exception since book2 is already in the bookshelf
    try {
      bookshelf.insertBook(book2);
      System.out.println("ERROR: THE SAME BOOKS SHOULD NOT BE ABLE TO BE ADDED TWICE");
      return false;
    } catch(IllegalArgumentException iae) {
      // expected
    }
     
    // resets book IDGenerator
    Book.resetGenerator();
    
    return true;
  }
  
  /**
   * Tests contains method in BinaryBookshelf.java to ensure it works properly
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean testContains() {
    // creates valid Attribute array
    Attribute[] a = {Attribute.AUTHOR, Attribute.TITLE, Attribute.PAGECOUNT, Attribute.ID};
    // creates new BinaryBookshelf
    BinaryBookshelf bookshelf = new BinaryBookshelf(a);
    
    // creates new Book and inserts it
    Book book0 = new Book("Good Omens", 288, "Gaiman", "Neil");
    bookshelf.insertBook(book0);
    
    // creates new Book but does not insert it
    Book book1 = new Book("FEED", 608, "Grant", "Mira");
    
    try {
      // ensures that the bookshelf contains book0
      if(!bookshelf.contains(book0)) {
        System.out.println("ERROR: bookshelf SHOULD CONTAIN BOOK 0");
        return false;
      }
      
      // ensures bookshelf does not contain book1
      if(bookshelf.contains(book1)) {
        System.out.println("ERROR: bookshelf SHOULD ONLY CONTAIN BOOK 0");
        return false;
      }
    } catch(Exception e) {
      System.out.println("An unexpected error has occured");
      return false;
    }
    
    // creates 6 new books to create a tree with
    Book book2 = new Book("Snow Crash", 468, "Stephenson", "Neal");
    Book book3 = new Book("2001", 296, "Clarke", "Arthur C");
    Book book4 = new Book("Legend", 284, "Liu", "Marie");
    Book book5 = new Book("Genki", 382, "Banno", "Eri");
    Book book6 = new Book("Genki Workbook", 148, "Shinagawa", "Chikaka");
    Book book7 = new Book("Champion", 302, "Liu", "Marie");
    
    // inserts the books in their correct places using setLeft() and setRight()
    // left child of book0 (internal)
    bookshelf.getRoot().setLeft(new TreeNode<Book>(book3));
    // left child of book3 (leaf)
    bookshelf.getRoot().getLeft().setLeft(new TreeNode<Book>(book5));
    // right child of book0 (internal)
    bookshelf.getRoot().setRight(new TreeNode<Book>(book1));
    // right child of book1 (internal)
    bookshelf.getRoot().getRight().setRight(new TreeNode<Book>(book2));
    // left child of book2 (internal)
    bookshelf.getRoot().getRight().getRight().setLeft(new TreeNode<Book>(book4));
    // right child of book4 (leaf)
    bookshelf.getRoot().getRight().getRight().getLeft().setRight(new TreeNode<Book>(book6));
    // left child of book4 (leaf)
    bookshelf.getRoot().getRight().getRight().getLeft().setLeft(new TreeNode<Book>(book7));
    
    try {
      // ensures the bookshelf contains the root
      if(!bookshelf.contains(book0)) {
        System.out.println("ERROR: bookshelf SHOULD CONTAIN THE ROOT book0");
        return false;
      }
      
      // ensures the bookshelf contains the previously unadded book
      if(!bookshelf.contains(book1)) {
        System.out.println("ERROR: bookshelf SHOULD CONTAIN THE INTERNAL NODE book1");
        return false;
      }
      
      // ensures the bookshelf contains another random book
      if(!bookshelf.contains(book6)) {
        System.out.println("ERROR: bookshelf SHOULD CONTAIN THE LEAF NODE book6");
        return false;
      }
      
      // ensures the bookshelf does not contain a book that has not been added
      // this book has the same author as another book in the tree
      if(bookshelf.contains(new Book("Prodigy", 354, "Liu", "Marie"))) {
        System.out.println("ERROR: bookshelf SHOULD NOT CONTAIN A BOOK THAT WAS NOT ADDED");
        return false;
      }
    } catch(Exception e) {
      System.out.println("An unexpected error has occured");
      return false;
    }
    
    //resets book IDGenerator
    Book.resetGenerator();
    
    return true;
  }
  
  /**
   * Tests getBooksByAuthor method in BinaryBookshelf.java to ensure it works properly
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean testGetBooksByAuthor() {
    // creates a valid Attribute array
    Attribute[] a = {Attribute.AUTHOR, Attribute.TITLE, Attribute.PAGECOUNT, Attribute.ID};
    // creates a BinaryBookshelf
    BinaryBookshelf bookshelf = new BinaryBookshelf(a);
    
    // creates 8 books to add to bookshelf
    Book book0 = new Book("Good Omens", 288, "Gaiman", "Neil");
    Book book1 = new Book("FEED", 608, "Grant", "Mira");
    Book book2 = new Book("Snow Crash", 468, "Stephenson", "Neal");
    Book book3 = new Book("2001", 296, "Clarke", "Arthur C");
    Book book4 = new Book("Legend", 284, "Liu", "Marie");
    Book book5 = new Book("Genki", 382, "Banno", "Eri");
    Book book6 = new Book("Genki Workbook", 148, "Shinagawa", "Chikaka");
    Book book7 = new Book("Champion", 302, "Liu", "Marie");
    
    // sets book0 as the root
    bookshelf.insertBook(book0);
    
    // creates an ArrayList with all books by Neil Gaiman
    ArrayList<Book> books1 = new ArrayList<Book>(bookshelf.getBooksByAuthor("Gaiman, Neil"));
    
    // makes sure book0 is in bookshelf by using the ArrayList
    if(books1.size() != 1) {
      System.out.println("ERROR: books1 ArrayList SHOULD CONTAIN 1 BOOK");
      return false;
    } else if(!books1.contains(book0)) {
      System.out.println("ERROR: THE BOOK IN books1 SHOULD BE book0");
      return false;
    }
    
    // creates tree using the same ordering as testContains() used
    // left child of book0 (internal)
    bookshelf.getRoot().setLeft(new TreeNode<Book>(book3));
    // left child of book3 (leaf)
    bookshelf.getRoot().getLeft().setLeft(new TreeNode<Book>(book5));
    // right child of book0 (internal)
    bookshelf.getRoot().setRight(new TreeNode<Book>(book1));
    // right child of book1 (internal)
    bookshelf.getRoot().getRight().setRight(new TreeNode<Book>(book2));
    // left child of book2 (internal)
    bookshelf.getRoot().getRight().getRight().setLeft(new TreeNode<Book>(book4));
    // right child of book4 (leaf)
    bookshelf.getRoot().getRight().getRight().getLeft().setRight(new TreeNode<Book>(book6));
    // left child of book4 (leaf)
    bookshelf.getRoot().getRight().getRight().getLeft().setLeft(new TreeNode<Book>(book7));
    
    // creates an ArrayList with all books by Marie Liu
    ArrayList<Book> books2 = new ArrayList<Book>(bookshelf.getBooksByAuthor("Liu, Marie"));
    
    // ensures that it contains book7 and book4 and that the size is correct
    if(books2.size() != 2) {
      System.out.println("ERROR: books2 ArrayList SHOULD CONTAIN 2 BOOKS");
      return false;
    } else if(!books2.contains(book7) || !books2.contains(book4)) {
      System.out.println("ERROR: THE BOOKS IN books2 SHOULD BE book7 AND book4");
      return false;
    }
    
    // creates an ArrayList with all books by Eri Banno
    ArrayList<Book> books3 = new ArrayList<Book>(bookshelf.getBooksByAuthor("Banno, Eri"));
    
    // ensures that it contains book5 and that the size is correct
    if(books3.size() != 1) {
      System.out.println("ERROR: books3 ArrayList SHOULD CONTAIN 1 BOOK");
      return false;
    } else if(!books3.contains(book5)) {
      System.out.println("ERROR: THE BOOK IN books3 SHOULD BE book5");
      return false;
    }
    
    // creates an ArrayList with all books by Cinda W Chima
    ArrayList<Book> books4 = new ArrayList<Book>(bookshelf.getBooksByAuthor("Chima, Cinda W"));
    
    // ensures that there are no books because no books by Cinda W Chima were added
    if(books4.size() != 0) {
      System.out.println("ERROR: books4 ArrayList SHOULD CONTAIN NO BOOKS");
      return false;
    } else if(!books4.isEmpty()) {
      System.out.println("ERROR: books4 SHOULD BE EMPTY");
      return false;
    }
    
    // resets Books IDGenerator
    Book.resetGenerator();
    
    return true;
  }
}
