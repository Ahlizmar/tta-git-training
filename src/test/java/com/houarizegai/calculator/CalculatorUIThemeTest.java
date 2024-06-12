package com.houarizegai.calculator;

import com.houarizegai.calculator.theme.properties.Theme;
import com.houarizegai.calculator.theme.ThemeLoader;
import com.houarizegai.calculator.ui.CalculatorUI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.lang.reflect.Method;

import static com.houarizegai.calculator.util.ColorUtil.hex2Color;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for Dark and Light themes in the CalculatorUI class.
 */
public class CalculatorUIThemeTest {

    private CalculatorUI calculatorUI;

    @BeforeEach
    public void setUp() {
        calculatorUI = new CalculatorUI();
    }

    /**
     * Test to verify the application of the Dark theme.
     *
     * @throws Exception if any reflection operation fails
     */
    @Test
    public void testDarkThemeApplication() throws Exception {
        // Load themes from the YAML configuration
        var themesMap = ThemeLoader.loadThemes();
        Theme darkTheme = themesMap.get("Dark");

        // Access and invoke the private applyTheme method using reflection
        applyTheme(darkTheme);

        // Assert the theme colors are correctly applied
        assertThemeColors(darkTheme);
    }

    /**
     * Test to verify the application of the Light theme.
     *
     * @throws Exception if any reflection operation fails
     */
    @Test
    public void testLightThemeApplication() throws Exception {
        // Load themes from the YAML configuration
        var themesMap = ThemeLoader.loadThemes();
        Theme lightTheme = themesMap.get("Light");

        // Access and invoke the private applyTheme method using reflection
        applyTheme(lightTheme);

        // Assert the theme colors are correctly applied
        assertThemeColors(lightTheme);
    }

    /**
     * Helper method to apply the theme using reflection.
     *
     * @param theme the theme to apply
     * @throws Exception if any reflection operation fails
     */
    private void applyTheme(Theme theme) throws Exception {
        Method applyThemeMethod = CalculatorUI.class.getDeclaredMethod("applyTheme", Theme.class);
        applyThemeMethod.setAccessible(true);
        applyThemeMethod.invoke(calculatorUI, theme);
    }

    /**
     * Helper method to assert the theme colors are correctly applied.
     *
     * @param theme the theme to assert
     */
    private void assertThemeColors(Theme theme) {
        JFrame window = getField(calculatorUI, "window");
        JComboBox<String> comboCalculatorType = getField(calculatorUI, "comboCalculatorType");
        JComboBox<String> comboTheme = getField(calculatorUI, "comboTheme");
        JTextField inputScreen = getField(calculatorUI, "inputScreen");
        JButton btn0 = getField(calculatorUI, "btn0");
        JButton btnEqual = getField(calculatorUI, "btnEqual");

        // Check application background color
        assertEquals(hex2Color(theme.getApplicationBackground()), window.getContentPane().getBackground());
        // Check theme selection text color
        assertEquals(hex2Color(theme.getThemeSelectionTextColor()), comboCalculatorType.getForeground());
        assertEquals(hex2Color(theme.getThemeSelectionTextColor()), comboTheme.getForeground());
        // Check display text color
        assertEquals(hex2Color(theme.getDisplayTextColor()), inputScreen.getForeground());
        // Check equal button text and background color
        assertEquals(hex2Color(theme.getBtnEqualTextColor()), btnEqual.getForeground());
        assertEquals(hex2Color(theme.getBtnEqualBackground()), btnEqual.getBackground());
        // Check numbers button background color
        assertEquals(hex2Color(theme.getNumbersBackground()), btn0.getBackground());
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
            var field = calculatorUI.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return (T) field.get(calculatorUI);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Failed to get field: " + fieldName, e);
        }
    }
}
