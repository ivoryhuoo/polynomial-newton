/**
 * Subclass of Exception 
 * @author ivoryhuo
 *
 */
public class SolutionNotFound extends Exception {
	
	//Constructor that accepts a custom error message
    public SolutionNotFound(String message) { //Message parameter to its constructor 
    	//Call the constructor of the superclass (Exception) with the provided message
        super(message); 
    }
}