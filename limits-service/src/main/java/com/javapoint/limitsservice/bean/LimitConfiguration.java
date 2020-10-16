package com.javapoint.limitsservice.bean;

public class LimitConfiguration {

    private int maximum ;
    private int minimum ;

    protected LimitConfiguration(){

    }

    public int getMaximum() {
        return maximum;
    }

    public int getMinimum() {
        return minimum;
    }

    public LimitConfiguration(int minimum , int maximum){
        super() ;
        this.minimum = minimum ;
        this.maximum = maximum ;
    }
}
