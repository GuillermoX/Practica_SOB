/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.v1;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

/**
 *
 * @author Guillermo Pinteño Cabello
 * @author Àlvaro Pérez Cabeller
 * 
 * Server exception handler to inform the REST client of the error
 * 
 */
@Provider
public class GenericBadRequestMapper implements ExceptionMapper<Exception> {
    @Override
    public Response toResponse(Exception ex) {
        // Parsin exceptions
        if (ex instanceof jakarta.json.stream.JsonParsingException ||
            ex instanceof jakarta.json.bind.JsonbException) {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity("Bad JSON formating")
                           .build();
        }
        else if(ex instanceof jakarta.ws.rs.ProcessingException){
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity("Bad JSON values")
                           .build();
        }
        // Other exceptions
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                       .entity("Internal server error: " + ex)
                       .build();
    }
}
