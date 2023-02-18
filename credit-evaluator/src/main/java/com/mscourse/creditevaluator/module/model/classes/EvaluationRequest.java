package com.mscourse.creditevaluator.module.model.classes;

public class EvaluationRequest {

    //Properties
    public String cpf;
    public Float rent;


    //Constructors
    public EvaluationRequest(String cpf, Float rent) {
        setCpf(cpf);
        setRent(rent);
    }

    public EvaluationRequest() {}


    //Getters
    public String getCpf() { return this.cpf; }
    public Float getrent() { return this.rent; }


    //Setters
    protected void setCpf(String cpf) { this.cpf = cpf; }
    protected void setRent(Float rent) { this.rent = rent; }    
}
