package com.mscourse.creditevaluator.module.model.classes;

import java.math.BigDecimal;

import com.mscourse.creditevaluator.module.enumerated.CardFlag;


public class Card {
    
    //Properties
    private Integer id;
    private String name; 
    private CardFlag flag;
    private BigDecimal rent;
    private BigDecimal limit;
    private String client;


    //Constructors
    public Card(String name, CardFlag flag, BigDecimal rent, BigDecimal limit, String client) {
        setName(name);
        setFlag(flag);
        setRent(rent);
        setLimit(limit);
        setClient(client);
    }

    public Card() {}
    
    
    //Getters
    public Integer getId() { return this.id; }
    public String getName() { return this.name; }
    public CardFlag getFlag() { return this.flag; }
    public BigDecimal getRent() { return this.rent; }
    public BigDecimal getLimit() { return this.limit; }
    public String getClient() { return this.client; }
    
    
    //Setters
    public void setId(Integer id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setFlag(CardFlag flag) { this.flag = flag; }
    public void setRent(BigDecimal rent) { this.rent = rent; }
    public void setLimit(BigDecimal limit) { this.limit = limit; }
    public void setClient(String client) { this.client = client; }
}
