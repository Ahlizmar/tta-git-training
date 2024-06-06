package com.houarizegai.calculator.theme.properties;

public class Theme {

    private String name;
    private String applicationBackground;
    private String textColor;
    private String btnEqualTextColor;
    private String operatorBackground;
    private String numbersBackground;
    private String btnEqualBackground;
    private String selectionBackground; // Added for additional themes
    private String selectionTextColor; // Added for additional themes
    private String displayTextColor; // Added for additional themes

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApplicationBackground() {
        return applicationBackground;
    }

    public void setApplicationBackground(String applicationBackground) {
        this.applicationBackground = applicationBackground;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public String getBtnEqualTextColor() {
        return btnEqualTextColor;
    }

    public void setBtnEqualTextColor(String btnEqualTextColor) {
        this.btnEqualTextColor = btnEqualTextColor;
    }

    public String getOperatorBackground() {
        return operatorBackground;
    }

    public void setOperatorBackground(String operatorBackground) {
        this.operatorBackground = operatorBackground;
    }

    public String getNumbersBackground() {
        return numbersBackground;
    }

    public void setNumbersBackground(String numbersBackground) {
        this.numbersBackground = numbersBackground;
    }

    public String getBtnEqualBackground() {
        return btnEqualBackground;
    }

    public void setBtnEqualBackground(String btnEqualBackground) {
        this.btnEqualBackground = btnEqualBackground;
    }

    // Getters and setters for the new properties
    public String getSelectionBackground() {
        return selectionBackground;
    }

    public void setSelectionBackground(String selectionBackground) {
        this.selectionBackground = selectionBackground;
    }

    public String getSelectionTextColor() {
        return selectionTextColor;
    }

    public void setSelectionTextColor(String selectionTextColor) {
        this.selectionTextColor = selectionTextColor;
    }

    public String getDisplayTextColor() {
        return displayTextColor;
    }

    public void setDisplayTextColor(String displayTextColor) {
        this.displayTextColor = displayTextColor;
    }
}