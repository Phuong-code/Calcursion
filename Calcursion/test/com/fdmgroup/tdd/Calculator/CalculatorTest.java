package com.fdmgroup.tdd.Calculator;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fdmgroup.tdd.Calculator.Calculator;

class CalculatorTest {
	
	Calculator calculator;
	ExpressionSanitizer experssionsanitizer;
	NegativeNumberParenthesesAdder parenthesisAdder;
	OperatorFinder operatorFinder;
	PowerCalculator powerCalculator;
	
	@BeforeEach
	void setup() {
		experssionsanitizer = new ExpressionSanitizer();
		parenthesisAdder = new NegativeNumberParenthesesAdder();
		operatorFinder = new OperatorFinder();
		calculator = new Calculator();
		powerCalculator = new PowerCalculator();
		
	}
	
	
	
	// ##################### INDIVIDUALS METHODS TESTING #####################
	
	
	
	// Test sanitizeInput method in the ExpressionSanitizer class
	
	@Test
	void test_sanitizeInput_method() {
		assertEquals("-1+23/4", experssionsanitizer.sanitizeInput(" -1 + 23 / 4"));
	}
	
	@Test
	void test_sanitizeInput_method_with_brackets() {
		assertEquals("(4--4)*(3/4)^0.5", experssionsanitizer.sanitizeInput("( 4 - - 4) *(3/4)^0.5"));
	}
	
	
	
	// Test addParentheses method in the NegativeNumberParenthesisAdder class
	
	@Test
	void test_addParenthesis_method_with_minus_minus() {
		assertEquals("2.36-(-43.3)", parenthesisAdder.addParentheses("2.36--43.3"));
	}
	
	@Test
	void test_addParenthesis_method_with_plus_minus() {
		assertEquals("3+(-5)", parenthesisAdder.addParentheses("3+-5"));
	}
	
	
	
	// Test findLastOperatorIndex method in the OperatorFinder class
	
	@Test
	void test_findLastOperatorIndex_method_with_plus() {
		assertEquals(1, operatorFinder.findLastOperatorIndex("2+3", 2, 0, '+'));
	}
	
	@Test
	void test_findLastOperatorIndex_method_with_plus_minus() {
		assertEquals(3, operatorFinder.findLastOperatorIndex("63+-25",5, 0, '+', '-'));
	}
	
	@Test
	void test_findLastOperatorIndex_method_with_multiply_division_inBrackets() {
		assertEquals(-1, operatorFinder.findLastOperatorIndex("(5*6)",4, 0, '*', '/'));
	}
	
	@Test
	void test_findLastOperatorIndex_method_with_power() {
		assertEquals(2, operatorFinder.findLastOperatorIndex("15^(1/3)",7, 0, '^'));
	}
	
	
	
	// Test power method in the ExponentiationCalculator class
	
	@Test
	void test_power_method_with_an_integer_exponent() {
		assertEquals(16, powerCalculator.power(2,4));
	}
	
	@Test
	void test_power_method_with_minus_an_integer_exponent() {
		assertEquals(0.0625, powerCalculator.power(2,-4), 0.000001);
	}
	
	@Test
	void test_power_method_with_a_fractional_exponent_in_form_of_a_string() {
		assertEquals(1.587401, powerCalculator.power(2,"(2/3)"), 0.000001);
	}
	
	@Test
	void test_power_method_with_minus_a_fractional_exponent_in_form_of_a_string() {
		assertEquals(0.629960, powerCalculator.power(2,"(-2/3)"), 0.000001);
	}
	
	@Test
	void test_power_method_with_a_decimal_exponent() {
		assertEquals(1.164733, powerCalculator.power(2,0.22), 0.000001);
	}
	
	@Test
	void test_power_method_with_a_minus_decimal_exponent() {
		assertEquals(0.901249, powerCalculator.power(2,(-0.15)), 0.000001);
	}
	
	
	
	// ##################### FINAL TESTS (EVALUATE METHOD) #####################
	
	
	
	// Test one single number
	
	@Test
	void test_minus_1() {
		assertEquals(-1, calculator.evaluate(" -1 "));
	}
	
	@Test
	void test_1_34() {
		assertEquals(1.34, calculator.evaluate(" 1.34 "));
	}
	
	

	// Test simple operation
	
	@Test
	void test_2_plus_3(){
		assertEquals(5, calculator.evaluate("2+3"));
	}
	
	@Test
	void test_4_plus_plus_2() {
		assertEquals(6, calculator.evaluate("4++2"));
	}
	
	@Test
	void test_plus_5_plus_plus_7() {
		assertEquals(12, calculator.evaluate("+5++7"));
	}
	
