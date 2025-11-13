/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities.model;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author guillermo
 */
@Entity
@XmlRootElement
public class Provider implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="Provider_Gen", allocationSize = 1)
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
