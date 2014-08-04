package com.nolbu.rest.inventory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;

import com.nolbu.dao.OracleNolbu;
import com.nolbu.dao.SchemaNolbu;
import com.nolbu.util.ToJSON;

@Path("v2/inventory")
public class V2_Inventory {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnBrandParts(@QueryParam("brand") String brand) throws Exception{
		
		String returnString = null;
		JSONArray json = new JSONArray();
		
		
		try{
		    if(brand == null){
				return Response.status(400).entity("Error:please enter brand name").build();
		    	  }
		    
		    SchemaNolbu dao = new SchemaNolbu();
		    
		    json = dao.queryReturnBrandParts(brand);
		    returnString = json.toString();
		    
		} catch(Exception e){
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}
		
		return Response.ok(returnString).build();
	}    

/*	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnErrorOnBrand() throws Exception{
	    return Response.status(400).entity("Error: please specify brand for this search");
	}
*/
	
	@Path("/{brand}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnBrand(@PathParam("brand") String brand) throws Exception{
		
		String returnString = null;
		JSONArray json = new JSONArray();
		
		
		try{
		    
		    SchemaNolbu dao = new SchemaNolbu();
		    
		    json = dao.queryReturnBrandParts(brand);
		    returnString = json.toString();
		    
		} catch(Exception e){
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}
		
		return Response.ok(returnString).build();
	}    

	@Path("/{brand}/{item_number}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnSpecificBrandItem(@PathParam("brand") String brand,
														  @PathParam("item_number") int item_number ) throws Exception{
		
		String returnString = null;
		JSONArray json = new JSONArray();
		
		
		try{
		    
		    SchemaNolbu dao = new SchemaNolbu();
		    
		    json = dao.queryReturnBrandItemNumber(brand,item_number);
		    returnString = json.toString();
		    
		} catch(Exception e){
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}
		
		return Response.ok(returnString).build();
	}    
}
