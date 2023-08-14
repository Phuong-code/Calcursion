package com.fdmgroup.tdd.Calculator;

/**
 * The ExpressionSanitizer class is responsible for sanitizing the input expression by removing whitespace characters.
 */
public class ExpressionSanitizer {
	
    /**
     * Sanitizes the input expression by removing whitespace characters.
     *
     * @param expression The expression to sanitize.
     * @return The sanitized expression without whitespace.
     */
    public String sanitizeInput(String expression) 
    {
    	// Remove whitespace characters from the expression
        return sanitizeInputRecursive(expression, 0, new StringBuilder());
    }
    
    
     
    /**
     * Recursive helper method to sanitize the input expression.
     *
     * @param expression           The expression to sanitize.
     * @param index                The current index in the expression.
     * @param sanitizedExpression  The StringBuilder to store the sanitized expression.
     * @return The sanitized expression without whitespace.
     */
    private String sanitizeInputRecursive(String expression, int index, StringBuilder sanitizedExpression) 
    {
    	// Base case: If the index exceeds the expression length, return the sanitized expression
    	if (index >= expression.length()) 
    	{
            return sanitizedExpression.toString();
        }

        char c = expression.charAt(index);
        // Skip whitespace characters
        if (!Character.isWhitespace(c)) 
        {
            sanitizedExpression.append(c);
        }
        
        // Recursive call to process the next character
        return sanitizeInputRecursive(expression, index + 1, sanitizedExpression);
    }
}
