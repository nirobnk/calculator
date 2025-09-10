package com.niroz.backend;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/calc")
@CrossOrigin(origins = "http://localhost:5173") // Allow frontend dev server
public class CalculatorController {

    @Autowired
    private CalculatorService service;

    @PostMapping
    public CalculationResponse calculate(@Valid @RequestBody CalculationRequest req) {
        try {
            double result = service.calculate(req.getOp(), req.getX(), req.getY());
            return new CalculationResponse(result);
        } catch (Exception e) {
            return new CalculationResponse(e.getMessage());
        }
    }
}