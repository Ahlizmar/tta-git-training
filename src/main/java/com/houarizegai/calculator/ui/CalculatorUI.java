package com.houarizegai.calculator.ui;

import com.houarizegai.calculator.theme.properties.Theme;
import com.houarizegai.calculator.logic.CalculatorLogic;
import com.houarizegai.calculator.theme.ThemeLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.Map;
import java.util.regex.Pattern;

public class CalculatorUI {

    private static final String FONT_NAME = "Roboto"; // Changed to a modern font for a more contemporary look
    private static final String DOUBLE_OR_NUMBER_REGEX = "([-]?\\d+[.]\\d*)|(\\d+)|(-\\d+)";
    private static final String APPLICATION_TITLE = "Calculator";
    private static final int WINDOW_WIDTH = 410;
    private static final int WINDOW_HEIGHT = 600;
    private static final int BUTTON_WIDTH = 70; // Updated button width for consistent design
    private static final int BUTTON_HEIGHT = 71; // Updated button height for consistent design
    private static final int EQUAL_BUTTON_WIDTH = 160; // Updated equal button width to make it more prominent
    private static final int MARGIN_X = (WINDOW_WIDTH - (BUTTON_WIDTH * 4 + 3 * 20)) / 2; // Adjusted margin to center elements
    private static final int MARGIN_Y = 60;

    private final JFrame window;
    private JComboBox<String> comboCalculatorType;
    private JComboBox<String> comboTheme;
    private JTextField inputScreen;
    private JButton btnC;
    private JButton btnBack;
    private JButton btnMod;
    private JButton btnDiv;
    private JButton btnMul;
    private JButton btnSub;
    private JButton btnAdd;
    private JButton btn0;
    private JButton btn1;
    private JButton btn2;
    private JButton btn3;
    private JButton btn4;
    private JButton btn5;
    private JButton btn6;
    private JButton btn7;
    private JButton btn8;
    private JButton btn9;
    private JButton btnPoint;
    private JButton btnEqual;
    private JButton btnRoot;
    private JButton btnPower;
    private JButton btnLn; // changed natural logarithm button declaration
    private JButton btnLog; // Added log button declaration
    private JButton btnPi; // Added Pi button declaration
    private JButton btnFact; // Added factorial button declaration
    private JButton btnSin; // Added sine button declaration
    private JButton btnCos; // Added cosine button declaration
    private JButton btnTan; // Added tangent button declaration
    private JButton btnDeg; // Added degrees button declaration
    private JButton btnRad; // Added radians button declaration

    private char selectedOperator = ' ';
    private boolean go = true; // For calculate with Opt != (=)
    private boolean addToDisplay = true; // Connect numbers in display
    private double typedValue = 0;

    private final Map<String, Theme> themesMap;
    private final CalculatorLogic logic;

    public CalculatorUI() {
        logic = new CalculatorLogic();

        themesMap = ThemeLoader.loadThemes();
        window = new JFrame(APPLICATION_TITLE);

        // Adjusted window size to accommodate new button dimensions
        window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT); // Use WINDOW_WIDTH and WINDOW_HEIGHT for standard mode
        window.setLocationRelativeTo(null);

        // Updated columns and rows to fit new button dimensions
        int[] columns = {MARGIN_X, MARGIN_X + 90, MARGIN_X + 90 * 2, MARGIN_X + 90 * 3, MARGIN_X + 90 * 4, MARGIN_X + 90 * 5};
        int[] rows = {MARGIN_Y, MARGIN_Y + 100, MARGIN_Y + 100 + 80, MARGIN_Y + 100 + 80 * 2, MARGIN_Y + 100 + 80 * 3, MARGIN_Y + 100 + 80 * 4};

        initInputScreen(columns, rows);
        initButtons(columns, rows);
        initCalculatorTypeSelector();
        initThemeSelector();