	@Test
	void test_minus_5_plus_3(){
		assertEquals(-2, calculator.evaluate("-5+3"));
	}
	
	@Test
	void test_2_minus_minus_3() {
		assertEquals(5, calculator.evaluate("2--3"));
	}
	
	@Test
	void test_minus_23_minus_minus_14() {
		assertEquals(-9, calculator.evaluate("-23--14"));
	}
	
	@Test
	void test_4_times_6() {
		assertEquals(24, calculator.evaluate("4*6"));
	}
	
	@Test
	void test_4_times_minus_6() {
		assertEquals(-24, calculator.evaluate("4*-6"));
	}
	
	@Test
	void test_minus_5_times_minus_6() {
		assertEquals(30, calculator.evaluate("-5*-6"));
	}
	
	@Test
	void test_12_divided_by_4() {
		assertEquals(3, calculator.evaluate("12/4"));
	}
	
	@Test
	void test_8_divided_by_minus_3() {
		assertEquals(-2.666666, calculator.evaluate("8/-3"), 0.000001);
	}
	
	@Test
	void test_expression_with_large_result() {
		assertEquals(1.0E17, calculator.evaluate("10000000000000000*10"));
	}
	
	
	
	// Test decimal numbers
	
	@Test
	void test_5_26_plus_6_98() {
		assertEquals(12.24, calculator.evaluate("5.26 + 6.98"));
	}
	
	@Test
	void test_minus_9_64_plu_minus_6_98() {
		assertEquals(-16.62, calculator.evaluate("-9.64 +- 6.98"));
	}
		
	@Test
	void test_65_4_multiply_by_3_6() {
		assertEquals(235.44, calculator.evaluate("65.4 * 3.6"), 0.000001);
	}
	
	@Test
	void test_53_1_divided_by_32_9() {
		assertEquals(1.613981, calculator.evaluate("53.1/32.9"), 0.000001);
	}
	
	
	
	//Test BODMAS
	
	@Test
	void test_1_plus_2_plus_3(){
		assertEquals(6, calculator.evaluate("1+2+3"));
	}
	
	@Test
	void test_1_plus_2_mutiply_3(){
		assertEquals(7, calculator.evaluate("1+2*3"));
	}
		
	@Test
	void test_minus_1_plus_4_mutiply_minus_3() {
		assertEquals(-13, calculator.evaluate("-1+4*-3"));
	}
	
	@Test
	void test_multiple_operations1() {
		assertEquals(-759.5, calculator.evaluate("-1 + 2/4 +23-34 * 23"));
	}
	
	@Test
	void test_multiple_operations2() {
		assertEquals(22, calculator.evaluate("3+4*5-2/2"));
	}
	
	
	
	// Test multiple operations with brackets
	
	@Test
	void test_1_plus_2_inBrackets_mutiply_3(){
		assertEquals(6, calculator.evaluate("(1+2)+3"));
	}	
	
	@Test
	void test_2_brackets() {
		assertEquals(8, calculator.evaluate("(1+2)+(2+3)"));
	}
	
	@Test
	void test_brackets_inBrackets1() {
		assertEquals(40.0, calculator.evaluate("30/5*3+10+(3+3*(6/2))"));
	}
	
	@Test
	void test_brackets_inBrackets2() {
		assertEquals(5.5, calculator.evaluate("((2+3)*4-9)/2"));
	}
	
	@Test
	void test_expression_with_long_expression_within_parentheses() {
		assertEquals(100, calculator.evaluate("((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((100))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))"));
	}
	
	
	
	// Test expression with whitespace
	
	@Test
	void test_expression_with_whitespace1() {
		assertEquals(5, calculator.evaluate("2 + 3"));
	}

	@Test
	void test_multiple_whitespace_between_numbers() {
		assertEquals(5, calculator.evaluate("2   +   3"));
	}

	@Test
	void test_whitespace_at_start() {
		assertEquals(5, calculator.evaluate("  2+3"));
	}

	@Test
	void test_whitespace_at_end() {
		assertEquals(5, calculator.evaluate("2+3  "));
	}

	@Test
	void test_whitespace_around_parentheses() {
		assertEquals(5, calculator.evaluate("( 2 + 3 )"));
	}

	@Test
	void test_whitespace_in_parentheses_and_multiple_whitespace_between_numbers() {
		assertEquals(5, calculator.evaluate("(  2   +  3   )"));
	}
	
	
	
	// Test simple Exponentiation
	
	@Test
	void test_2_power_4() {
		assertEquals(16, calculator.evaluate("2^4"));
	}

