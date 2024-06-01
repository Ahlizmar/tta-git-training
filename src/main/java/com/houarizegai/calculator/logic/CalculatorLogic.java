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

    public double log(double number) {
        return Math.log(number);
    }
}

