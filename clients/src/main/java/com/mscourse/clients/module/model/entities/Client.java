package com.mscourse.clients.module.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

import java.time.LocalDateTime;

import org.springframework.data.annotation.PersistenceCreator;

import com.mscourse.clients.module.model.classes.Auditable;

@Entity
@Table(name = "clients")
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


    //Constructors
    @PersistenceCreator
    public Client(String cpf, String name, Integer age) {
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
