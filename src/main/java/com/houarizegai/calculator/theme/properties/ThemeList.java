package com.houarizegai.calculator.theme.properties;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThemeList {
    private List<Theme> themes;

    // Getter and setter for the list of themes
    public List<Theme> getThemes() {
        return themes;
    }

    public void setThemes(List<Theme> themes) {
        this.themes = themes;
    }

    // Method to convert the list of themes to a map
    public Map<String, Theme> getThemesAsMap() {
        Map<String, Theme> themesMap = new HashMap<>();
        for (Theme theme : themes) {
            themesMap.put(theme.getName(), theme);
        }
        return themesMap;
    }
}
