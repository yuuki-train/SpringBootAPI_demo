package com.example.demo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "candidate")
public class Candidate {
    @Id
    private String id;

    private String name;

    private double exp;

    @Indexed(unique = true)
    private String email;


    public String getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public double getExp(){
        return exp;
    }
    public String getEmail(){
        return email;
    }

    public void setId(String id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setExp(double exp){
        this.exp = exp;
    }
    public void setEmail(String email){
        this.email = email;
    }

}
