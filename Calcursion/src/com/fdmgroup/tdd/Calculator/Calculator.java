package com.fdmgroup.tdd.Calculator;

public class Calculator implements ICalculator {

	public double evaluate(String expression) 
    {
    	// Step 1: Sanitize the input expression to remove whitespace
        ExpressionSanitizer sanitizer = new ExpressionSanitizer();
        String sanitizedExpression = sanitizer.sanitizeInput(expression);

        // Step 2: Add parentheses to negative numbers for correct evaluation
        NegativeNumberParenthesesAdder parenthesisAdder = new NegativeNumberParenthesesAdder();
        String addedParenthesisSanitizedExpression = parenthesisAdder.addParentheses(sanitizedExpression);

        // Step 3: Evaluate the sanitized expression
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        double result = evaluator.evaluateExpression(addedParenthesisSanitizedExpression);
        return result;
    }
}
