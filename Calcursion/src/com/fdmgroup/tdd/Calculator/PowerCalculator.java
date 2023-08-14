package com.fdmgroup.tdd.Calculator;

/**
 * The PowerCalculator class is responsible for performing exponentiation operations.
 */
public class PowerCalculator {
	
    /**
     * Calculates the power of a base with an integer exponent.
     *
     * @param base     The base number.
     * @param exponent The exponent in a form of an integer.
     * @return The result of the power operation.
     */
    public double power(double base, int exponent) 
    {
        // Calculate the power of the base recursively
        if (exponent == 0) 
        {
            return 1.0;
        } 
        else if (exponent > 0) 
        {
            return base * power(base, exponent - 1);
        } else 
        {
            return 1.0 / (base * power(base, -exponent - 1));
        }
    }
    
    
    
    /**
     * Calculates the power of a base with a decimal exponent.
     *
     * @param base     The base number.
     * @param exponent The exponent in the form of a decimal.
     * @return The result of the power operation.
     */
	public double power(double base, double exponent) 
	{
		// Convert decimal exponent to fraction
		int[] fraction = decimalToFraction(exponent);
		int numerator = fraction[0];
		int denominator = fraction[1];
    	return powerHelper(base, numerator, denominator);
	}
	
	
	/**
	 * Recursive helper method to calculate the power of a base with a fractional exponent.
	 *
	 * @param base       The base number.
	 * @param numerator   The numerator of the fractional exponent.
	 * @param denominator The denominator of the fractional exponent.
	 * @return The result of the power operation.
	 */
	public double powerHelper(double base, int numerator, int denominator) 
	{
		String exponent;
		
		// Handle negative numerator by converting it to a positive exponent
		if (numerator < 0) {
			base = power(base, -1);
			numerator = -numerator;
		}
		
		// Check if both numerator and denominator are prime numbers
		if (isPrime(numerator) && isPrime(denominator)) {
			exponent = "("+numerator+"/"+denominator+")";
			return power(base, exponent);
		}
		
		// Handle cases where numerator is not prime
		if (!isPrime(numerator)) {
			int smallestDivisorNumerator =  findSmallestDivisor(numerator);
			exponent = "("+smallestDivisorNumerator+"/1)";			
			base = power(base, exponent);
			numerator = numerator / smallestDivisorNumerator;
		}
		
		// Handle cases where denominator is not prime
		if (!isPrime(denominator)) {
			int smallestDivisorDenominator = findSmallestDivisor(denominator);
			exponent = "(1/"+smallestDivisorDenominator+")";	
			base = power(base, exponent);
			denominator = denominator / smallestDivisorDenominator;
		}
		
		// Recursive call with updated numerator and denominator
		return powerHelper(base, numerator, denominator);
	}
	
	
	
    /**
     * Finds the smallest divisor of a number.
     *
     * @param number The number to find the smallest divisor of.
     * @return The smallest divisor of the number.
     */
    public int findSmallestDivisor(int number) {
    	return findSmallestDivisorHelper (number, 2);
    }
    
    
    
    /**
     * Recursive helper method to find the smallest divisor of a number.
     *
     * @param number  The number to find the smallest divisor of.
     * @param divisor The current divisor to check.
     * @return The smallest divisor of the number.
     */
    public int findSmallestDivisorHelper(int number, int divisor) {
    	// Check if the current divisor divides the number evenly
    	if (number % divisor == 0) {
    		return divisor; // Return the divisor if it is a smallest divisor
    	}
    	
    	// Recursive call with the next divisor
		return findSmallestDivisorHelper(number, divisor+1); 
    }
    
    
    
    /**
     * Checks if a number is prime.
     *
     * @param number The number to check for primality.
     * @return True if the number is prime, false otherwise.
     */
    public boolean isPrime(int number) {
    	return isPrimeHelper(number, 2);
    }
    
    
    
    /**
     * Recursive helper method to check if a number is prime.
     *
     * @param number  The number to check for primality.
     * @param divisor The current divisor to check.
     * @return True if the number is prime, false otherwise.
     */
    public boolean isPrimeHelper(int number, int divisor) {
    	// Base case: If the divisor exceeds half of the number, it is prime
    	if (divisor > number/2) {
    		return true;
    	}
    	
    	// Check if the current divisor divides the number evenly
    	if (number % divisor == 0 && divisor < number) {
    		return false;
    	}
    	
    	// Recursive call with the next divisor
    	return isPrimeHelper(number, divisor+1);
    	
    }
	
	
    /**
     * Checks if the exponent is a fractional expression (e.g., "(2/3)").
     *
     * @param expression The exponent expression in the form of a fraction in brackets.
     * @return True if the exponent is a fractional expression, false otherwise.
     */
	public boolean isFractionalString(String expression) 
	{
		// Check if the expression represents a fractional number
        if (expression.contains("/") && 
        		!expression.contains(".") && 
        		!expression.contains("+") && !expression.contains("*") && !expression.substring(expression.indexOf('/')+1, expression.length()).contains(expression) && 
        		expression.startsWith("(") && expression.endsWith(")")) {
        	return true;
        }
        else 
        {
        	return false;
        }
	}
	
	
	
