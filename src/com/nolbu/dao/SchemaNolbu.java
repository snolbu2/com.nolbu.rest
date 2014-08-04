package com.nolbu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.codehaus.jettison.json.JSONArray;

import com.nolbu.util.ToJSON;

public class SchemaNolbu extends OracleNolbu {

    public JSONArray queryReturnBrandParts(String brand) throws Exception{
	  	PreparedStatement query = null;
	  	Connection conn = null;
	  	
	  	ToJSON converter = new ToJSON();
	  	JSONArray json = new JSONArray();
	  	
	  	try{
	  	    conn = oraclePcPartsConnection();
	  	    query = conn.prepareStatement("SELECT PC_PARTS_PK,PC_PARTS_TITLE, PC_PARTS_CODE, PC_PARTS_MAKER, PC_PARTS_AVAIL,PC_PARTS_DESC"+ 
	  		    										  " FROM PC_PARTS " + 
	  		    											" WHERE UPPER(PC_PARTS_MAKER) = ? ");
	  	    query.setString(1,  brand.toUpperCase());
	  	    ResultSet rs = query.executeQuery();
	  	    
	  	    json = converter.toJSONArray(rs);
	  	    query.close();
	  	    	
	  	} catch (SQLException sqlError){
  	    	sqlError.printStackTrace();
    		return json;
	  	} catch (Exception e){
	  	    e.printStackTrace();
	  	    return json;
	  	} finally{
	  	    if(conn != null) conn.close();
	  	}
	  	
	  	return json;
           }

    public JSONArray queryReturnBrandItemNumber(String brand, int item_number) throws Exception{
	  	PreparedStatement query = null;
	  	Connection conn = null;
	  	
	  	ToJSON converter = new ToJSON();
	  	JSONArray json = new JSONArray();
	  	
	  	try{
	  	    conn = oraclePcPartsConnection();
	  	    query = conn.prepareStatement("SELECT PC_PARTS_PK,PC_PARTS_TITLE, PC_PARTS_CODE, PC_PARTS_MAKER, PC_PARTS_AVAIL,PC_PARTS_DESC"+ 
	  		    										  " FROM PC_PARTS " + 
	  		    											" WHERE UPPER(PC_PARTS_MAKER) = ? " +
	  		    										                " AND PC_PARTS_CODE = ? ");
	  	    query.setString(1,  brand.toUpperCase());
	  	    query.setInt(2,  item_number);
	  	    ResultSet rs = query.executeQuery();
	  	    
	  	    json = converter.toJSONArray(rs);
	  	    query.close();
	  	    	
	  	} catch (SQLException sqlError){
  	    	sqlError.printStackTrace();
    		return json;
	  	} catch (Exception e){
	  	    e.printStackTrace();
	  	    return json;
	  	} finally{
	  	    if(conn != null) conn.close();
	  	}
	  	
	  	return json;
           }
}
