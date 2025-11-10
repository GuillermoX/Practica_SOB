/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author guillermo
 */
@Entity
@XmlRootElement
public class Provider {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Provider_Gen")
    private int id;
    
    private String name;
    
    
    public Provider(){}
    
    //Setters and getters
    
    public int getId() {
        return id;
    }
    
    public String getName(){
        return this.name;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void setName(String name){
        this.name = name;
    }
}