	@Test
	void test_2_power_minus_3() {
		assertEquals(0.125, calculator.evaluate("2^-3"));
	}
	
	@Test
	void test_3_multiply_2_power_3() {
		assertEquals(24, calculator.evaluate("3*2^3"));
	}
	
	@Test
	void test_1_divided_by_2_inBrackets_power_2() {
		assertEquals(0.25, calculator.evaluate("(1/2)^2"));
	}
	
	@Test
	void test_minus_2_divided_by_3_inBrackets_power_2() {
		assertEquals(-0.444444, calculator.evaluate("-(2/3)^2"), 0.000001);
	}
	
	@Test
	void test_2_power_3_inBrackets_power_2() {
		assertEquals(64, calculator.evaluate("(2^3)^2"));
	}
	
	@Test
	void test_minus_2_inBrackets() {
		assertEquals(0.25, calculator.evaluate("(-2)^(-2)"));
	}
	
	@Test
	void test_exponentiation_of_zero() {
		assertEquals(1, calculator.evaluate("2^0"));
	}
	
	@Test
	void test_expression_with_decimal_numbers_5() {
	    assertEquals(0.04, calculator.evaluate("0.2^2"), 0.000001);
	}
	
	@Test
	void test_exponentiation_with_integer_exponent() {
	    assertEquals(16, calculator.evaluate("2^4.0"));
	}

	@Test
	void test_exponentiation_with_negative_exponent() {
	    assertEquals(0.5, calculator.evaluate("2^-1"));
	}

	@Test
	void test_exponentiation_with_negative_integer_exponent() {
	    assertEquals(0.25, calculator.evaluate("2^(-2)"));
	}
	
	@Test
	void test_exponentiation_with_zero_exponent() {
	    assertEquals(1, calculator.evaluate("16^0"));
	}

	@Test
	void test_exponentiation_with_positive_exponent() {
	    assertEquals(1000, calculator.evaluate("10^3"));
	}

	@Test
	void test_exponentiation_with_zero_base_and_zero_exponent() {
	    assertEquals(1, calculator.evaluate("0^0"));
	}

	@Test
	void test_exponentiation_with_negative_base_and_zero_exponent() {
	    assertEquals(1, calculator.evaluate("(-1)^0"));
	}

	@Test
	void test_exponentiation_with_positive_base_and_zero_exponent() {
	    assertEquals(1, calculator.evaluate("1^0"));
	}

	@Test
	void test_exponentiation_with_zero_base_and_positive_exponent() {
	    assertEquals(0.0, calculator.evaluate("0^1"));
	}

	@Test
	void test_exponentiation_with_negative_base_and_positive_exponent() {
	    assertEquals(-1, calculator.evaluate("(-1)^1"));
	}

	@Test
	void test_exponentiation_with_positive_base_and_positive_exponent() {
	    assertEquals(1, calculator.evaluate("1^1"));
	}
	
	
	// Test fractional exponentiation
	
	@Test
	void test_exponentiation_with_cube_root() {
	    assertEquals(2, calculator.evaluate("8^(1/3)"));
	}

	@Test
	void test_exponentiation_with_square_root() {
	    assertEquals(4, calculator.evaluate("16^(1/2)"));
	}

	@Test
	void test_exponentiation_with_fractional_exponent() {
	    assertEquals(22.627416, calculator.evaluate("8^(3/2)"));
	}

	@Test
	void test_exponentiation_with_fractional_exponent_cube_root() {
	    assertEquals(9, calculator.evaluate("27^(2/3)"));
	}

	@Test
	void test_exponentiation_with_fractional_exponent_negative_square_root() {
	    assertEquals(0.25, calculator.evaluate("16^(-0.5)"));
	}

	@Test
	void test_exponentiation_with_negative_fractional_exponent() {
	    assertEquals(2.0, calculator.evaluate("0.25^-0.5"));
	}

	@Test
	void test_exponentiation_with_fractional_exponent_square_root() {
	    assertEquals(0.5, calculator.evaluate("0.25^(1/2)"));
	}

	@Test
	void test_exponentiation_with_fractional_exponent_addition() {
	    assertEquals(2.828427, calculator.evaluate("2^(1/2+2/2)"), 0.000001);
	}

	@Test
	void test_exponentiation_with_fractional_exponent_addition_cube_root() {
	    assertEquals(8, calculator.evaluate("8^(2/3+1/3)"));
	}

	@Test
	void test_exponentiation_with_negative_base_and_fractional_exponent() {
	    assertEquals(-0.125, calculator.evaluate("-4^(-3/2)"));
	}
	
	@Test
	void test_exponentiation_with_integer_multiplication() {
	    assertEquals(8, calculator.evaluate("2^(3*1)"));
	}

