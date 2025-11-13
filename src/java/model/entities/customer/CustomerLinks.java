/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities.customer;

import jakarta.persistence.Embeddable;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author guillermo
 */
@Embeddable
@XmlRootElement
public class CustomerLinks {
    @XmlElement(name = "model")
    private String model;
    
    public CustomerLinks(){}

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
