/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.entitiesServices;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import java.util.NoSuchElementException;
import model.entities.customer.Customer;
import model.entities.model.Model;

/**
 *
 * @author guillermo
 */
public class CustomerService {
    
    EntityManager em;
    
    public CustomerService(EntityManager em){
        this.em = em;
    }
    
    public Customer findByNameAndPsw(String name, String psw){
        Customer customer;
        try{
            customer = em.createNamedQuery("Customer.findByUserPsw", Customer.class)
                    .setParameter("user", name).setParameter("psw", psw).getSingleResult();
        }
        catch(NoResultException e){
            return null;
        }
        return customer;
    }
    
    public void setLastModel(Customer cust, Model model, String modelsUri){
        cust.getLinks().setModel(modelsUri + model.getId());
        em.persist(cust);
    }
}
