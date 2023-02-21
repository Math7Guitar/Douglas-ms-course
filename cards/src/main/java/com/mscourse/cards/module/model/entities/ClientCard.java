package com.mscourse.cards.module.model.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;

import org.springframework.data.annotation.PersistenceCreator;

@Entity
public class ClientCard {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String cpf;

    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;

    @Column
    private BigDecimal cardLimit;

    //Constructors
    @PersistenceCreator
    public ClientCard(String cpf, Card card, BigDecimal limit) {;
        setCpf(cpf);
        setCard(card);
        setLimit(limit);
    }

    public ClientCard() {}
    
    
    //Getters
    public Integer getId() { return this.id; }
    public String getCpf() { return this.cpf; }
    public Card getCard() { return this.card; }
    public BigDecimal getLimit() { return this.cardLimit; }
    
    
    //Setters
    public void setId(Integer id) { this.id = id; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public void setCard(Card card) { this.card = card; }
    public void setLimit(BigDecimal limit) { this.cardLimit = limit; }

}
