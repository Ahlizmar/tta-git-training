package com.houarizegai.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class AppTest {

    private App app;

    @BeforeEach
    public void setUp() {
        app = new App();
    }

    @Test
    public void testMain() {
        App.main(new String[]{});
    }
}
