package com.houarizegai.calculator.logic;

public class CalculatorLogic {

    public double calculate(double firstNumber, double secondNumber, char operator) {
        try{
            switch (operator){ 
                case '+':
                    return firstNumber + secondNumber;
                case '-':
                    return firstNumber - secondNumber;
                case '*':
                    return firstNumber * secondNumber;
                case '/':
                    if (secondNumber == 0) {
                        throw new ArithmeticException("Cannot divide by 0");
                    }
                    return firstNumber / secondNumber;
                case '%':
                    return firstNumber % secondNumber;
                case '^':
                    if (secondNumber == 0 && firstNumber == 0) {
                        throw new ArithmeticException("Cannot divide by 0");
                    }
                    return Math.pow(firstNumber, secondNumber);
                default:
                    return secondNumber;
            }
        }catch (ArithmeticException e) {
            // Handle error (return a default value, rethrow, etc.)
            return Double.NaN; // Return NaN to indicate an error in calculation
        }
    }

    public double sqrt(double number) {
        try{
            if (number < 0) {
                throw new ArithmeticException("Cannot calculate square root of a negative number");
            }
            return Math.sqrt(number);
        }catch (ArithmeticException e) {
            // Handle error (return a default value, rethrow, etc.)
            return Double.NaN; // Return NaN to indicate an error in calculation
        }
    }

    public double PI(double number) { // PI logic
        return Math.PI * number;
    }
    
    public double ln(double number) { // ln logic
        try{
            if (number < 0) {
                throw new ArithmeticException("Cannot calculate ln of a negative number");
            }
            return Math.log(number);
        }catch (ArithmeticException e) {
            // Handle error (return a default value, rethrow, etc.)
            return Double.NaN; // Return NaN to indicate an error in calculation
        }
    }

    public double log(double number) { // log logic
        try{
            if (number < 0) {
                throw new ArithmeticException("Cannot calculate log of a negative number");
            }
            return Math.log10(number);
        }catch (ArithmeticException e) {
            // Handle error (return a default value, rethrow, etc.)
            return Double.NaN; // Return NaN to indicate an error in calculation
        }
    }

    public int factorial(int number) { 
        int res = 1; 
        
        if (number >= 0) {
            for (int i = 2; i <= number; i++) {
                res *= i;
            }
            System.out.println(res);
            return res;
        } else {
            for (int i = -2; i >= number; i--) {
                res *= i;
                System.out.println(i + " " + res);
            }
            System.out.println(- Math.abs(res));
            return - Math.abs(res);
        }
        
    } 

    // Method to calculate sine of a number (in degrees)
    // Added sine function
    public double sin(double number) {
        return Math.sin(Math.toRadians(number)); // Convert degrees to radians
    }

    // Added cosine function
    public double cos(double number) {
        return Math.cos(Math.toRadians(number)); // Convert degrees to radians
    }

    // Method to calculate tangent of a number (in degrees)
    public double tan(double number) {
        return Math.tan(Math.toRadians(number)); // Convert degrees to radians
    }
    
    public double degrees(double number) {// convert radian to degree
        return Math.toDegrees(number);
    }

    public double radians(double number) { // convert degree to radian
        return Math.toRadians(number);
    }

}

