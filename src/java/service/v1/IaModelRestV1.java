package service.v1;

import authn.BasicAuthDecoder;
import authn.Secured;
import com.sun.xml.messaging.saaj.util.Base64;
import jakarta.ejb.Stateless;
import jakarta.enterprise.inject.Default;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import model.entities.customer.Customer;
import model.entities.model.Model;
import service.entitiesServices.CustomerService;
import service.entitiesServices.ModelService;

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
    public Response create(Model model) {
        ModelService ms = new ModelService(em);
        if(!ms.create(model))
            return Response.status(Response.Status.BAD_REQUEST).entity("Unreasonable model values").build();
        else
            return Response.status(Response.Status.CREATED).entity(model).build(); 
    }

    
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response find(@PathParam("id") int id,  @DefaultValue("") @HeaderParam(HttpHeaders.AUTHORIZATION) String auth) {
        ModelService ms = new ModelService(em);
        CustomerService cs = new CustomerService(em);
        Response res;
        
        Model model = ms.find(id);
        if(model != null){    //If model found
            Customer cust = null;
            
            BasicAuthDecoder decoder = new BasicAuthDecoder();
            List<String> nameAndPsw = decoder.decode(auth);
            if(nameAndPsw != null)   //If auth decoded correctly
                cust = cs.findByNameAndPsw(nameAndPsw.get(0), nameAndPsw.get(1));    //Get the customer
            
            if(model.isPriv() && (cust == null)){     //If private model and customer not registered
                res = Response.status(Response.Status.FORBIDDEN).build();   
            }
            else{ 
                if(cust != null)       //If customer registered save last model checked
                    cs.setLastModel(cust, model, "/models/");
                
                res = Response.ok().entity(model).build();
            }
        }
        else{
            res = Response.status(Response.Status.NOT_FOUND).build();
        }
        
        return res;
    }
    
    
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response findQuery(@QueryParam("capability") List<String> caps, @QueryParam("provider") String prov) {
        ModelService ms = new ModelService(em);
        List<Model> models = ms.findFiltered(caps, prov);
        return Response.ok().entity(models).build();
        
    }

    
}
