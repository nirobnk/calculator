package com.niroz.backend;

import jakarta.validation.constraints.NotNull;

public class CalculationRequest {

    @NotNull
    private Double x;
    @NotNull
    private Double y;
    @NotNull
    private String op;

    public Double getX(){return x;}
    public void setX(Double x){this.x = x;}

    public Double getY(){return y;}
    public void setY(Double y){this.y= y;}

    public String getOp(){return op;}
    public void setOp(String op){this.op = op;}

}
