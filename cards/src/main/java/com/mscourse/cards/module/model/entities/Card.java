package com.mscourse.cards.module.model.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.PersistenceCreator;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.mscourse.cards.module.model.classes.Auditable;
import com.mscourse.cards.module.model.enumerated.CardFlag;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cards")
public class Card extends Auditable {
    
    //Properties
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name; 
    
    @Enumerated(EnumType.STRING)
    private CardFlag flag;
    
    @Column
    private BigDecimal rent;
    
    @Column
    private BigDecimal limit;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_client", nullable = false)
    @JsonBackReference
    private Client client;


    //Constructors
    @PersistenceCreator
    public Card(String name, CardFlag flag, BigDecimal rent, BigDecimal limit, Client client) {
        super();
        setName(name);
        setFlag(flag);
        setRent(rent);
        setLimit(limit);
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
    public BigDecimal getLimit() { return this.limit; }
    public Client getClient() { return this.client; }
    
    
    //Setters
    public void setId(Integer id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setFlag(CardFlag flag) { this.flag = flag; }
    public void setRent(BigDecimal rent) { this.rent = rent; }
    public void setLimit(BigDecimal limit) { this.limit = limit; }
    public void setClient(Client client) { this.client = client; }
}
