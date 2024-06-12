package com.houarizegai.calculator.ui;

import com.houarizegai.calculator.theme.properties.Theme;

import javax.swing.*;

import static com.houarizegai.calculator.util.ColorUtil.hex2Color;

public class ThemeApplier {

    public static void applyTheme(JFrame window, JComboBox<String> comboCalculatorType, JComboBox<String> comboTheme,
                                  JTextField inputScreen, JButton btn0, JButton btn1, JButton btn2, JButton btn3,
                                  JButton btn4, JButton btn5, JButton btn6, JButton btn7, JButton btn8, JButton btn9,
                                  JButton btnPoint, JButton btnC, JButton btnBack, JButton btnMod, JButton btnDiv,
                                  JButton btnMul, JButton btnSub, JButton btnAdd, JButton btnRoot, JButton btnLog,
                                  JButton btnPower, JButton btnEqual, JButton btnPi, Theme theme) {
        // Set application background color
        window.getContentPane().setBackground(hex2Color(theme.getApplicationBackground()));

        // Set text colors for buttons
        JButton[] buttons = {btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnPoint, btnC, btnBack, btnMod,
                btnDiv, btnMul, btnSub, btnAdd, btnRoot, btnLog, btnPower, btnPi};
        for (JButton button : buttons) {
            button.setForeground(hex2Color(theme.getButtonTextColor())); // Apply button text color
        }

        // Set text color for the equal button
        btnEqual.setForeground(hex2Color(theme.getBtnEqualTextColor())); // Apply equal button text color

        // Set background colors for combo boxes
        comboCalculatorType.setBackground(hex2Color(theme.getApplicationBackground()));
        comboTheme.setBackground(hex2Color(theme.getApplicationBackground()));

        // Set text colors for theme selection combo boxes
        comboCalculatorType.setForeground(hex2Color(theme.getThemeSelectionTextColor())); // Apply theme selection text color
        comboTheme.setForeground(hex2Color(theme.getThemeSelectionTextColor())); // Apply theme selection text color

        // Set background color for the input screen
        inputScreen.setBackground(hex2Color(theme.getApplicationBackground()));

        // Set text color for the input screen
        inputScreen.setForeground(hex2Color(theme.getDisplayTextColor())); // Apply display text color

        // Set background colors for number buttons
        for (JButton button : buttons) {
            button.setBackground(hex2Color(theme.getNumbersBackground())); // Apply number button background color
        }

        // Set background colors for operator buttons
        JButton[] operatorButtons = {btnC, btnBack, btnMod, btnDiv, btnMul, btnSub, btnAdd, btnRoot, btnLog, btnPower, btnPi};
        for (JButton button : operatorButtons) {
            button.setBackground(hex2Color(theme.getOperatorBackground())); // Apply operator button background color
        }

        // Set background color for the equal button
        btnEqual.setBackground(hex2Color(theme.getBtnEqualBackground())); // Apply equal button background color

        // Apply selection background and text color for combo boxes
        UIManager.put("ComboBox.selectionBackground", hex2Color(theme.getSelectionBackground())); // Apply combo box selection background color
        UIManager.put("ComboBox.selectionForeground", hex2Color(theme.getSelectionTextColor())); // Apply combo box selection text color
    }
}
