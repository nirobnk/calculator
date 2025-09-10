package com.niroz.backend;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorServiceTest {

    private final CalculatorService service = new CalculatorService();

    @Test
    void testAddition() {
        assertEquals(5.0, service.calculate("add", 2.0, 3.0));
    }

    @Test
    void testSubtraction() {
        assertEquals(1.0, service.calculate("sub", 3.0, 2.0));
    }

    @Test
    void testMultiplication() {
        assertEquals(6.0, service.calculate("mul", 2.0, 3.0));
    }

    @Test
    void testDivision() {
        assertEquals(2.0, service.calculate("div", 6.0, 3.0));
    }

    @Test
    void testDivideByZero() {
        Exception e = assertThrows(IllegalArgumentException.class,
                () -> service.calculate("div", 5.0, 0.0));
        assertEquals("Divide by zero", e.getMessage());
    }

    @Test
    void testInvalidOperation() {
        Exception e = assertThrows(IllegalArgumentException.class,
                () -> service.calculate("mod", 5.0, 2.0));
        assertTrue(e.getMessage().contains("Unknown operation"));
    }
}
