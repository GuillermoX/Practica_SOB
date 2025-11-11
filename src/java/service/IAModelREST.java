package service;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.entities.Comment;
import model.entities.Model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author guillermo
 */
@Stateless
@Path("models")
public class IAModelREST {
    
    
    
    @PersistenceContext(unitName = "homework1")
    private EntityManager em;
    
    
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Comment entity) {
        em.persist(entity);
    }
    
    /*@GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response find(@PathParam("id") int id) {
        return Response.ok().entity(em.find(Model.class, id)).build();
    }*/
    
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response findQuery(
                                @QueryParam("capability1") String cap1,
                                @QueryParam("capability2") String cap2,
                                @QueryParam("provider") String prov
                            ) {
            TypedQuery<Model> query = em.createNamedQuery("Model.findByProvider", Model.class);
            Model model = query.setParameter("prov_name", prov)
                            .getSingleResult();
            
            String jpql = "SELECT m FROM Model m"; // 1=1 facilita concatenar
            String capacities = "(SELECT c.name FROM Model JOIN m.capacities c)";
            
            jpql += " WHERE 1=1";
            
            if (cap1 != null && !cap1.isEmpty()) {
                jpql += " AND :cap1_name IN " + capacities;
            }
            if (cap2 != null && !cap2.isEmpty()) {
                jpql += " AND :cap2_name IN " + capacities;
            }
            if (prov != null && !prov.isEmpty()) {
                jpql += " AND m.provider.name = :prov_name";
            }
            
            TypedQuery<Model> query2 = em.createQuery(jpql, Model.class);

            if (cap1 != null && !cap1.isEmpty()) {
                query2.setParameter("cap1_name", cap1);
            }
            if (cap2 != null && !cap2.isEmpty()) {
                query2.setParameter("cap2_name", cap2);
            }
            if (prov != null && !prov.isEmpty()) {
                query2.setParameter("prov_name", prov);
            }
            
            Model model2 = query2.getSingleResult();
            
            return Response.ok().entity(model2).build();
        
    }

    
}
