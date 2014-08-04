package com.nolbu.rest.status;

import javax.ws.rs.GET;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.*;

import com.nolbu.dao.*;

@Path("v1/status")
public class V1_status {
	private static final String api_version = "00.01.00";
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnTitle(){
		return "<p>Java Web Service</p>";
	}
	
	@Path("/version")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnVersion(){
		return "<p>Version : " + api_version + "</p>";
	}	
	
	@Path("/database")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnDatabaseStatus() throws Exception{
		PreparedStatement query = null;
		String myString = null;
		String returnString = null;
		Connection conn = null;
		
		try{
			conn = OracleNolbu.OracleNolbuConn().getConnection();
			query = conn.prepareStatement("select to_char(sysdate, 'YYYY-MM-DD HH24:MI:SS') DATETIME From sys.dual");
			ResultSet rs = query.executeQuery();
			
			while(rs.next()){
				myString = rs.getString("DATETIME");
			}
			query.close();
			returnString = "<p>Database Startup</p>" +
													"<p>Database Date/Time return: " + myString + "</p>";
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			if(conn != null) conn.close();
		}
		
		return returnString;
	}
}
