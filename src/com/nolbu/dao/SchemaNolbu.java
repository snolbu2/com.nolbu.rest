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
    
    public int insertIntoPC_PARTS(String PC_PARTS_PK,
	    										String PC_PARTS_TITLE,
	    										String PC_PARTS_CODE,
	    										String PC_PARTS_MAKER,
	    										String PC_PARTS_AVAIL,
	    										String PC_PARTS_DESC) throws Exception{
		PreparedStatement query = null;
		Connection conn = null;
		
		try{
		    conn = oraclePcPartsConnection();
		    query = conn.prepareStatement("Insert into PC_PARTS "+
			    									 " (PC_PARTS_PK,PC_PARTS_TITLE, PC_PARTS_CODE, PC_PARTS_MAKER, PC_PARTS_AVAIL, PC_PARTS_DESC) "+
			    								    " VALUES(?,?,?,?,?,?)");
		    int pkint = Integer.parseInt(PC_PARTS_PK);
	    	 query.setInt(1, pkint);
	    	 query.setString(2, PC_PARTS_TITLE);
    	 	 query.setString(3, PC_PARTS_CODE);
    	 	 query.setString(4, PC_PARTS_MAKER);
    	 	 
    	 	 int avilint = Integer.parseInt(PC_PARTS_AVAIL);
    	 	 query.setInt(5, avilint);
    	 	 query.setString(6, PC_PARTS_DESC);
    	 	 
    	 	 query.executeUpdate();
		} catch(Exception e){
		    e.printStackTrace();
		    return 500;
		}finally {
		    if(conn != null) conn.close();
		}
		
		return 200;
    }
    
 public int updatePC_PARTS(int pk, int avail) throws Exception{
	 PreparedStatement query = null;
	 Connection conn = null;
	 
	 try{
		 conn = oraclePcPartsConnection();
		 query = conn.prepareStatement("UPDATE PC_PARTS " + 
				 																		" SET PC_PARTS_AVAIL = ? WHERE PC_PARTS_PK = ?");
		 query.setInt(1, avail);
		 query.setInt(2, pk);
		 query.executeUpdate();
		 
	 } catch(Exception e){
		 e.printStackTrace();
		 return 500;
	 } finally{
		 if(conn != null) conn.close();
	   }

	 return 200;
   }

 public int deletePC_PARTS(int pk) throws Exception{
	 PreparedStatement query = null;
	 Connection conn = null;
	 
	 try{
		 conn = oraclePcPartsConnection();
		 query = conn.prepareStatement("DELETE FROM PC_PARTS " + 
				 																		" WHERE PC_PARTS_PK = ?");
		 query.setInt(1, pk);
		 query.executeUpdate();
		 
	 } catch(Exception e){
		 e.printStackTrace();
		 return 500;
	 } finally{
		 if(conn != null) conn.close();
	   }

	 return 200;
   }

}
