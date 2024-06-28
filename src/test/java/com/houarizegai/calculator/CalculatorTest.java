package com.houarizegai.calculator;

import com.houarizegai.calculator.ui.CalculatorUI;
import org.junit.jupiter.api.*;
import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {
    private CalculatorUI calculatorUI;

    @BeforeEach
    public void setUp() {
        calculatorUI = new CalculatorUI(); // Initialize a new CalculatorUI instance before each test
        clearInputScreen(); // Optionally clear the input screen before each test
        setAddToDisplay(calculatorUI, true);
        setGo(calculatorUI, true);
    }

    private void clearInputScreen() {
        JTextField inputScreen = findInputScreen(calculatorUI);
        inputScreen.setText("0"); // Clear the input screen
    }
    
    @AfterEach
    public void tearDown() {
        calculatorUI = null;

    }

    @Test
    public void testUIInitialization() {
        JFrame window = findWindow(calculatorUI);
        assertNotNull(window);
        assertEquals("Calculator", window.getTitle()); // Assuming APPLICATION_TITLE is "Calculator"
        assertEquals(410, window.getWidth()); // Assuming WINDOW_WIDTH is 410
        assertEquals(600, window.getHeight()); // Assuming WINDOW_HEIGHT is 600
        assertFalse(window.isResizable());
        assertEquals(JFrame.EXIT_ON_CLOSE, window.getDefaultCloseOperation());
    }


    @Test
    public void testButtonCAction() {
        // Simulate typing a value
        JTextField inputScreen = findInputScreen(calculatorUI);
        inputScreen.setText("12345");

        // Simulate pressing the "C" button
        JButton buttonC = findButton(calculatorUI, "C");
        buttonC.doClick();

        // Verify the input screen is cleared and other related fields are reset
        assertEquals("0", inputScreen.getText());
        assertEquals(' ', getSelectedOperator(calculatorUI));
        assertEquals(0, getTypedValue(calculatorUI), 0.001);
    }

    @Test
    public void testButtonBackAction() {
        // Simulate typing a value
        JTextField inputScreen = findInputScreen(calculatorUI);
        inputScreen.setText("12345");
        assertEquals("12345", inputScreen.getText());

        // Simulate pressing the "Back" button
        JButton buttonBack = findButton(calculatorUI, "<-");
        buttonBack.doClick();

        // Verify the last character is removed
        assertEquals("1234", inputScreen.getText());

        // Simulate pressing the "Back" button until the screen is empty
        buttonBack.doClick();
        buttonBack.doClick();
        buttonBack.doClick();
        buttonBack.doClick();

        // Verify the input screen shows "0" after all characters are removed
        assertEquals("0", inputScreen.getText());
    }

    @Test
    public void testButtonModAction() {
        JTextField inputScreen = findInputScreen(calculatorUI);
        JButton buttonMod = findButton(calculatorUI, "%");
        // Simulate typing a value
        inputScreen.setText("10");

        // Simulate pressing the "/" button
        buttonMod.doClick();

        // Verify the selected operator is set to '%'
        assertEquals('%', getSelectedOperator(calculatorUI));

        // Verify the input screen displays the correct calculated value (assuming initial typedValue was 0)
        assertEquals("10", inputScreen.getText());

        // Reset the state for further operations
        setGo(calculatorUI, true);
        setAddToDisplay(calculatorUI, true);

        // Simulate entering another value and pressing the "/" button again
        inputScreen.setText("5");
        buttonMod.doClick();

        // Verify the final output after division operation (100 / 5 = 20)
        assertEquals("0", inputScreen.getText());
    }

    @Test
    public void testButtonDivAction() {
        JTextField inputScreen = findInputScreen(calculatorUI);
        JButton buttonDiv = findButton(calculatorUI, "/");
        JButton buttonEqual = findButton(calculatorUI, "=");

        // Test with valid input producing integer result
        inputScreen.setText("100");
        buttonDiv.doClick();
        assertEquals("100", inputScreen.getText());  // Initial input displayed as-is

        setGo(calculatorUI, true);
        setAddToDisplay(calculatorUI, true);

        inputScreen.setText("5");
        buttonEqual.doClick();
        assertEquals("20", inputScreen.getText());  // 100 / 5 = 20, should be displayed as integer
    }

    @Test
    public void testButton7Action() {
        JButton button7 = findButton(calculatorUI, "7");
        JTextField inputScreen = findInputScreen(calculatorUI);
        // Simulate pressing the "5" button
        button7.doClick();
    
        // Verify the input screen displays "5" as expected
        assertEquals("7", inputScreen.getText());
        assertTrue(isAddToDisplay(calculatorUI));
        assertTrue(isGo(calculatorUI));
    
        // Simulate pressing the "5" button again (addToDisplay is true)
        button7.doClick();
    
        // Verify the input screen displays "55" after appending another "5"
        assertEquals("77", inputScreen.getText());
        assertTrue(isAddToDisplay(calculatorUI));
        assertTrue(isGo(calculatorUI));

    }

    @Test
    public void testButton8Action() { 
        JButton button8 = findButton(calculatorUI, "8");
        JTextField inputScreen = findInputScreen(calculatorUI);
        
        // Simulate pressing the "8" button
        button8.doClick();
    
        // Verify the input screen displays "8" as expected
        assertEquals("8", inputScreen.getText());
        assertTrue(isGo(calculatorUI));
    
        // Simulate pressing the "8" button again (addToDisplay is true)
        button8.doClick();
    
        // Verify the input screen displays "88" after appending another "8"
        assertEquals("88", inputScreen.getText());
        assertTrue(isGo(calculatorUI));
    }

    @Test
    public void testButton9Action() {
        // Simulate pressing the "9" button
        JButton button9 = findButton(calculatorUI, "9");
        JTextField inputScreen = findInputScreen(calculatorUI);
        
        button9.doClick();
    
        // Verify the input screen displays "9" as expected
        assertEquals("9", inputScreen.getText());
        assertTrue(isAddToDisplay(calculatorUI));
        assertTrue(isGo(calculatorUI));
    
        // Simulate pressing the "9" button again (addToDisplay is true)
        button9.doClick();
    
        // Verify the input screen displays "99" after appending another "9"
        assertEquals("99", inputScreen.getText());
        assertTrue(isAddToDisplay(calculatorUI));
        assertTrue(isGo(calculatorUI));
    }

    @Test
    public void testButtonMulAction() {
        JTextField inputScreen = findInputScreen(calculatorUI);
        JButton buttonMul = findButton(calculatorUI, "*");
        JButton buttonEqual = findButton(calculatorUI, "=");

        // Test with valid input producing integer result
        inputScreen.setText("10");
        buttonMul.doClick();
        assertEquals("10", inputScreen.getText());  // Initial input displayed as-is

        setGo(calculatorUI, true);

        inputScreen.setText("5");
        buttonEqual.doClick();
        assertEquals("50", inputScreen.getText());  // 100 / 5 = 20, should be displayed as integer
    }

    @Test
    public void testButton4Action() {
        JButton button4 = findButton(calculatorUI, "4");
        JTextField inputScreen = findInputScreen(calculatorUI);
       
        // Simulate pressing the "4" button
        button4.doClick();
    
        // Verify the input screen displays "4" as expected
        assertEquals("4", inputScreen.getText());
        assertTrue(isAddToDisplay(calculatorUI));
        assertTrue(isGo(calculatorUI));
    
        // Simulate pressing the "4" button again (addToDisplay is true)
        button4.doClick();
    
        // Verify the input screen displays "44" after appending another "4"
        assertEquals("44", inputScreen.getText());
        assertTrue(isAddToDisplay(calculatorUI));
        assertTrue(isGo(calculatorUI));
    }

    @Test
    public void testButton5Action() {
        JButton button5 = findButton(calculatorUI, "5");
        JTextField inputScreen = findInputScreen(calculatorUI);
        
        // Simulate pressing the "5" button
        button5.doClick();
    
        // Verify the input screen displays "5" as expected
        assertEquals("5", inputScreen.getText());
        assertTrue(isAddToDisplay(calculatorUI));
        assertTrue(isGo(calculatorUI));
    
        // Simulate pressing the "5" button again (addToDisplay is true)
        button5.doClick();
    
        // Verify the input screen displays "55" after appending another "5"
        assertEquals("55", inputScreen.getText());
        assertTrue(isAddToDisplay(calculatorUI));
        assertTrue(isGo(calculatorUI));
    }

    @Test
    public void testButton6Action() {
        JButton button6 = findButton(calculatorUI, "6");
        JTextField inputScreen = findInputScreen(calculatorUI);
        
        // Simulate pressing the "6" button
        button6.doClick();
    
        // Verify the input screen displays "6" as expected
        assertEquals("6", inputScreen.getText());
        assertTrue(isAddToDisplay(calculatorUI));
        assertTrue(isGo(calculatorUI));
    
        // Simulate pressing the "6" button again (addToDisplay is true)
        button6.doClick();
    
        // Verify the input screen displays "66" after appending another "6"
        assertEquals("66", inputScreen.getText());
        assertTrue(isAddToDisplay(calculatorUI));
        assertTrue(isGo(calculatorUI));
    }

    @Test
    public void testButtonSubAction() {
        JTextField inputScreen = findInputScreen(calculatorUI);
        JButton buttonSub = findButton(calculatorUI, "-");
        
        // Simulate typing a valid value
        inputScreen.setText("100");
    
        // Simulate pressing the "-" button for the first operation
        buttonSub.doClick();
    
        // Verify the selected operator is set to '-'
        assertEquals('-', getSelectedOperator(calculatorUI));
    
        // Verify the input screen displays the correct calculated value after the first operation (assuming initial typedValue was 0)
        assertEquals("100", inputScreen.getText());
    
        // Reset the state for subsequent operations
        setGo(calculatorUI, true);
        setAddToDisplay(calculatorUI, true);
    
        // Simulate entering another valid value and pressing the "-" button again (second operation)
        inputScreen.setText("10");
        buttonSub.doClick();
    
        // Verify the input screen displays the correct result after the second operation (100 - 10 = 90)
        assertEquals("90", inputScreen.getText());
    
        // Simulate another scenario where go is false (selectedOperator should be set without calculation)
        setGo(calculatorUI, false);
    
        // Simulate pressing the "-" button again without changing input
        buttonSub.doClick();
    
        // Verify that selectedOperator is '-' and input screen remains unchanged
        assertEquals('-', getSelectedOperator(calculatorUI));
        assertEquals("90", inputScreen.getText());
    }
    @Test
    public void testButton0Action() {
        // Simulate pressing the "0" button
        JButton button0 = findButton(calculatorUI, "0");
        button0.doClick();

        // Verify the input screen displays "0" as expected
        JTextField inputScreen = findInputScreen(calculatorUI);
        assertEquals("0", inputScreen.getText());
        assertTrue(isAddToDisplay(calculatorUI));
        assertTrue(isGo(calculatorUI));
    }

    @Test
    public void testButton1Action() {
        // Simulate pressing the "1" button
        JButton button1 = findButton(calculatorUI, "1");
        button1.doClick();

        // Verify the input screen displays "1" as expected
        JTextField inputScreen = findInputScreen(calculatorUI);
        assertEquals("1", inputScreen.getText());
        assertTrue(isAddToDisplay(calculatorUI));
        assertTrue(isGo(calculatorUI));

        // Simulate pressing the "1" button again (addToDisplay is true)
        button1.doClick();

        // Verify the input screen displays "11" after appending another "1"
        assertEquals("11", inputScreen.getText());
        assertTrue(isAddToDisplay(calculatorUI));
        assertTrue(isGo(calculatorUI));
    }

    @Test
    public void testButton2Action() {
        // Simulate pressing the "2" button
        JButton button2 = findButton(calculatorUI, "2");
        button2.doClick();

        // Verify the input screen displays "2" as expected
        JTextField inputScreen = findInputScreen(calculatorUI);
        assertEquals("2", inputScreen.getText());
        assertTrue(isAddToDisplay(calculatorUI));
        assertTrue(isGo(calculatorUI));

        // Simulate pressing the "2" button again (addToDisplay is true)
        button2.doClick();

        // Verify the input screen displays "22" after appending another "2"
        assertEquals("22", inputScreen.getText());
        assertTrue(isAddToDisplay(calculatorUI));
        assertTrue(isGo(calculatorUI));
    }

    @Test
    public void testButton3Action() {
        // Simulate pressing the "3" button
        JButton button3 = findButton(calculatorUI, "3");
        button3.doClick();

        // Verify the input screen displays "3" as expected
        JTextField inputScreen = findInputScreen(calculatorUI);
        assertEquals("3", inputScreen.getText());
        assertTrue(isAddToDisplay(calculatorUI));
        assertTrue(isGo(calculatorUI));

        // Simulate pressing the "3" button again (addToDisplay is true)
        button3.doClick();

        // Verify the input screen displays "33" after appending another "3"
        assertEquals("33", inputScreen.getText());
        assertTrue(isAddToDisplay(calculatorUI));
        assertTrue(isGo(calculatorUI));
    }

    @Test
    public void testButtonAddAction() {
        // Simulate typing a valid value
        JTextField inputScreen = findInputScreen(calculatorUI);
        inputScreen.setText("50");

        // Simulate pressing the "+" button for the first operation
        JButton buttonAdd = findButton(calculatorUI, "+");
        buttonAdd.doClick();

        // Verify the selected operator is set to '+'
        //assertEquals('+', getSelectedOperator(calculatorUI));

        // Verify the input screen displays the correct calculated value after the first operation (assuming initial typedValue was 0)
        assertEquals("50", inputScreen.getText());

        // Reset the state for subsequent operations
        setGo(calculatorUI, true);
        setAddToDisplay(calculatorUI, true);

        // Simulate entering another valid value and pressing the "+" button again (second operation)
        inputScreen.setText("25");
        buttonAdd.doClick();

        // Verify the input screen displays the correct result after the second operation (50 + 25 = 75)
        assertEquals("75", inputScreen.getText());

        // Simulate another scenario where go is false (selectedOperator should be set without calculation)
        setGo(calculatorUI, false);

        // Simulate pressing the "+" button again without changing input
        buttonAdd.doClick();

        // Verify that selectedOperator is '+' and input screen remains unchanged
        assertEquals('+', getSelectedOperator(calculatorUI));
        assertEquals("75", inputScreen.getText());
    }
    
    @Test
    public void testButtonDotAction() {
        // Simulate pressing the "." button
        JButton buttonDot = findButton(calculatorUI, ".");
        buttonDot.doClick();

        // Verify the input screen displays "0." as expected
        JTextField inputScreen = findInputScreen(calculatorUI);
        assertEquals("0.", inputScreen.getText());
        assertTrue(isAddToDisplay(calculatorUI));
        assertTrue(isGo(calculatorUI));

        // Simulate pressing the "." button again (addToDisplay is true)
        buttonDot.doClick();

        // Verify the input screen remains unchanged after pressing the "." button again
        assertEquals("0.", inputScreen.getText());
        assertTrue(isAddToDisplay(calculatorUI));
        assertTrue(isGo(calculatorUI));
    }

    @Test
    public void testButtonEqualAction() {
        // Simulate entering numbers and an operator (for example, 10 + 5)
        JButton button1 = findButton(calculatorUI, "1");
        JButton button0 = findButton(calculatorUI, "0");
        JButton buttonPlus = findButton(calculatorUI, "+");
        JButton button5 = findButton(calculatorUI, "5");
        JTextField inputScreen1 = findInputScreen(calculatorUI);
        assertEquals("0", inputScreen1.getText());     
        button1.doClick();
        button0.doClick();
        assertEquals("10", inputScreen1.getText());
        buttonPlus.doClick();
        button5.doClick();

        // Simulate pressing the "=" button
        JButton buttonEqual = findButton(calculatorUI, "=");
        buttonEqual.doClick();

        // Verify the input screen displays the correct result (15) after calculation
        JTextField inputScreen = findInputScreen(calculatorUI);
        assertEquals("15", inputScreen.getText());

        // Verify state after clicking "=" button
        assertFalse(isAddToDisplay(calculatorUI));
        assertEquals('=', getSelectedOperator(calculatorUI));
    }

    @Test
    public void testButtonRootAction() {
        // Simulate typing a valid value
        JTextField inputScreen = findInputScreen(calculatorUI);
        inputScreen.setText("100");

        // Simulate pressing the "sqrt" button
        JButton buttonRoot = findButton(calculatorUI, "sqrt");
        buttonRoot.doClick();
        //assertEquals('sqrt', getSelectedOperator(calculatorUI));

        // Verify the input screen displays the correct calculated value (sqrt(100) = 10)
        assertEquals("10", inputScreen.getText());
    }

    @Test
    public void testButtonPowAction() {
        JTextField inputScreen = findInputScreen(calculatorUI);
        JButton buttonPow = findButton(calculatorUI, "x^2");
        
        // Simulate typing a valid value
        inputScreen.setText("10");

        // Simulate pressing the "x^2" button
        buttonPow.doClick();
        //assertEquals('x^2', getSelectedOperator(calculatorUI));

        // Verify the input screen displays the correct calculated value (10 * 10 = 100)
        assertEquals("100", inputScreen.getText());
    }

    @Test
    public void testButtonLnAction() {
        JTextField inputScreen = findInputScreen(calculatorUI);
        JButton buttonLn = findButton(calculatorUI, "ln");

        // Test with a valid input
        inputScreen.setText("10");
        buttonLn.doClick();
        assertEquals("2.302585092994046", inputScreen.getText());

        // Test with a value that causes a domain error
        inputScreen.setText("-1");
        buttonLn.doClick();
        assertEquals("Domain error", inputScreen.getText());

        // Test with a single value value 
        inputScreen.setText("1");
        buttonLn.doClick();
        assertEquals("0", inputScreen.getText());
    }

    @Test
    public void testButtonLogAction() {
        JTextField inputScreen = findInputScreen(calculatorUI);
        JButton buttonLog = findButton(calculatorUI, "log");

        // Test with a valid input
        inputScreen.setText("10");
        buttonLog.doClick();
        assertEquals("1", inputScreen.getText());

        // Test with a value that causes a domain error
        inputScreen.setText("-1");
        buttonLog.doClick();
        assertEquals("Domain error", inputScreen.getText());
    }

    @Test
    public void testButtonPiAction() {
        JButton buttonPi = findButton(calculatorUI, "π");
        JTextField inputScreen = findInputScreen(calculatorUI);
        // Simulate pressing the "π" button
        inputScreen.setText("1");
        buttonPi.doClick();

        // Verify the input screen displays "π" as expected
        assertEquals("3.141592653589793", inputScreen.getText());
        assertTrue(isGo(calculatorUI));
    }


    // Helper methods to find components and access private fields
    private void selectCalculatorType(String type) {
        JComboBox<String> comboCalculatorType = findComboBox(calculatorUI, "Calculator type");
        comboCalculatorType.setSelectedItem(type);
    }
    private JComboBox<String> findComboBox(CalculatorUI calculatorUI, String label) {
        for (Component component : findWindow(calculatorUI).getContentPane().getComponents()) {
            if (component instanceof JComboBox && ((JComboBox<?>) component).getSelectedItem().equals(label)) {
                return (JComboBox<String>) component;
            }
        }
        return null;
    }
    private void setGo(CalculatorUI ui, boolean value) {
        try {
            Field field = CalculatorUI.class.getDeclaredField("go");
            field.setAccessible(true);
            field.set(ui, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private boolean isGo(CalculatorUI ui) {
        try {
            Field field = CalculatorUI.class.getDeclaredField("go");
            field.setAccessible(true);
            return (boolean) field.get(ui);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void setAddToDisplay(CalculatorUI ui, boolean value) {
        try {
            Field field = CalculatorUI.class.getDeclaredField("addToDisplay");
            field.setAccessible(true);
            field.set(ui, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isAddToDisplay(CalculatorUI ui) {
        try {
            Field field = CalculatorUI.class.getDeclaredField("addToDisplay");
            field.setAccessible(true);
            return (boolean) field.get(ui);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private JFrame findWindow(CalculatorUI ui) {
        for (Window window : Window.getWindows()) {
            if (window instanceof JFrame && ((JFrame) window).getTitle().equals("Calculator")) {
                return (JFrame) window;
            }
        }
        return null;
    }

    private JTextField findInputScreen(CalculatorUI ui) {
        for (Component component : findWindow(ui).getContentPane().getComponents()) {
            if (component instanceof JTextField) {
                return (JTextField) component;
            }
        }
        return null;
    }

    private JButton findButton(CalculatorUI ui, String text) {
        for (Component component : findWindow(ui).getContentPane().getComponents()) {
            if (component instanceof JButton && ((JButton) component).getText().equals(text)) {
                return (JButton) component;
            }
        }
        return null;
    }

    private char getSelectedOperator(CalculatorUI ui) {
        try {
            Field field = CalculatorUI.class.getDeclaredField("selectedOperator");
            field.setAccessible(true);
            return (char) field.get(ui);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private double getTypedValue(CalculatorUI ui) {
        try {
            Field field = CalculatorUI.class.getDeclaredField("typedValue");
            field.setAccessible(true);
            return (double) field.get(ui);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
