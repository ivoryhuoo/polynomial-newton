/**
 * Generic node class
 * @author ivoryhuo
 *
 */
public class Node <T> {

	//Private instance variables to be implemented
	private T data;
	private Node<T> next;
	
	/**
	 * Constructor that takes both data and next parameters
	 * @param data
	 * @param next
	 */
	public Node (T data, Node<T> next) {
		this.data = data;
		this.next = next;
	}
	
	/**
	 * Accessor method to return next
	 * @return next
	 */
	public Node<T> getNext() {
		return next;
	}
	
	/**
	 * Accessor method to return data
	 * @return data
	 */
	public T getData() {
		return data;
	}
	
	/**
	 * Mutator method which takes a parameter of type Node<T>
	 * @param next
	 */
	public void setNext(Node<T> next) {
		this.next = next;
	}
	
}
