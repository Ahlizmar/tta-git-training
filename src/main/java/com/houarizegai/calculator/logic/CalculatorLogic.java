package com.houarizegai.calculator.logic;

public class CalculatorLogic {

    public double calculate(double firstNumber, double secondNumber, char operator) {
        switch (operator) {
            case '+':
                return firstNumber + secondNumber;
            case '-':
                return firstNumber - secondNumber;
            case '*':
                return firstNumber * secondNumber;
            case '/':
                return firstNumber / secondNumber;
            case '%':
                return firstNumber % secondNumber;
            case '^':
                return Math.pow(firstNumber, secondNumber);
            default:
                return secondNumber;
        }
    }

    public double sqrt(double number) {
        return Math.sqrt(number);
    }

    public double ln(double number) { // ln logic
        return Math.log(number);
    }
    public double PI(double number) { // PI logic
        return Math.PI * number;
    }
    public double log(double number) { // log logic
        return Math.log10(number);
    }

    public int factorial(int number) { // need exeption 
        int res = 1, i; 
        for (i = 2; i <= number; i++) 
            res *= i; 
        return res; 
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
}

