/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities.customer;

import com.sun.xml.messaging.saaj.util.Base64;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.SequenceGenerator;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.StringTokenizer;

/**
 *
 * @author guillermo
 */
@Entity
@NamedQuery(name = "Customer.findByUserPsw",
            query = "SELECT 1 FROM Customer c WHERE c.user = :user AND c.psw = :psw")
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
    private String psw;     //Not secure

    
}
