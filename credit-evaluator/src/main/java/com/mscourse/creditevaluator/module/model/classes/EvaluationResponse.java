package com.mscourse.creditevaluator.module.model.classes;

public class EvaluationResponse {

    //Properties
    public String card, flag;
    public Float allowedlimit;


    //Constructors
    public EvaluationResponse(String card, String flag, Float limit) {
        setCard(card);
        setFlag(flag);
        setLimit(limit);
    }

    public EvaluationResponse() {}


    //Getters
    public String getCard() { return this.card; }
    public String getFlag() { return this.flag; }
    public Float getLimit() { return this.allowedlimit; }


    //Setters
    protected void setCard(String card) { this.card = card; }
    protected void setFlag(String flag) { this.flag = flag; }
    protected void setLimit(Float limit) { this.allowedlimit = limit; }    
}
