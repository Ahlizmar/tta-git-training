package com.houarizegai.calculator;

import com.houarizegai.calculator.logic.CalculatorLogic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for CalculatorLogic class.
 */
class CalculatorLogicTest {

    // Instance of CalculatorLogic to be tested.
    private CalculatorLogic calculatorLogic;

    /**
     * Initializes the CalculatorLogic instance before each test.
     */
    @BeforeEach
    void setUp() {
        calculatorLogic = new CalculatorLogic();
    }

    /**
     * Tests the calculate method with various arithmetic operations.
     *
     * @param firstNumber the first operand
     * @param secondNumber the second operand
     * @param operator the arithmetic operator
     * @param expectedResult the expected result of the calculation
     */
    @ParameterizedTest
    @CsvSource({
            "3,5,+,8",
            "2,8,-,-6",
            "44.5,10,*,445",
            "320,5,/,64",
            "3,5,%,3",
            "5,3,^,125"
    })
    void testCalculation(double firstNumber, double secondNumber, char operator, double expectedResult) {
        assertEquals(expectedResult, calculatorLogic.calculate(firstNumber, secondNumber, operator));
    }

    /**
     * Tests the sqrt method with various inputs.
     *
     * @param number the number to find the square root of
     * @param expectedResult the expected result of the square root
     */
    @ParameterizedTest
    @CsvSource({
            "9,3",
            "16,4",
            "25,5"
    })
    void testSqrt(double number, double expectedResult) {
        assertEquals(expectedResult, calculatorLogic.sqrt(number));
    }

    /**
     * Tests the log method with various inputs.
     *
     * @param number the number to find the logarithm of
     * @param expectedResult the expected result of the logarithm
     */
    @ParameterizedTest
    @CsvSource({
            "1,0",
            "2.718281828459045,1",  // Approximation of Math.E
            "10,2.302585092994046"
    })
    void testLog(double number, double expectedResult) {
        assertEquals(expectedResult, calculatorLogic.ln(number), 1e-8);
    }

    /**
     * Tests the PI method with various inputs.
     *
     * @param number the number to be multiplied by PI
     * @param expectedResult the expected result of the multiplication
     */
    @ParameterizedTest
    @CsvSource({
            "2,6.283185307179586"
    })
    void testPi(double number, double expectedResult) {
        assertEquals(expectedResult, calculatorLogic.PI(number), 1e-8);
    }

    /**
     * Tests the sin method with various inputs in degrees.
     *
     * @param number the angle in degrees
     * @param expectedResult the expected result of the sine calculation
     */
    @ParameterizedTest
    @CsvSource({
            "30,0.5",
            "45,0.7071067811865475",
            "90,1"
    })
    void testSin(double number, double expectedResult) {
        assertEquals(expectedResult, calculatorLogic.sin(number), 1e-8);
    }

    /**
     * Tests the cos method with various inputs in degrees.
     *
     * @param number the angle in degrees
     * @param expectedResult the expected result of the cosine calculation
     */
    @ParameterizedTest
    @CsvSource({
            "60,0.5",
            "45,0.7071067811865476",
            "0,1"
    })
    void testCos(double number, double expectedResult) {
        assertEquals(expectedResult, calculatorLogic.cos(number), 1e-8);
    }

    /**
     * Tests the tan method with various inputs in degrees.
     *
     * @param number the angle in degrees
     * @param expectedResult the expected result of the tangent calculation
     */
    @ParameterizedTest
    @CsvSource({
            "45,1",
            "30,0.5773502691896257",
            "60,1.7320508075688767"
    })
    void testTan(double number, double expectedResult) {
        assertEquals(expectedResult, calculatorLogic.tan(number), 1e-8);
    }
}
