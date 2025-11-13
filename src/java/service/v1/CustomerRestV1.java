/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.v1;

import authn.Secured;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import model.entities.customer.Customer;

/**
 *
 * @author guillermo
 */
@Stateless
@Path("v1/customer")
public class CustomerRestV1 {
    
    @PersistenceContext(unitName = "homework1")
    private EntityManager em;    
    
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response find() {
        List<Customer> cust = em.createNamedQuery("Customer.findAll", Customer.class).getResultList();
        return Response.ok(cust).build();
    }
    
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response find(@PathParam("id") int id) {
        Customer cust = em.find(Customer.class, id);
        if(cust == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok().entity(cust).build();
    }
}
