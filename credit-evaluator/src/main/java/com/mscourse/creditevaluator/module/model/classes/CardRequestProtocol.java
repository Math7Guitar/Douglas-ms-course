package com.mscourse.creditevaluator.module.model.classes;

public class CardRequestProtocol {
    
    //Properties
    private String protocol;


    //Constructors
    public CardRequestProtocol(String protocol) {
        setProtocol(protocol);
    }

    public CardRequestProtocol() {}


    //Getters
    public String getProtocol() { return this.protocol; }


    //Setters
    public void setProtocol(String protocol) { this.protocol = protocol; }
}
