package com.niroz.backend;

public class CalculationResponse {
    private Double result;
    private String error;

    public CalculationResponse(Double result){this.result = result;}
    public CalculationResponse(String error){this.error = error;}

    public Double getResult(){return result;}
    public String getError(){return error;}

}