        window.setLayout(null);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }

    private void initThemeSelector() {
        // Ordered themes with Light first and Violet last for better UX
        String[] orderedThemes = {"Light", "Dark", "Violet"};
        comboTheme = createComboBox(orderedThemes, 230, 30, "Theme");
        comboTheme.addItemListener(event -> {
            if (event.getStateChange() != ItemEvent.SELECTED)
                return;

            String selectedTheme = (String) event.getItem();
            Theme selectedThemeObject = themesMap.get(selectedTheme);
            applyTheme(selectedThemeObject);
        });

        // Apply the first theme by default (Light theme) for initial user experience
        Theme defaultTheme = themesMap.get("Light");
        applyTheme(defaultTheme);
    }

    private void applyTheme(Theme theme) {
        ThemeApplier.applyTheme(window, comboCalculatorType, comboTheme, inputScreen, btn0, btn1, btn2, btn3,
                btn4, btn5, btn6, btn7, btn8, btn9, btnPoint, btnC, btnBack, btnMod, btnDiv, btnMul,
                btnSub, btnAdd, btnRoot, btnLn, btnLog, btnPower, btnEqual, btnPi, theme);
    }

    private void initInputScreen(int[] columns, int[] rows) {
        inputScreen = new JTextField("0");
        // Adjusted input screen width for better alignment with new button dimensions
        inputScreen.setBounds(columns[0], rows[0], 350, 70);
        inputScreen.setEditable(false);
        inputScreen.setBackground(Color.WHITE);
        inputScreen.setFont(new Font(FONT_NAME, Font.PLAIN, 36)); // Increased size and made bold for readability
        inputScreen.setHorizontalAlignment(JTextField.RIGHT); // Align text to the right for a standard calculator look
        window.add(inputScreen);
    }

    private void initCalculatorTypeSelector() {
        comboCalculatorType = createComboBox(new String[]{"Standard", "Scientific"}, 50, 30, "Calculator type");
        comboCalculatorType.addItemListener(event -> {
            if (event.getStateChange() != ItemEvent.SELECTED)
                return;

            String selectedItem = (String) event.getItem();
            switch (selectedItem) {
                case "Standard":
                    window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT); // Keep the original window size for Standard mode
                    inputScreen.setBounds(MARGIN_X, MARGIN_Y, 350, 70); // Adjust inputScreen size for Standard mode
                    btnRoot.setVisible(false);
                    btnPower.setVisible(false);
                    btnLn.setVisible(false);
                    btnLog.setVisible(false); // Hide log button in Standard mode
                    btnPi.setVisible(false); // Hide Pi button in Standard mode
                    btnFact.setVisible(false);
                    btnSin.setVisible(false); // Hide sine button in Standard mode
                    btnCos.setVisible(false); // Hide cosine button in Standard mode
                    btnTan.setVisible(false); // Hide tangent button in Standard mode
                    btnDeg.setVisible(false);// Hide degrees button in Standard mode
                    btnDeg.setVisible(false);// Hide radians button in Standard mode
                    break;
                case "Scientific":
                    window.setSize(580, WINDOW_HEIGHT); // Adjust the width and keep the height the same for Scientific mode
                    inputScreen.setBounds(MARGIN_X, MARGIN_Y, 510, 70); // Adjust inputScreen size for Scientific mode
                    btnRoot.setVisible(true);
                    btnPower.setVisible(true);
                    btnLn.setVisible(true);
                    btnLog.setVisible(true); // Show log button in Scientific mode
                    btnPi.setVisible(true); // Show Pi button in Scientific mode
                    btnFact.setVisible(true); // Show factorial button in Scientific mode
                    btnSin.setVisible(true); // Show sine button in Scientific mode
                    btnCos.setVisible(true); // Show cosine button in Scientific mode
                    btnTan.setVisible(true); // Show tangent button in Scientific mode
                    btnDeg.setVisible(true); // Show degrees button in Scientific mode
                    btnRad.setVisible(true); // Show radians button in Scientific mode
                    break;
            }
        });
    }

    private void initButtons(int[] columns, int[] rows) {
        btnC = createButton("C", columns[0], rows[1]);
        btnC.addActionListener(event -> {
            inputScreen.setText("0");
            selectedOperator = ' ';
            typedValue = 0;
        });

        btnBack = createButton("<-", columns[1], rows[1]);
        btnBack.addActionListener(event -> {
            String str = inputScreen.getText();
            StringBuilder str2 = new StringBuilder();
            for (int i = 0; i < (str.length() - 1); i++) {
                str2.append(str.charAt(i));
            }
            if (str2.toString().equals("")) {
                inputScreen.setText("0");
            } else {
                inputScreen.setText(str2.toString());
            }
        });

        btnMod = createButton("%", columns[2], rows[1]);
        btnMod.addActionListener(event -> {
            if (!Pattern.matches(DOUBLE_OR_NUMBER_REGEX, inputScreen.getText()) || !go)
                return;

            typedValue = logic.calculate(typedValue, Double.parseDouble(inputScreen.getText()), selectedOperator);
            if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(typedValue))) {
                inputScreen.setText(String.valueOf((int) typedValue));
            } else {
                inputScreen.setText(String.valueOf(typedValue));
            }
            selectedOperator = '%';
            go = false;
            addToDisplay = false;
        });

        btnDiv = createButton("/", columns[3], rows[1]);
        btnDiv.addActionListener(event -> {
            if (!Pattern.matches(DOUBLE_OR_NUMBER_REGEX, inputScreen.getText()))
                return;

            if (go) {
                typedValue = logic.calculate(typedValue, Double.parseDouble(inputScreen.getText()), selectedOperator);
                if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(typedValue))) {
                    inputScreen.setText(String.valueOf((int) typedValue));
                } else {
                    inputScreen.setText(String.valueOf(typedValue));
                }
                selectedOperator = '/';
                go = false;
                addToDisplay = false;
            } else {
                selectedOperator = '/';
            }
        });

        btn7 = createButton("7", columns[0], rows[2]);
        btn7.addActionListener(event -> {
            if (addToDisplay) {
                if (Pattern.matches("[0]*", inputScreen.getText())) {
                    inputScreen.setText("7");
                } else {
                    inputScreen.setText(inputScreen.getText() + "7");
                }
            } else {
                inputScreen.setText("7");
                addToDisplay = true;
            }
            go = true;
        });

        btn8 = createButton("8", columns[1], rows[2]);
        btn8.addActionListener(event -> {
            if (addToDisplay) {
                if (Pattern.matches("[0]*", inputScreen.getText())) {
                    inputScreen.setText("8");
                } else {
                    inputScreen.setText(inputScreen.getText() + "8");
                }
            } else {
                inputScreen.setText("8");
                addToDisplay = true;
            }
            go = true;
        });

        btn9 = createButton("9", columns[2], rows[2]);
        btn9.addActionListener(event -> {
            if (addToDisplay) {
                if (Pattern.matches("[0]*", inputScreen.getText())) {
                    inputScreen.setText("9");
                } else {
                    inputScreen.setText(inputScreen.getText() + "9");
                }
            } else {
                inputScreen.setText("9");
                addToDisplay = true;
            }
            go = true;
        });

        btnMul = createButton("*", columns[3], rows[2]);
        btnMul.addActionListener(event -> {
            if (!Pattern.matches(DOUBLE_OR_NUMBER_REGEX, inputScreen.getText()))
                return;

            if (go) {
                typedValue = logic.calculate(typedValue, Double.parseDouble(inputScreen.getText()), selectedOperator);
                if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(typedValue))) {
                    inputScreen.setText(String.valueOf((int) typedValue));
                } else {
                    inputScreen.setText(String.valueOf(typedValue));
                }
                selectedOperator = '*';
                go = false;
                addToDisplay = false;
            } else {
                selectedOperator = '*';
            }
        });

        btn4 = createButton("4", columns[0], rows[3]);
        btn4.addActionListener(event -> {
            if (addToDisplay) {
                if (Pattern.matches("[0]*", inputScreen.getText())) {
                    inputScreen.setText("4");
                } else {
                    inputScreen.setText(inputScreen.getText() + "4");
                }
            } else {
                inputScreen.setText("4");
                addToDisplay = true;
            }
            go = true;
        });

        btn5 = createButton("5", columns[1], rows[3]);
        btn5.addActionListener(event -> {
            if (addToDisplay) {
                if (Pattern.matches("[0]*", inputScreen.getText())) {
                    inputScreen.setText("5");
                } else {
                    inputScreen.setText(inputScreen.getText() + "5");
                }
            } else {
                inputScreen.setText("5");
                addToDisplay = true;
            }
            go = true;
        });

        btn6 = createButton("6", columns[2], rows[3]);
        btn6.addActionListener(event -> {
            if (addToDisplay) {
                if (Pattern.matches("[0]*", inputScreen.getText())) {
                    inputScreen.setText("6");
                } else {
                    inputScreen.setText(inputScreen.getText() + "6");
                }
            } else {
                inputScreen.setText("6");
                addToDisplay = true;
            }
            go = true;
        });

        btnSub = createButton("-", columns[3], rows[3]);
        btnSub.addActionListener(event -> {
            if (!Pattern.matches(DOUBLE_OR_NUMBER_REGEX, inputScreen.getText()))
                return;

            if (go) {
                typedValue = logic.calculate(typedValue, Double.parseDouble(inputScreen.getText()), selectedOperator);
                if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(typedValue))) {
                    inputScreen.setText(String.valueOf((int) typedValue));
                } else {
                    inputScreen.setText(String.valueOf(typedValue));
                }

                selectedOperator = '-';
                go = false;
                addToDisplay = false;
            } else {
                selectedOperator = '-';
            }
        });

        btn1 = createButton("1", columns[0], rows[4]);
        btn1.addActionListener(event -> {
            if (addToDisplay) {
                if (Pattern.matches("[0]*", inputScreen.getText())) {
                    inputScreen.setText("1");
                } else {
                    inputScreen.setText(inputScreen.getText() + "1");
                }
            } else {
                inputScreen.setText("1");
                addToDisplay = true;
            }
            go = true;
        });

        btn2 = createButton("2", columns[1], rows[4]);
        btn2.addActionListener(event -> {
            if (addToDisplay) {
                if (Pattern.matches("[0]*", inputScreen.getText())) {
                    inputScreen.setText("2");
                } else {
                    inputScreen.setText(inputScreen.getText() + "2");
                }
            } else {
                inputScreen.setText("2");
                addToDisplay = true;
            }
            go = true;
        });

        btn3 = createButton("3", columns[2], rows[4]);
        btn3.addActionListener(event -> {
            if (addToDisplay) {
                if (Pattern.matches("[0]*", inputScreen.getText())) {
                    inputScreen.setText("3");
                } else {
                    inputScreen.setText(inputScreen.getText() + "3");
                }
            } else {
                inputScreen.setText("3");
                addToDisplay = true;
            }
            go = true;
        });

        btnAdd = createButton("+", columns[3], rows[4]);
        btnAdd.addActionListener(event -> {
            if (!Pattern.matches(DOUBLE_OR_NUMBER_REGEX, inputScreen.getText()))
                return;

            if (go) {
                typedValue = logic.calculate(typedValue, Double.parseDouble(inputScreen.getText()), selectedOperator);
                if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(typedValue))) {
                    inputScreen.setText(String.valueOf((int) typedValue));
                } else {
                    inputScreen.setText(String.valueOf(typedValue));
                }
                selectedOperator = '+';
                go = false;
                addToDisplay = false;
            } else {
                selectedOperator = '+';
            }
        });

        btnPoint = createButton(".", columns[0], rows[5]);
        btnPoint.addActionListener(event -> {
            if (addToDisplay) {
                if (!inputScreen.getText().contains(".")) {
                    inputScreen.setText(inputScreen.getText() + ".");
                }
            } else {
                inputScreen.setText("0.");
                addToDisplay = true;
            }
            go = true;
        });

        btn0 = createButton("0", columns[1], rows[5]);
        btn0.addActionListener(event -> {
            if (addToDisplay) {
                if (Pattern.matches("[0]*", inputScreen.getText())) {
                    inputScreen.setText("0");
                } else {
                    inputScreen.setText(inputScreen.getText() + "0");
                }
            } else {
                inputScreen.setText("0");
                addToDisplay = true;
            }
            go = true;
        });

        btnEqual = createButton("=", columns[2], rows[5]);
        btnEqual.setSize(EQUAL_BUTTON_WIDTH, BUTTON_HEIGHT); // Updated equal button size for prominence
        btnEqual.addActionListener(event -> {
            if (!Pattern.matches(DOUBLE_OR_NUMBER_REGEX, inputScreen.getText()))
                return;

            if (go) {
                typedValue = logic.calculate(typedValue, Double.parseDouble(inputScreen.getText()), selectedOperator);
                if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(typedValue))) {
                    inputScreen.setText(String.valueOf((int) typedValue));
                } else {
                    inputScreen.setText(String.valueOf(typedValue));
                }
                selectedOperator = '=';
                addToDisplay = false;
            }
        });

        btnRoot = createButton("√", columns[4], rows[1]);
        btnRoot.addActionListener(event -> {
            if (!Pattern.matches(DOUBLE_OR_NUMBER_REGEX, inputScreen.getText()))
                return;

            if (go) {
                typedValue = logic.sqrt(Double.parseDouble(inputScreen.getText()));
                if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(typedValue))) {
                    inputScreen.setText(String.valueOf((int) typedValue));
                } else {
                    inputScreen.setText(String.valueOf(typedValue));
                }
                selectedOperator = '√';
                addToDisplay = false;
            }
        });
        btnRoot.setVisible(false);

        btnPower = createButton("pow", columns[4], rows[2]);
        btnPower.addActionListener(event -> {
            if (!Pattern.matches(DOUBLE_OR_NUMBER_REGEX, inputScreen.getText()))
                return;

            if (go) {
                typedValue = logic.calculate(typedValue, Double.parseDouble(inputScreen.getText()), selectedOperator);
                if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(typedValue))) {
                    inputScreen.setText(String.valueOf((int) typedValue));
                } else {
                    inputScreen.setText(String.valueOf(typedValue));
                }
                selectedOperator = '^';
                go = false;
                addToDisplay = false;
            } else {
                selectedOperator = '^';
            }
        });
        btnPower.setFont(new Font(FONT_NAME, Font.PLAIN, 24)); // Updated font for consistency
        btnPower.setVisible(false);
        
        // modified btnlog to btnLn
        btnLn = createButton("ln", columns[4], rows[3]);
        btnLn.addActionListener(event -> {
            if (!Pattern.matches(DOUBLE_OR_NUMBER_REGEX, inputScreen.getText()))
                return;

            if (go) {
                typedValue = logic.ln(Double.parseDouble(inputScreen.getText()));
                if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(typedValue))) {
                    inputScreen.setText(String.valueOf((int)typedValue));
                } else {
                    inputScreen.setText(String.valueOf(typedValue));
                }
                selectedOperator = 'n';
                addToDisplay = false;
            }
        });
        btnLn.setVisible(false);
        // new log button
        btnLog = createButton("log", columns[4], rows[4]);
        btnLog.addActionListener(event -> {
            if (!Pattern.matches(DOUBLE_OR_NUMBER_REGEX, inputScreen.getText()))
                return;

            if (go) {
                typedValue = logic.log(Double.parseDouble(inputScreen.getText()));
                if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(typedValue))) {
                    inputScreen.setText(String.valueOf((int)typedValue));
                } else {
                    inputScreen.setText(String.valueOf(typedValue));
                }
                selectedOperator = 'l';
                addToDisplay = false;
            }
        });
        btnLog.setVisible(false);

        btnPi = createButton("\u03C0", columns[4], rows[4]); // Added Pi button initialization
        btnPi.addActionListener(event -> { // Added action listener for Pi button
            if (!Pattern.matches(DOUBLE_OR_NUMBER_REGEX, inputScreen.getText()))
                return;

            if (go) {
                typedValue = logic.PI(Double.parseDouble(inputScreen.getText()));
                if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(typedValue))) {
                    inputScreen.setText(String.valueOf((int) typedValue));
                } else {
                    inputScreen.setText(String.valueOf(typedValue));
                }
                selectedOperator = 'P';
                addToDisplay = false;
            }
        });
        btnPi.setVisible(false); // Set Pi button visibility to false initially

        // Added factorial button
        btnFact = createButton("!", columns[4], rows[5]);
        btnFact.addActionListener(event -> {
            if (!Pattern.matches(DOUBLE_OR_NUMBER_REGEX, inputScreen.getText()))
                return;

            if (go) {
                typedValue = logic.factorial(Integer.parseInt(inputScreen.getText()));
                if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(typedValue))) {
                    inputScreen.setText(String.valueOf((int) typedValue));
                } else {
                    inputScreen.setText(String.valueOf(typedValue));
                }
                selectedOperator = 'f';
                addToDisplay = false;
            }
        });
        btnFact.setVisible(false);

        btnSin = createButton("sin", columns[5], rows[1]);
        btnSin.addActionListener(event -> {
            if (!Pattern.matches(DOUBLE_OR_NUMBER_REGEX, inputScreen.getText()))
                return;

            if (go) {
                double inputValue = Double.parseDouble(inputScreen.getText());
                double result = logic.sin(inputValue);
                if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(result))) {
                    inputScreen.setText(String.valueOf((int) result));
                } else {
                    inputScreen.setText(String.valueOf(result));
                }
                selectedOperator = 's';
                addToDisplay = false;
            }
        });
        btnSin.setVisible(false); // Set sine button visibility to false initially
        window.add(btnSin); // Add sine button to window

        btnCos = createButton("cos", columns[5], rows[2]);
        btnCos.addActionListener(event -> {
            if (!Pattern.matches(DOUBLE_OR_NUMBER_REGEX, inputScreen.getText()))
                return;

            if (go) {
                double inputValue = Double.parseDouble(inputScreen.getText());
                double result = logic.cos(inputValue);
                if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(result))) {
                    inputScreen.setText(String.valueOf((int) result));
                } else {
                    inputScreen.setText(String.valueOf(result));
                }
                selectedOperator = 'c';
                addToDisplay = false;
            }
        });
        btnCos.setVisible(false); // Set cosine button visibility to false initially
        window.add(btnCos); // Add cosine button to window

        btnTan = createButton("tan", columns[5], rows[3]);
        btnTan.addActionListener(event -> {
            if (!Pattern.matches(DOUBLE_OR_NUMBER_REGEX, inputScreen.getText()))
                return;

            if (go) {
                double inputValue = Double.parseDouble(inputScreen.getText());
                double result = logic.tan(inputValue);
                if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(result))) {
                    inputScreen.setText(String.valueOf((int) result));
                } else {
                    inputScreen.setText(String.valueOf(result));
                }
                selectedOperator = 't';
                addToDisplay = false;
            }
        });
        btnTan.setVisible(false); // Set tangent button visibility to false initially
        window.add(btnTan); // Add tangent button to window

        // Added degrees button
        btnDeg = createButton("deg", columns[5], rows[4]);
        btnDeg.addActionListener(event -> {
            if (!Pattern.matches(DOUBLE_OR_NUMBER_REGEX, inputScreen.getText()))
                return;

            if (go) {
                typedValue = logic.degrees(Double.parseDouble(inputScreen.getText()));
                if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(typedValue))) {
                    inputScreen.setText(String.valueOf((int) typedValue));
                } else {
                    inputScreen.setText(String.valueOf(typedValue));
                }
                selectedOperator = 'd';
                addToDisplay = false;
            }
        });
        btnDeg.setVisible(false);

        // Added radians button
        btnRad = createButton("rad", columns[5], rows[5]);
        btnRad.addActionListener(event -> {
            if (!Pattern.matches(DOUBLE_OR_NUMBER_REGEX, inputScreen.getText()))
                return;

            if (go) {
                typedValue = logic.radians(Double.parseDouble(inputScreen.getText()));
                if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(typedValue))) {
                    inputScreen.setText(String.valueOf((int) typedValue));
                } else {
                    inputScreen.setText(String.valueOf(typedValue));
                }
                selectedOperator = 'r';
                addToDisplay = false;
            }
        });
        btnRad.setVisible(false);
    }

    private JComboBox<String> createComboBox(String[] items, int x, int y, String toolTip) {
        JComboBox<String> combo = new JComboBox<>(items);
        combo.setBounds(x, y, 140, 25);
        combo.setToolTipText(toolTip);
        combo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        window.add(combo);

        return combo;
    }

    private JButton createButton(String label, int x, int y) {
        JButton btn = new JButton(label);
        btn.setBounds(x, y, BUTTON_WIDTH, BUTTON_HEIGHT); // Set button size for consistency
        btn.setFont(new Font(FONT_NAME, Font.PLAIN, 25)); // Set font size for all buttons here
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setFocusable(false);
        window.add(btn);
        return btn;
    }
}