package com.fdmgroup.tdd.Calculator;

public class NegativeNumberParenthesesAdder {
		
    /**
     * Adds parentheses to negative numbers in the expression for correct evaluation.
     *
     * @param expression The expression to add parentheses to.
     * @return The expression with added parentheses to negative numbers.
     */
	public String addParentheses(String expression) 
	{
		// Add parentheses to negative numbers in the expression
        return addParenthesesRecursive(expression, 0, new StringBuilder(), 0);
    }

	
	
    /**
     * Recursive helper method to add parentheses to negative numbers in the expression.
     *
     * @param expression             The expression to add parentheses to.
     * @param index                  The current index in the expression.
     * @param addedBracketExpression The StringBuilder to store the expression with added parentheses.
     * @param parenthesesCount       The count of open parentheses encountered so far.
     * @return The expression with added parentheses to negative numbers.
     */
    private String addParenthesesRecursive(String expression, int index, StringBuilder addedBracketExpression, int parenthesesCount) 
    {
    	// Base case: If the index exceeds the expression length, add closing parentheses if necessary and return the added bracket expression
    	if (index >= expression.length()) 
    	{
            if (parenthesesCount == 1) 
            {
                addedBracketExpression.append(')');
            }
            return addedBracketExpression.toString();
        }

        char c = expression.charAt(index);
        
        // Check if a opening parenthesis should be added before the current character
        if (shouldAddOpeningParenthesis(expression, index, parenthesesCount)) 
        {
            addedBracketExpression.append('(');
            parenthesesCount++;
        }
        
        // Check if a closing parenthesis should be added before the current character
        if (shouldAddClosingParenthesis(expression, index, parenthesesCount)) 
        {
            addedBracketExpression.append(')');
            parenthesesCount--;
        }

        addedBracketExpression.append(c);

        return addParenthesesRecursive(expression, index + 1, addedBracketExpression, parenthesesCount);
    }

    
    
    /**
     * Checks if an opening parenthesis should be added before a negative number.
     *
     * @param expression       The expression.
     * @param index            The current index in the expression.
     * @param parenthesesCount The count of open parentheses encountered so far.
     * @return True if an opening parenthesis should be added before a negative number, false otherwise.
     */
    private boolean shouldAddOpeningParenthesis(String expression, int index, int parenthesesCount) 
    {
        // Check if an opening parenthesis should be added before a negative number
        return expression.charAt(index) == '-' && 
        		(index != 0 && isOperator(expression.charAt(index - 1))) && 
        		parenthesesCount == 0;
    }

    
    
    /**
     * Checks if a closing parenthesis should be added after a negative number.
     *
     * @param expression       The expression.
     * @param index            The current index in the expression.
     * @param parenthesesCount The count of open parentheses encountered so far.
     * @return True if a closing parenthesis should be added after a negative number, false otherwise.
     */
    private boolean shouldAddClosingParenthesis(String expression, int index, int parenthesesCount) 
    {
        char c = expression.charAt(index);
        return parenthesesCount == 1 && isOperator(c) && 
        		index != 0 && !isOperator(expression.charAt(index - 1));
    }

    
    
    /**
     * Checks if a character is an operator (+, -, *, /, ^).
     *
     * @param ch The character to check.
     * @return True if the character is an operator, false otherwise.
     */
    private boolean isOperator(char ch) 
    {
    	// Check if a character is an arithmetic operator
        return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^';
    }
}