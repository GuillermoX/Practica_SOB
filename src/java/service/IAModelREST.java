package service;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.Path;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author guillermo
 */
@Stateless
@Path("rest/api/v1/models")
public class IAModelREST {
    
    
    
    @PersistenceContext(unitName = "sob_grup_27")
    private EntityManager em;
    
    
    
    
    
    
}
