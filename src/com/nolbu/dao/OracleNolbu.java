package com.nolbu.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;



public class OracleNolbu {

	private static DataSource OracleNolbu = null;
	private static Context context = null;
	
	public static DataSource OracleNolbuConn() throws Exception{
		
		if(OracleNolbu != null){
			return OracleNolbu;
		}
		
		try{
			if(context == null){
				context = new InitialContext();
			}
			
			OracleNolbu = (DataSource)context.lookup("nolbuOracle");
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return OracleNolbu;
	}
}
