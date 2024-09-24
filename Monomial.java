/**
 * Class that stores a monomial
 * @author ivoryhuo
 */
public class Monomial implements Comparable<Monomial> { //Define the class as having a compareTo method (use OrderedLinkedList properly)
	
	//Private instance variables to be implemented
    private int coefficient;
    private int degree;

    /**
     * Constructor that accepts both coefficient and degree instance variables
     * @param coefficient of monomial
     * @param degree of monomial
     */
    public Monomial(int coefficient, int degree) {
        this.coefficient = coefficient; 
        this.degree = degree;
    }

    /**
     * Accessor method for the coefficient of the monomial
     * @return coefficient of monomial
     */
    public int getCoefficient() {
        return coefficient;
    }

    /**
     * Accessor method for the degree of the monomial
     * @return degree of monomial
     */
    public int getDegree() {
        return degree;
    }

    /**
     * Method that accepts a monomial as a parameter and returns an integer
     * Determine which monomial has the higher degree
     * Copy pasted from the assignment 
     */
    @Override
    public int compareTo(Monomial m) {
        return this.degree - m.degree;
    }
}