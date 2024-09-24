/**
 * Class that uses a linked list to store a polynomial
 * @author ivoryhuo
 *
 */
public class Polynomial {

	//Private instance variable
	//Stores monomials --> when declaring this instance variable, let the generic type be Monomial
	private OrderedLinkedList<Monomial> polynomial;
	
	/**
	 * Constructor that accepts no parameters, which creates an empty polynomial (i.e., one with no monomials).
	 */
    public Polynomial() {
        polynomial = new OrderedLinkedList<>();
    }
    
    /**
     * Method to add a monomial to the polynomial
     * Takes two integer parameters (a coefficient and a degree) and adds a new monomial to the polynomial
     * Assume that a monomial of the same degree does not already exist in the Polynomial and that the degree is greater than or equal to zero.
     * @param coefficient
     * @param degree
     */
    public void add(int coefficient, int degree) {
    	//Call the monomial constructor with parameters
        Monomial monomial = new Monomial(coefficient, degree);
        //Assume that a monomial of the same degree does not already exist
        //Degree is greater than or equal to 0 --> simply insert it 
        polynomial.insert(monomial);
    }

    /**
     * Method to calculate the derivative of the polynomial
     * @return new polynomial object (the derivative of the polynomial whose method was called (i.e. the derivative of this)
     */
    public Polynomial derivative() {
    	//Create a new Polynomial object to store the result of the derivative calculation
        Polynomial derivative = new Polynomial();
        
        //Iterate through each monomial in the original polynomial stored in the polynomial field
        for (int i = 0; i < polynomial.getSize(); i++) {
            Monomial monomial = polynomial.get(i); //get monomial at current index
            int coefficient = monomial.getCoefficient() * monomial.getDegree(); //calculate coefficient (multiply coefficient by degree --> power rule for differentation)
            int degree = monomial.getDegree() - 1; //calculate degree by subtracting 1 from the degree of the current monomial --> power rule for differentiation
            if (degree >= 0) { //check if the calculated degree for the derivative term is greater than or equal to zero (don't add terms with negative degrees)
                //If non negative, add the calculated coefficient and degree as a new monomial to the derivative polynomial using the add method
            	derivative.add(coefficient, degree); //creates a new term in the derivative polynomial based on the derivative calculations for each monomial
            }
        }
        return derivative; //Return the derivative polynomial 
    }
    
    /**
     * Method that evaluates the polynomial for a given value
     * @param z
     * @return f(z) the value where the polynomial is stored in class
     */
    public double eval(double z) {
        double result = 0; //store the result
        
        //Iterate through each monomial in the original polynomial stored in the polynomial field
        for (int i = 0; i < polynomial.getSize(); i++) {
            Monomial monomial = polynomial.get(i); //get current monomial at the index 
            //Calculate contribution of the current monomial to the overall result
            result += monomial.getCoefficient() * Math.pow(z, monomial.getDegree());
        }
        return result; //Value of the polynomial when evaluated at the specified value of z
    }
    
    /**
     * Method that gives a string representation of all nonzero monomials in the polynomial
     * @return String representation
     */
    public String toString() { 
        String result = ""; //Initialize an empty string to store the result
        boolean firstMonomial = true; // Flag to track the first monomial in the polynomial

        //Iterate through the monomials in the polynomial 
        for (int i = 0; i < polynomial.getSize(); i++) {
            Monomial monomial = polynomial.get(i); //Get the monomial 
            //Retrieve the coefficient and degree information about the monomial 
            int coefficient = monomial.getCoefficient();
            int degree = monomial.getDegree();

            //Ensure only nonzero monomials are represented 
            if (coefficient != 0) { //Check if the coefficient is non-zero
                if (!firstMonomial) {
                    if (coefficient > 0) { 
                        result += " + "; //Use + operator
                    } else {
                        result += " - "; //Use - operator
                    }
                } else if (coefficient < 0) {
                    result += "-"; //Add minus sign without a space for the first monomial if it's negative
                }

                //Check coefficient display 
                if (Math.abs(coefficient) != 1 || degree == 0) {
                    //Handle coefficients other than 1 and -1, or zero degree
                    result += Math.abs(coefficient); //Display absolute value 
                } else {
                    result += "1"; //Always display the number 1 as a coefficient
                }

                //Check value of the degree 
                if (degree > 0) {
                    result += "*x"; 
                    if (degree > 1) { //If degree > 1, display the degree 
                        result += "^" + degree;
                    } else if (degree == 1) {
                        result += "^1"; //Display "^1" when the degree is 1 (default)
                    }
                } else {
                    result += "*x^0"; //Explicitly show x^0 when there is no "x" variable
                }

                firstMonomial = false; //Update the flag to indicate the first monomial is processed
            }
        }

        if (firstMonomial) { //If there were no non-zero monomials, return "0"
            result += "0";
        }

        return result; //Return the string representation
    }
    
    /**
     * Method that implements Newton's Method with 3 parameters 
     * @param x0, the initial value for the solution search 
     * @param e, the double value representing the tolerance of the solution
     * @param T, a double value representing the root of the polynomial 
     * @throws SolutionNotFound 9different message depending on the situation)
     */
    public double solve(double x0, double e, int T) throws SolutionNotFound {
        //Initialize the previous to the initial value provided as x0
        double previous = x0;

        for (int i = 0; i < T; i++) { 
            //Calculate the derivative value at the previous point
            double derivativeValue = derivative().eval(previous);

            //Check if the derivative at the previous is not zero to avoid division by zero
            if (Math.abs(derivativeValue) < 1e-10) {
                throw new SolutionNotFound("divide by zero error");
            }

            //Calculate the current using Newton's formula with the previous point
            double current = previous - eval(previous) / derivativeValue;

            //Check if the absolute difference between previous and current is within the tolerance e
            if (Math.abs(current - previous) < e) {
                return current; //Found a solution within tolerance
            }

            previous = current; //Update previous with the current result
        }

        //If the maximum number of iterations is reached, raise an exception
        throw new SolutionNotFound("maximum iteration exceeded");
    }
}