	@Test
	void test_exponentiation_with_integer_subtraction() {
	    assertEquals(1, calculator.evaluate("1^(2-1)"));
	}

	@Test
	void test_exponentiation_with_fractional_exponent_multiplication() {
	    assertEquals(4.096, calculator.evaluate("1.6^(1.5*2)"), 0.000001);
	}

	@Test
	void test_exponentiation_with_fractional_exponent_multiplication_negation() {
	    assertEquals(0.68173, calculator.evaluate("0.6^(-3/2*-1/2)"), 0.000001);
	}

	@Test
	void test_exponentiation_with_a_long_decimal_exponent() {
	    assertEquals(1.19426, calculator.evaluate("2^0.256121"), 0.0001);
	}	
    
	@Test
	void test_exponentiation_with_a_negative_long_decimal_exponent() {
	    assertEquals(0.83733, calculator.evaluate("2^-0.256121"), 0.00001);
	}
    
    
	// Test multiple Operations with brackets and exponentiation
	
	@Test
	void test_expression_with_negative_exponent_within_parentheses() {
	    assertEquals(0.015625, calculator.evaluate("(2^(-3))^2"));
	}
	
	@Test
	void test_expression_with_exponentiation_within_multiple_parentheses_pairs() {
	    assertEquals(256, calculator.evaluate("((2^(2^2))^2)"));
	}
	
	@Test
	void test_large_expression() {
		assertEquals(47.5, calculator.evaluate("2 + 3 * (4^2) - 5 / 2"));
	}
	
	@Test
	void test_expression_with_large_expression_and_parentheses() {
		assertEquals(37.5, calculator.evaluate("((2 + 3) * (4^2) - 5) / 2"));
	}
	
	@Test
	void test_expression_with_multiple_parentheses_pairs() {
		assertEquals(5, calculator.evaluate("(((2 + 3)))"));
	}
	
	@Test
	void test_expression_with_nested_parentheses() {
		assertEquals(30, calculator.evaluate("(2 * (3 + (4 * (5 - 2))))"));
	}
	
	@Test
	void test_multiple_nested_parentheses() {
		assertEquals(47, calculator.evaluate("(2 + (3 * (4 + (5 - (6 / (7 - 8))))))"));
	}
	
	@Test
	void test_expression_with_exponentiation_within_parentheses() {
		assertEquals(64, calculator.evaluate("((2^3)^2)"));
	}
	
	@Test
	void test_large_expression_with_exponentitation_and_parentheses() {
		assertEquals(-0.134608, calculator.evaluate("(2 -- 3)^-0.6 * ((1/2)^0.5) /- 2"), 0.000001);
	}
	
	@Test
	void test_calculate_complicated_expression_with_decimal_numbers() {
	    assertEquals(11.08, calculator.evaluate("((2.1 - 0.1) / 0.2) + (1.2 * 0.9)"));
	}
	
	@Test
	void test_calculate_complicated_expression_with_fractions() {
	    assertEquals(1, calculator.evaluate("((1/2)^(-0.5)) / ((1/4)^(-0.25))"));
	}

	@Test
	void test_calculate_complicated_expression_with_decimal_numbers_2() {
	    assertEquals(0.731707, calculator.evaluate("((0.1 / 0.2) + (0.3 / 0.4)) / ((0.5 / 0.6) + (0.7 / 0.8))"), 0.000001);
	}

	@Test
	void test_calculate_complicated_expression_with_decimal_numbers_3() {
	    assertEquals(-0.09375, calculator.evaluate("(1.5 + (-2.25)) / ((3.5 - (4.5 / (5.5 + (-6.5)))))"), 0.000001);
	}

	@Test
	void test_calculate_complicated_expression_with_decimal_numbers_4() {
	    assertEquals(37.916666, calculator.evaluate("((1.25 / 0.25) * (2.5^2)) + (3.75 / (0.5 + (0.25^2)))"), 0.000001);
	}

	@Test
	void test_calculate_complicated_expression_with_decimal_numbers_5() {
	    assertEquals(2.492759, calculator.evaluate("((-1.5^(-0.5)) - (2.75 / 0.25)) / ((3.25 - (4.5^(-0.75))) + (-5.75 / 0.75))"), 0.000001);
	}

	@Test
	void test_calculate_complicated_expression_with_decimal_numbers_6() {
	    assertEquals(-3, calculator.evaluate("((-1.1 / 0.2) + (-2.2 / 0.4) - (3.3 / 0.6)) / ((4.4 / 0.8) + (5.5 / 1) - (6.6 / 1.2))"));
	}	
}

