package com.mscourse.cards.module.model.classes;

import java.math.BigDecimal;

public class CardIssuanceData {
    
    //Properties
    private Integer id;
    private String cpf, address;
    private BigDecimal cardlimit;


    //Constructors
    public CardIssuanceData(Integer id, String cpf, String address, Float cardlimit) {
        setId(id);
        setCpf(cpf);
        setAddress(address);
        setLimit(BigDecimal.valueOf(cardlimit));
    }

    public CardIssuanceData() {}


    //Getters
    public Integer getId() { return this.id; }
    public String getCpf() { return this.cpf; }
    public String getAddress() { return this.address; }
    public BigDecimal getLimit() { return this.cardlimit; }


    //Setters
    public void setId(Integer id) { this.id = id; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public void setAddress(String address) { this.address = address;}
    public void setLimit(BigDecimal cardlimit) { this.cardlimit = cardlimit; }
}
