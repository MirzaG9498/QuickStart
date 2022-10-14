package com.example.demo;

import java.sql.SQLException;
import java.util.List;

import com.example.demo.entity.Fee;
import com.example.demo.entity.Student;
import com.example.demo.exceptions.ElementNotFoundException;
import com.example.demo.services.FeeService;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("api/v1/fees")
public class FeeResource {
	
	private FeeService service;
	
	public FeeResource() {
		super();
		this.service=new FeeService();
	}

	@GET
    @Produces(value=MediaType.APPLICATION_JSON)
    public List<Fee> findAll() {
        
        return this.service.findAll();
    }
	
	@POST
    @Produces(value=MediaType.APPLICATION_JSON)
	@Consumes(value=MediaType.APPLICATION_JSON)
    public Response addFee(Fee entity) {
        
		 try {
	            this.service.add(entity);
	            return Response.status(201).entity(entity).build();
	        } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	            return Response.status(200).entity("record already exists").build();
	        }
    }
	
	@GET
	@Produces(value=MediaType.APPLICATION_JSON)
    @Path(value="/{key}")
    public Fee findById(@PathParam("key") int key) {
        
        return this.service.findById(key).orElseThrow(()->
        new WebApplicationException(
                Response.ok(new ElementNotFoundException("Element Not Found","ERR-603"))
                 .build()));
    }
	
	@DELETE
	@Path(value="/{key}")
	public Response deleteById(@PathParam("key") int key) {
		
		int rowDeleted=this.service.removeById(key);
		if(rowDeleted==1) {
			return Response.status(200).entity("One row deleted").build();
			
		}else {
			throw new WebApplicationException(Response.ok(new ElementNotFoundException("ERR:408", "No element found")).build());
		}
	}
	
	@PUT
	@Produces(value=MediaType.APPLICATION_JSON)
	@Consumes(value=MediaType.APPLICATION_JSON)
	@Path(value="/{course}/{revisedAmount}")
	public Response updateAmount(@PathParam("course") String course,@PathParam("revisedAmount") double revisedAmount) {
		int rowUpdated=this.service.updateByCourse(course, revisedAmount);
		if(rowUpdated==1) {
			return Response.status(200).entity("One row updated").build();
		}else {
			throw new WebApplicationException(Response.ok(new ElementNotFoundException("ERR:408", "No element found")).build());
			
		}
		
	}
	
	
	

}
