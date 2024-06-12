package com.houarizegai.calculator;

import com.houarizegai.calculator.theme.properties.Theme;
import com.houarizegai.calculator.theme.ThemeLoader;
import com.houarizegai.calculator.ui.CalculatorUI;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static com.houarizegai.calculator.util.ColorUtil.hex2Color;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorVioletThemeTest {
    @Test
    public void testVioletThemeApplication() throws Exception {
        // Load themes
        var themesMap = ThemeLoader.loadThemes();
        Theme violetTheme = themesMap.get("Violet");

        // Create Calculator UI instance
        CalculatorUI calculatorUI = new CalculatorUI();

        // Get components
        JFrame window = getField(calculatorUI, "window");
        JComboBox<String> comboCalculatorType = getField(calculatorUI, "comboCalculatorType");
        JComboBox<String> comboTheme = getField(calculatorUI, "comboTheme");
        JTextField inputScreen = getField(calculatorUI, "inputScreen");
        JButton btn0 = getField(calculatorUI, "btn0");
        JButton btnEqual = getField(calculatorUI, "btnEqual");

        // Apply Violet Theme using reflection to access the private method
        Method applyThemeMethod = CalculatorUI.class.getDeclaredMethod("applyTheme", Theme.class);
        ((Method) applyThemeMethod).setAccessible(true);  // Make the private method accessible
        applyThemeMethod.invoke(calculatorUI, violetTheme);

        // Assert the theme colors
        assertEquals(hex2Color(violetTheme.getApplicationBackground()), window.getContentPane().getBackground());
        assertEquals(hex2Color(violetTheme.getThemeSelectionTextColor()), comboCalculatorType.getForeground());
        assertEquals(hex2Color(violetTheme.getThemeSelectionTextColor()), comboTheme.getForeground());
        assertEquals(hex2Color(violetTheme.getDisplayTextColor()), inputScreen.getForeground());
        assertEquals(hex2Color(violetTheme.getBtnEqualTextColor()), btnEqual.getForeground());
        assertEquals(hex2Color(violetTheme.getBtnEqualBackground()), btnEqual.getBackground());
        assertEquals(hex2Color(violetTheme.getNumbersBackground()), btn0.getBackground());
    }

    /**
     * Helper method to get the value of a private field using reflection.
     *
     * @param calculatorUI The CalculatorUI instance.
     * @param fieldName The name of the field to be accessed.
     * @param <T> The type of the field.
     * @return The value of the field.
     */
    @SuppressWarnings("unchecked")
    private <T> T getField(CalculatorUI calculatorUI, String fieldName) {
        try {
            Field field = calculatorUI.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return (T) field.get(calculatorUI);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Failed to get field: " + fieldName, e);
        }
    }
}