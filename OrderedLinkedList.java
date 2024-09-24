/**
 * Linked class that allows ordered insertion 
 * Take an element to insert and find the proper location for that element in the linked list 
 * @author ivoryhuo
 * @param <T>
 */
public class OrderedLinkedList<T extends Comparable<T>> { //Generic class, compareTo method --> the Type T that is stored in this list must be Comparable 
    
	//Private instance variables to be implemented 
	private Node<T> head; //Store the start of the linked list 
    private int size; //Keep track of the size of the linked list 

    /**
     * Constructor that accepts no parameters an creates an empty linked list 
     */
    public OrderedLinkedList() {
        head = null; //Initialize head to null (no elements in the list)
        size = 0; //Initialize size to 0
    }

    /**
     * Insert method that accepts a single parameter of type (the generic type) and has void return type
     * The method inserts the value into the list in the proper position (maintained in order from largest to smallest
     * Calculate the proper posotion of the element in the Ordered List
     * @param data
     */
    public void insert(T data) {
        Node<T> newNode = new Node<>(data, null);
        
        //Use the compareTo method to help determine the position of the element
        //If the list is empty or the data should be inserted at the beginning, make the new node the new head
        if (head == null || data.compareTo(head.getData()) >= 0) {
            newNode.setNext(head);
            head = newNode;
        } else {
        	//If the data should be inserted in the middle or at the end, traverse the list to find the appropriate position
            Node<T> current = head;
            while (current.getNext() != null && data.compareTo(current.getNext().getData()) < 0) {
                current = current.getNext();
            }
            newNode.setNext(current.getNext());
            current.setNext(newNode);
        }
        size++; //Increment the size of the list
    }

    /**
     * Get method that takes a single integer parameter i returns the i-th element in the list (of type T)
     * The method should be zero-indexed (i.e. get (0), should return the first element in the list)
     * If the i-th element in the list does not exist, the method should throw an IndexOutOfBoundsException (which is built-in Java).
     * @param index of the element to retrieve
     * @return the element at the specific index
     */
    public T get(int index) {
        if (index < 0 || index >= size) { //If the index is outside, throw the built-in Java Exception 
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        //Loop through the list for the element at the specific index 
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }

        return current.getData(); //Return the data stored at the specific index 
    }

    /**
     * Method that returns the number of elements in the linked list
     * @return size of the linked list
     */
    public int getSize() {
        return size; //return the size of the linked list 
    }
}