package com.fdmgroup.tdd.Calculator;

public class OperatorFinder {
	
    /**
     * Finds the index of the last occurrence of an operator in the expression.
     *
     * @param expression       The expression to search for operators.
     * @param index            The current index in the expression.
     * @param parenthesesCount The count of open parentheses encountered so far.
     * @param operators        The array of operators to search for.
     * @return The index of the last occurrence of an operator, or -1 if not found.
     */
    public int findLastOperatorIndex(String expression, int index, int parenthesesCount, char... operators) 
    {
        if (index < 0) {
            return -1;
        }

        char currentChar = expression.charAt(index);

        // Update parentheses count for proper handling of parentheses
        if (currentChar == ')') 
        {
            parenthesesCount++;
        } 
        else if (currentChar == '(') 
        {
            parenthesesCount--;
        } 
        else if (parenthesesCount == 0 && containsOperator(currentChar, operators)) 
        {
            return index;
        }

        return findLastOperatorIndex(expression, index - 1, parenthesesCount, operators);
    }

    
    
    /**
     * Checks if a character is one of the specified operators.
     *
     * @param ch        The character to check.
     * @param operators The array of operators to compare against.
     * @return True if the character is one of the operators, false otherwise.
     */
    private boolean containsOperator(char ch, char... operators) 
    {
    	// Check if a character is one of the specified operators
        if (ch == operators[0]) 
        {
            return true;
        }

        if (operators.length == 1) 
        {
            return false;
        }

        char[] remainingOperators = new char[operators.length - 1];
        System.arraycopy(operators, 1, remainingOperators, 0, operators.length - 1);

        return containsOperator(ch, remainingOperators);
    }
}
