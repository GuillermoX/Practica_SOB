package service.v1;

import authn.Secured;
import com.sun.xml.messaging.saaj.util.Base64;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.List;
import java.util.StringTokenizer;
import model.entities.customer.Customer;
import model.entities.model.Model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author guillermo
 */
@Stateless
@Path("v1/models")
public class IaModelRestV1 {
    
    @PersistenceContext(unitName = "homework1")
    private EntityManager em;
    
    
    
    @POST
    @Secured
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response create(Model entity) {
        try{
            em.persist(entity);
            URI uri = UriBuilder.fromResource(IaModelRestV1.class)
                       .path(String.valueOf(entity.getId()))
                        .build();
            return Response.created(uri).build();
        }
        catch(Exception e){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
    
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response find(@PathParam("id") int id, @HeaderParam("Authorization") String auth) {
        Model model = em.find(Model.class, id);
        Response res;
        if(model != null){
            if(model.isPriv() && !isCustomerRegistered(em, auth))
                res = Response.status(Response.Status.FORBIDDEN).build();    
            else
                res = Response.ok().entity(model).build();
        }
        else{
            res = Response.status(Response.Status.NOT_FOUND).build();
        }
        
        return res;
    }
    
    
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response findQuery(
                                @QueryParam("capability1") String cap1,
                                @QueryParam("capability2") String cap2,
                                @QueryParam("provider") String prov
                            ) {
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

            List<Model> models = query.getResultList();

            return Response.ok().entity(models).build();
        
    }


    
    
    /**
     * Checks if a customer is registered on the system 
     * @param em EntityManager
     * @param auth authentication "user:password" in Base64 encoding
     * @return true if customer is registered, false if not.
     */
    public static boolean isCustomerRegistered(EntityManager em, String auth){
        if(auth == null) return false;
        
        auth = auth.replace("Basic ", "");
        String decode = Base64.base64Decode(auth);
        StringTokenizer tokenizer = new StringTokenizer(decode, ":");
        String username = tokenizer.nextToken();
        String password = tokenizer.nextToken();

        List<Customer> customer = em.createNamedQuery("Customer.findByUserPsw", Customer.class)
                .setParameter("user", username).setParameter("psw", password).getResultList();
        return !customer.isEmpty();
    }
    
}