    /**
     * Calculates the power of a base with a fractional exponent expressed as a string (e.g., "(2/3)").
     *
     * @param base     The base number.
     * @param exponent The fractional exponent in the form of a string.
     * @return The result of the power operation.
     */
	public double power(double base, String exponent ) 
	{
		// Calculate the exponentiation of a base and fractional exponent
    	int numerator = Integer.parseInt(exponent.substring(1,exponent.indexOf('/')));
    	int denominator = Integer.parseInt(exponent.substring(exponent.indexOf('/')+1, exponent.length()-1));
		     
    	double result = calculateNthRoot(power(base, numerator), denominator);
		
        return result;
	}
	
	
	
    /**
     * Converts a decimal number to a fraction.
     *
     * @param decimal The decimal number.
     * @return An array representing the numerator and denominator of the fraction.
     */
	public int[] decimalToFraction(double decimal) 
	{
		// Convert a decimal number to a fraction
		int maxDigitsAfterDecimalAllowed = 4;
        int numerator = (int) (decimal * power(10, maxDigitsAfterDecimalAllowed));
        int denominator = (int) power(10, maxDigitsAfterDecimalAllowed);
        int gcd = findGCD(numerator, denominator);
        numerator /= gcd;
        denominator /= gcd;
        
        if (denominator < 0) 
        {
        	numerator = -numerator;
        	denominator = -denominator;
        }

        int[] fraction = {numerator, denominator};
        return fraction;
        
    }

	
	
    /**
     * Finds the greatest common divisor (gcd) of two numbers.
     *
     * @param a The first number.
     * @param b The second number.
     * @return The greatest common divisor.
     */
    // Recursive method to find the greatest common divisor (gcd)
    private int findGCD(int a, int b) 
    {
        // Find the greatest common divisor (gcd) of two numbers recursively
        if (b == 0) 
        {
            return a;
        }
        return findGCD(b, a % b);
    }
    
    
    
    /**
     * Calculates the nth root of a number using the Newton-Raphson method.
     *
     * @param number The number to calculate the root of.
     * @param n      The degree of the root.
     * @return The nth root of the number.
     */
    // Calculate Nth Root
    private double calculateNthRoot(double x, int n) 
    {
    	// Calculate the Nth root of a number
        if (x == 0) 
        {
            return 0; // Special case: root of 0 is 0
        }
        if (n == 1) 
        {
        	return x; // Special case: x power 1 is x
        }

        double guess = x / n; // Initial guess for the nth root
        return calculateNthRootRecursive(x, n, guess);
    }

    
    
    /**
     * Recursive method to calculate the next guess for the nth root using Newton's method.
     *
     * @param x     The number to calculate the root of.
     * @param n     The degree of the root.
     * @param guess The current guess for the root.
     * @return The next guess for the root.
     */
    private double calculateNthRootRecursive(double x, int n, double guess) 
    {
        // Calculate the next guess using the Newton's method
    	  	
    	// Calculate the next guess using the formula: nextGuess = ((n - 1) * guess + x / guess^(n - 1)) / n
    	
    	// Calculate the next guess
    	double nextGuess = ((n - 1) * guess + x / power(guess, n - 1)) / n;

        // Check if the next guess is close enough to the previous guess
        if (closeEnough(nextGuess, guess)) 
        {
            return truncate(nextGuess, 6); // Truncate to 6 decimal places
        }

        // Recursive call with the next guess as the new current guess
        return calculateNthRootRecursive(x, n, nextGuess);
    }

    
    
    /**
     * Checks if two numbers are close enough (within a specified epsilon).
     *
     * @param a The first number.
     * @param b The second number.
     * @return True if the numbers are close enough, false otherwise.
     */
    private boolean closeEnough(double a, double b) 
    {
        // Check if two numbers are close enough (within a specified epsilon)
    	
        double epsilon = 0.0000001; // The desired precision
        
        // Compare the difference between the numbers with the epsilon
        return truncate(a - b, 6) < epsilon && truncate(b - a, 6) < epsilon;
    }

    
    
    /**
     * Truncates the value to the specified number of decimal places.
     *
     * @param value          The value to truncate.
     * @param decimalPlaces The number of decimal places to keep.
     * @return The truncated value.
     */
    private double truncate(double value, int decimalPlaces) 
    {
        // Truncate the value to the specified number of decimal places
    	
        // Multiply the value by the power of 10, truncate the fractional part, and divide by the power of 10
        int multiplier = (int) power(10, decimalPlaces);
        return floor(value * multiplier) / multiplier;
    }

    
    
    /**
     * Truncates the fractional part of the value.
     *
     * @param value The value to truncate.
     * @return The truncated value.
     */
    private double floor(double value) 
    {
        // Truncate the fractional part of the value
    	
        // If the integer part is less than or equal to the value, return the integer part
        // Otherwise, return the integer part minus 1
        int intValue = (int) value;
        if (intValue <= value) 
        {
            return intValue;
        } 
        else 
        {
            return intValue - 1;
        }
    }
}
