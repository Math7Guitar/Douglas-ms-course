package com.mscourse.cards.module.model.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.PersistenceCreator;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.mscourse.cards.module.model.classes.Auditable;

import jakarta.persistence.Column;


public class Client extends Auditable {
    
    //Properties
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 200)
    private String cpf;

    @Column(nullable = false, length = 200)
    private String name;

    @Column(nullable = false, length = 200)
    private Integer age;

    @OneToMany(mappedBy = "client")
    @JsonManagedReference
    private List<Card> cards;


    //Constructors
    @PersistenceCreator
    public Client(String cpf, String name, Integer age) {
        super();
        setCpf(cpf);
        setName(name);
        setAge(age);
        setCreatedBy("Douglas ms-course");
        setCreationDate(LocalDateTime.now());
        setLastModifiedBy("admin");
        setLastModifiedDate(LocalDateTime.now());
    }

    public Client() {}


    //Getters
    public Integer getId() { return this.id; }
    public String getCpf() { return this.cpf; }
    public String getName() {return this.name; }
    public Integer getAge() { return this.age; }


    //Setters
    public void setId(Integer id) { this.id = id; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public void setName(String name) { this.name = name; }
    public void setAge(Integer age) { this.age = age; }
}

