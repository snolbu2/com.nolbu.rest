package com.nolbu.rest.inventory;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.nolbu.dao.SchemaNolbu;

@Path("v3/inventory")
public class V3_inventory {

	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public Response addPcParts2(String incomingData) throws Exception{
		
		String returnString = null;
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		SchemaNolbu dao = new SchemaNolbu();
		
		try{
			JSONObject partsData = new JSONObject(incomingData);
			System.out.println("jsonData: " + partsData.toString());
			
			int http_code = dao.insertIntoPC_PARTS(partsData.optString("PC_PARTS_PK"), 
																													partsData.optString("PC_PARTS_TITLE"), 
																													partsData.optString("PC_PARTS_CODE"), 
																													partsData.optString("PC_PARTS_MAKER"), 
																													partsData.optString("PC_PARTS_AVAIL"), 
																													partsData.optString("PC_PARTS_DESC"));
			
			if(http_code == 200){
				jsonObject.put("HTTP_CODE", "200");
				jsonObject.put("MSG", "Item has been entered successfully,version 3");
				returnString = jsonArray.put(jsonObject).toString();
			} else {
				return Response.status(500).entity("Unable to enter Item").build();
			}

			System.out.println("returnString: " + returnString);
		} catch(Exception e){
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}
		
		return Response.ok(returnString).build();
		
	}

	@Path("/{brand}/{item_number}")
	@PUT
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateItem(@PathParam("brand") String brand,
																			@PathParam("item_number") int item_number,
																			String incomingData) throws Exception{
		
		int pk;
		int avail;
		int http_code;
		
		String returnString = null;
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		SchemaNolbu dao = new SchemaNolbu();
		
		try{
			JSONObject partsData = new JSONObject(incomingData);
			pk = partsData.optInt("PC_PARTS_PK", 0);
			avail = partsData.optInt("PC_PARTS_AVAIL",0);
			
			http_code = dao.updatePC_PARTS(pk, avail);
			
			if(http_code == 200){
				jsonObject.put("HTTP_CODE", "200");
				jsonObject.put("MSG", "Item has been entered successfully,version 3");
			} else {
				return Response.status(500).entity("Unable to enter Item").build();
			}

			returnString = jsonArray.put(jsonObject).toString();
		} catch(Exception e){
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}
		
		return Response.ok(returnString).build();
		
	}

	@Path("/{brand}/{item_number}")
	@DELETE
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteItem(@PathParam("brand") String brand,
																			@PathParam("item_number") int item_number,
																			String incomingData) throws Exception{
		
		int pk;
		int http_code;
		
		String returnString = null;
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		SchemaNolbu dao = new SchemaNolbu();
		
		try{
			JSONObject partsData = new JSONObject(incomingData);
			pk = partsData.optInt("PC_PARTS_PK", 0);
			
			http_code = dao.deletePC_PARTS(pk);
			
			if(http_code == 200){
				jsonObject.put("HTTP_CODE", "200");
				jsonObject.put("MSG", "Item has been entered successfully,version 3");
			} else {
				return Response.status(500).entity("Unable to enter Item").build();
			}

			returnString = jsonArray.put(jsonObject).toString();
		} catch(Exception e){
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}
		
		return Response.ok(returnString).build();
		
	}
}
