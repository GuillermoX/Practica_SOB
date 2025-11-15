/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.entitiesServices;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import model.entities.model.Capability;
import model.entities.model.Model;
import model.entities.model.Provider;

/**
 *
 * @author guillermo
 */
public class ModelService {
    
    private EntityManager em;
    
    public ModelService(EntityManager em){
        this.em = em;
    }
    
    public boolean create(Model model){
        if(!Model.checkValues(model))
            return false;
        
        em.persist(model);
        return true;      
    }
    
    public Model find(int id){
        return em.find(Model.class, id);
    }
    
    public List<Model> findFiltered(List<String> caps, String prov){
        
        //Initialize capabilities variables
        String cap1 = null;
        String cap2 = null;
        if(caps.size() >= 1) cap1 = caps.get(0);
        if(caps.size() == 2) cap2 = caps.get(1);
        
        //General query
        String jpql = "SELECT m FROM Model m WHERE 1=1";
        //Subquery to capabilities filter
        String subpql = " (SELECT c.name FROM Model m2 JOIN m2.capabilities c WHERE m2.name = m.name)";

        //Only insert the condition if filter specified
        if (cap1 != null && !cap1.isEmpty()) {
            jpql += " AND :cap1_name IN" + subpql;
        }
        if (cap2 != null && !cap2.isEmpty()) {
            jpql += " AND :cap2_name IN" + subpql;
        }
        if (prov != null && !prov.isEmpty()) {
            jpql += " AND m.provider.name = :prov_name";
        }

        jpql += " ORDER BY m.name";

        //Create the query
        TypedQuery<Model> query = em.createQuery(jpql, Model.class);

        //Only insert the parameter if specified
        if (cap1 != null && !cap1.isEmpty()) {
            query.setParameter("cap1_name", cap1);
        }
        if (cap2 != null && !cap2.isEmpty()) {
            query.setParameter("cap2_name", cap2);
        }
        if (prov != null && !prov.isEmpty()) {
            query.setParameter("prov_name", prov);
        }
        
        return query.getResultList();
    }
}
