package com.mscourse.creditevaluator.module.model.classes;


public class Client {
    
    //Properties
    private Integer id;
    private String cpf;
    private String name;
    private Integer age;
    

    //Constructors
    public Client(Integer id, String cpf, String name, Integer age) {
        setId(id);
        setCpf(cpf);
        setName(name);
        setAge(age);
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

