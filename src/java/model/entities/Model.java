/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.Set;
import model.entities.enums.Capacity;
import model.entities.enums.License;
/**
 *
 * @author guillermo
 */
@Entity
@XmlRootElement
public class Model {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Model_Gen")
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "PROVIDER_ID")
    private Provider provider;
    
    private Set<Capacity> capacities;    
    private License license;    
    private Date trainingDate;  
    private String version;
    
    
    public Model(){}
    

    //Setters and getters
    
    public int getId() {
        return id;
    }

    public Provider getProvider() {
        return provider;
    }

    public Set<Capacity> getCapacities() {
        return capacities;
    }

    public License getLicense() {
        return license;
    }

    public Date getTrainingDate() {
        return trainingDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public void setCapacities(Set<Capacity> capacities) {
        this.capacities = capacities;
    }

    public void setLicense(License license) {
        this.license = license;
    }

    public void setTrainingDate(Date trainingDate) {
        this.trainingDate = trainingDate;
    }
    
    

    
}
