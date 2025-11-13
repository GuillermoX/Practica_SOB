/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities.customer;

import com.sun.xml.messaging.saaj.util.Base64;
import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.SequenceGenerator;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author guillermo
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Customer.findByUserPsw",
            query = "SELECT c FROM Customer c WHERE c.user = :user AND c.psw = :psw"),
    @NamedQuery(name = "Customer.findAll",
            query = "SELECT c FROM Customer c")
})
@XmlRootElement
public class Customer implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id 
    @SequenceGenerator(name="Customer_Gen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Customer_Gen")
    private int id;
    
    @Column(name="USR")
    private String user;
    
    @Column(name="PSW")
    @JsonbTransient
    private String psw;     //Not secure
    
    @Embedded  
    private CustomerLinks links;

    public Customer(){}

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public String getPsw() {
        return psw;
    }

    public CustomerLinks getLinks() {
        if(this.links == null) this.links = new CustomerLinks();
        return links;
    }

    
    
    public void setId(int id) {
        this.id = id;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public void setLinks(CustomerLinks links) {
        this.links = links;
    }
    
    
}
