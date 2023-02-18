package com.mscourse.cards.module.model.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.PersistenceCreator;

import com.mscourse.cards.module.model.classes.Auditable;
import com.mscourse.cards.module.model.enumerated.CardFlag;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cards")
public class Card extends Auditable {

    //Properties
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name; 
    
    @Column(nullable = true)
    @Enumerated(EnumType.STRING)
    private CardFlag flag;
    
    @Column(nullable = false)
    private BigDecimal rent;
    
    @Column(nullable = false)
    private BigDecimal cardlimit;

    @Column(nullable = false)
    private String client;


    //Constructors
    @PersistenceCreator
    public Card(String name, CardFlag flag, Float rent, Float limit, String client) {
        setName(name);
        setFlag(flag);
        setRent(BigDecimal.valueOf(rent));
        setLimit(BigDecimal.valueOf(limit));
        setClient(client);
        setCreatedBy("Douglas ms-course");
        setCreationDate(LocalDateTime.now());
        setLastModifiedBy("admin");
        setLastModifiedDate(LocalDateTime.now());
    }

    public Card() {}
    
    
    //Getters
    public Integer getId() { return this.id; }
    public String getName() { return this.name; }
    public CardFlag getFlag() { return this.flag; }
    public BigDecimal getRent() { return this.rent; }
    public BigDecimal getLimit() { return this.cardlimit; }
    public String getClient() { return this.client; }
    
    
    //Setters
    public void setId(Integer id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setFlag(CardFlag flag) { this.flag = flag; }
    public void setRent(BigDecimal rent) { this.rent = rent; }
    public void setLimit(BigDecimal limit) { this.cardlimit = limit; }
    public void setClient(String client) { this.client = client; }
    
}
