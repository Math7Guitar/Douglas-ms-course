package com.mscourse.creditevaluator.module.model.classes;

import java.util.List;

public class ClientStatus {

    //Porperties
    private Integer id;
    private String name;
    private List<Card> cards;


    //Constructors
    public ClientStatus(Integer id, String name, List<Card> cards) {
        setClientId(id);
        setClientName(name);
        setCards(cards);
    }

    public ClientStatus() {}


    //Getters
    public Integer getClientId() { return this.id; }
    public String getClientName() { return this.name; }
    public List<Card> getCards() { return this.cards; }


    //Setters
    public void setClientId(Integer id) { this.id = id; }
    public void setClientName(String name) { this.name = name; }
    public void setCards(List<Card> cards) { this.cards = cards; }
}