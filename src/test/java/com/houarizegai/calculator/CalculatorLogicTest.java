package com.houarizegai.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.houarizegai.calculator.logic.CalculatorLogic;

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
            "25,5",
            "-3,NaN"
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
            "10,2.302585092994046",
            "-3,NaN"
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
     * Tests the factorial method with various inputs.
     *
     * @param number the number to be factorial
     * @param expectedResult the expected result of the factorial
     */
    @ParameterizedTest
    @CsvSource({
            "3,6",   // Factorial of 3 is 6
            "4,24",  // Factorial of 4 is 24
            "5,120", // Factorial of 5 is 120
            "6,720", // Factorial of 6 is 720
            "10,3628800", // Factorial of 10 is 3628800
            "-3,-6", // Factorial of -3 is -6
            //"2.3, exception", // Factorial of 2.3 is exception
    })
    void testFact(int number, int expectedResult) {
        assertEquals(expectedResult, calculatorLogic.factorial(number), 1e-8);
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
     * Tests the degrees method with various inputs in radians.
     *
     * @param number the angle in radians
     * @param expectedResult the expected result of the conversion
     */
    @ParameterizedTest
    @CsvSource({
            "1.5707963267948966,90",          // π/2 radians to 90 degrees
            "0.7853981633974483,45",          // π/4 radians to 45 degrees
            "0,0",                            // 0 radians to 0 degrees
            "-1.5707963267948966,-90",        // -π/2 radians to -90 degrees
            "3.141592653589793,180",          // π radians to 180 degrees
            "6.283185307179586,360",          // 2π radians to 360 degrees
            "0.5235987755982988,30",          // π/6 radians to 30 degrees
            "-3.141592653589793,-180",        // -π radians to -180 degrees
            "4.71238898038469,270",           // 3π/2 radians to 270 degrees
            "1e-10,5.729577951308232e-9"      // Tiny angle, precision check
    })
    void testDegrees(double number, double expectedResult) {
        assertEquals(expectedResult, calculatorLogic.degrees(number), 1e-8);
    }

     /**
     * Tests the degrees method with various inputs in radians.
     *
     * @param number the angle in radians
     * @param expectedResult the expected result of the conversion
     */
    @ParameterizedTest
    @CsvSource({
            "90,1.5707963267948966",          // 90 degrees to π/2 radians
            "45,0.7853981633974483",          // 45 degrees to π/4 radians
            "0,0",                            // 0 degrees to 0 radians
            "-90,-1.5707963267948966",        // -90 degrees to -π/2 radians
            "180,3.141592653589793",          // 180 degrees to π radians
            "360,6.283185307179586",          // 360 degrees to 2π radians
            "30,0.5235987755982988",          // 30 degrees to π/6 radians
            "-180,-3.141592653589793",        // -180 degrees to -π radians
            "270,4.71238898038469",           // 270 degrees to 3π/2 radians
            "1e-10,5.729577951308232e-9"      // Tiny angle, precision check
    })
    void testRadians(double number, double expectedResult) {
        assertEquals(expectedResult, calculatorLogic.radians(number), 1e-8);
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
