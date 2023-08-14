package com.fdmgroup.tdd.Calculator;

/**
 * The ExpressionEvaluator class is responsible for evaluating arithmetic expressions by performing the necessary operations.
 */
public class ExpressionEvaluator {
	
    /**
     * Evaluates the expression by performing the arithmetic operations.
     *
     * @param expression The expression to evaluate.
     * @return The result of the expression evaluation.
     */
    public double evaluateExpression(String expression) 
    {
    	// Check if the expression is empty
        if (expression.isEmpty()) 
        {
            return 0.0;
        }

        OperatorFinder operatorFinder = new OperatorFinder();
        
        // Find the last occurrence of addition or subtraction operator
        int index = operatorFinder.findLastOperatorIndex(expression, expression.length() - 1, 0, '+', '-');
        if (index != -1) 
        {
            return separateTwoOperands(expression, index);
        }

        // Find the last occurrence of multiplication or division operator
        index = operatorFinder.findLastOperatorIndex(expression, expression.length() - 1, 0, '*', '/');
        if (index != -1) 
        {
            return separateTwoOperands(expression, index);
        }

        // Find the last occurrence of exponentiation operator
        index = operatorFinder.findLastOperatorIndex(expression, expression.length() - 1, 0, '^');
        if (index != -1) 
        {
            return separateTwoOperands(expression, index);
        }

        // If the expression is wrapped in parentheses, evaluate the inner expression recursively
        if (expression.startsWith("(") && expression.endsWith(")")) 
        {
            String innerExpression = expression.substring(1, expression.length() - 1);
            return evaluateExpression(innerExpression);
        }
        
        // If no operators are found, parse the expression as a double and return it
        return Double.parseDouble(expression);
    }

    
    
    /**
     * Evaluates the expression that contains exponentiation operations.
     *
     * @param expression               The expression to evaluate.
     * @param exponentiationCalculator The ExponentiationCalculator instance.
     * @return The result of the expression evaluation.
     */
    private double separateTwoOperands(String expression, int operatorIndex) 
    {
        char operator = expression.charAt(operatorIndex);
        String leftOperand = expression.substring(0, operatorIndex);
        String rightOperand = expression.substring(operatorIndex + 1);
        
        double leftValue = evaluateExpression(leftOperand);
        double rightValue = 0;
        
        // If the operator is an exponentiation and the right operand is a fractional string (e.g., "2/3"), calculate the exponentiation
        if (operator == '^' && new PowerCalculator().isFractionalString(rightOperand)) 
        {
                return new PowerCalculator().power(leftValue, rightOperand);  		
        }
        else 
        {
        	rightValue = evaluateExpression(rightOperand);  
        }
  
        // Perform the arithmetic operation based on the operator
        return performOperation(leftValue, rightValue, operator);
    }

    
    
    /**
     * Evaluates the expression without exponentiation operations.
     *
     * @param expression The expression to evaluate.
     * @return The result of the expression evaluation.
     */
    private double performOperation(double leftOperand, double rightOperand, char operator) 
    {
        switch (operator) 
        {
            case '+':
                return leftOperand + rightOperand;
            case '-':
                return leftOperand - rightOperand;
            case '*':
                return leftOperand * rightOperand;
            case '/':
                return leftOperand / rightOperand;
            case '^':
                return new PowerCalculator().power(leftOperand, rightOperand);
            default:
            	return 0;
        }
    }
}
