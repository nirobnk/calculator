package com.niroz.backend;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {
    public double calculate(String op, double x, double y) {
        return switch (op) {
            case "add" -> x + y;
            case "sub" -> x - y;
            case "mul" -> x * y;
            case "div" -> {
                if (y == 0) throw new IllegalArgumentException("Divide by zero");
                yield x / y;
            }
            default -> throw new IllegalArgumentException("Unknown operation: " + op);
        };
    }
}
