package ee.degeetia.dumonitor.storage;

import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;
import java.util.Enumeration;

import java.sql.*;
import org.json.*;


public class Store extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	
	// acceptable keys: identical to settable database fields
	public String[] inKeys = {"personcode","action",
		 "sender","receiver","restrictions",
		 "sendercode","receivercode","actioncode",
		 "xroadrequestid","xroadservice","usercode"};
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
	throws ServletException, IOException {
		boolean ok;
		
		resp.setContentType("text/plain");
		Map<String, String> inParams = new HashMap(); // parsed params stored here
		ServletOutputStream os = resp.getOutputStream();
		ok=parseCgi(req, resp, os, inParams); // parse input as cgi key=value parameters		
		if (ok && inParams!=null) {
			handleStoreParams(req, resp, os, inParams);
			os.flush();
		  os.close();	
		}	
  }
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		boolean ok;
		
		resp.setContentType("text/plain");
    Map<String, String> inParams = new HashMap(); // parsed params stored here		
		ServletOutputStream os = resp.getOutputStream();				
		String ctype = req.getHeader("Content-Type");		
		if (ctype==null || !ctype.equals("application/json")) {
			// parse input as cgi key=value parametets
			//os.println("not json");
			ok=parseCgi(req, resp, os, inParams);
		}	else {		
			// parse input as json
			//os.println("is json");
			ok=parseJson(req, resp, os, inParams);							
		}
		if (ok && inParams!=null) {
			handleStoreParams(req, resp, os, inParams);
			os.flush();
		  os.close();	
		}	
	}
	
	
  private void showError(int code, String msg, 
	       HttpServletResponse resp, ServletOutputStream os) 
	throws ServletException, IOException {
		//resp.sendError(resp.SC_BAD_REQUEST, msg); // possible alternative to json output
		os.println("{\"errcode\":"+code+", \"errmessage\":\""+msg+"\"}");
		os.flush();
		os.close();					
	}
	
	private void showOK(HttpServletResponse resp, ServletOutputStream os) 
	throws ServletException, IOException {		
		os.println("{\"ok\":1}");
		os.flush();
		os.close();					
	}
		

	/*
	 *  Parse input as cgi key=value parameters  
	 */

	private boolean parseCgi(HttpServletRequest req, HttpServletResponse resp, 
	       ServletOutputStream os, Map<String, String> inParams) 
	throws ServletException, IOException {
		String key,inp;
		
		for (int i=0; i<inKeys.length; i++) {
			key=inKeys[i];
			inParams.put(key, null);	// default value is null	
			inp = req.getParameter(key); // cgi parameter
			inParams.put(key, inp);	
		}	
    return true;		
	}
	
	/*
	 *  Parse input as json {"key":"value"} parameters
	 */
	
	private boolean parseJson(HttpServletRequest req, HttpServletResponse resp, 
	       ServletOutputStream os, Map<String, String> inParams) 
	throws ServletException, IOException {
		String inp, key;
		JSONObject jsonObject; 
	
		for (int i=0; i<inKeys.length; i++) inParams.put(inKeys[i], null);		
		Set<String> set = inParams.keySet();
    Iterator iter = set.iterator();  
			
    StringBuffer jb = new StringBuffer();
		try {							
			BufferedReader reader = req.getReader();
			String line;
			while ((line = reader.readLine()) != null)
				jb.append(line);
		} catch (Exception e) { 
			//throw new IOException("Error reading request string");
      showError(1, "cannot read input", resp, os);			
      return false;			
		} 
		try {			
			jsonObject = new JSONObject(jb.toString());		
			for (int i=0; i<inKeys.length; i++) {
				key=inKeys[i];
				inParams.put(key, null);	// default value is null					
				if (jsonObject.has(key)) {
					inp = (String) jsonObject.getString(key); // json parameter
				  if (inp!=null) inParams.put(key, inp);	
				}	
		  }									  		
		} catch (Exception e) {				
			//throw new IOException("Error parsing JSON request string");
			showError(2, "failed to parse input json: "+e.getMessage(), resp, os);
			return false;	
		}				
    return true;
	}
	
	/*
	 *  Store parsed parameters passed as hashmap  
	 */
	
	private void handleStoreParams(HttpServletRequest req, HttpServletResponse resp, 
	       ServletOutputStream os, Map<String, String> inParams) 
	throws ServletException, IOException {												
		
		// check whether the driver present
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			showError(3, "failed to find the PostgreSQL JDBC Driver", resp, os);
			return;
		}
		
		// create db connection
		
		/*
		String url = "jdbc:postgresql://localhost/test";
		Properties props = new Properties();
		props.setProperty("user","fred");
		props.setProperty("password","secret");
		props.setProperty("ssl","true");
		Connection conn = DriverManager.getConnection(url, props);
		String url = "jdbc:postgresql://localhost/test?user=fred&password=secret&ssl=true";
		Connection conn = DriverManager.getConnection(url);
		*/
    Connection conn=null;
		try {
			conn = DriverManager.getConnection(
				 "jdbc:postgresql://localhost/aj",
				 "ajuser",
				 "aj22p" );
		} catch(Exception e) {
			showError(4, "failed to connect to the database", resp, os);
			return;
		}	
		
		// insert data
		
		try {									
			String sql = "insert into ajlog"
			+ "(personcode,action,"
			+ "sender,receiver,restrictions,sendercode,receivercode,"
			+ "actioncode,xroadrequestid,xroadservice,usercode)"
			+ " values "
			+ "(?,?, ?,?,?,?,?, ?,?,?,?)";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			for (int i=0; i<inKeys.length; i++) {
				if (inParams.get(inKeys[i])!=null) {
					preparedStatement.setString(i+1, inParams.get(inKeys[i]));				  
        } else {
					preparedStatement.setNull(i+1, java.sql.Types.VARCHAR);
				}
			}	

			// execute insert SQL stetement
			int n = preparedStatement.executeUpdate();			
			if (n!=1) {
				showError(6, "record not stored", resp, os);
			} else {
				showOK(resp,os);
			}
				  
		} catch(Exception e) {
			showError(5, "database storage error: "+e.getMessage(), resp, os);
			return;		
		} finally {
				//It's important to close the connection when you are done with it
				try { conn.close(); } catch (Throwable ignore) { 
					/* Propagate the original exception
		      instead of this one that you may want just logged */ }
		}
	}	
	
	/*
	 *  Query from database  
	 */
	
	private void handleQueryParams(HttpServletRequest req, HttpServletResponse resp, 
	       ServletOutputStream os, Map<String, String> inParams) 
	throws ServletException, IOException {
		String inp,x;
		
		resp.setContentType("text/plain");		
		os.println("Hello world 23!");
			
		Set<String> set = inParams.keySet();
    Iterator iter = set.iterator();
		for(String key: set) {		
			if (inParams.get(key)!=null) {
				os.println(key + " - " + inParams.get(key));	
			}	
		}
		
		// store inParams to database using jdbc
		
		// check whether the driver present
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			showError(3, "failed to find the PostgreSQL JDBC Driver", resp, os);
			return;
		}
		
		// create db connection
		
		/*
		String url = "jdbc:postgresql://localhost/test";
		Properties props = new Properties();
		props.setProperty("user","fred");
		props.setProperty("password","secret");
		props.setProperty("ssl","true");
		Connection conn = DriverManager.getConnection(url, props);
		String url = "jdbc:postgresql://localhost/test?user=fred&password=secret&ssl=true";
		Connection conn = DriverManager.getConnection(url);
		*/
    Connection conn=null;
		try {
			conn = DriverManager.getConnection(
				 "jdbc:postgresql://localhost/aj",
				 "ajuser",
				 "aj22p" );
		} catch(Exception e) {
			showError(4, "failed to connect to the database", resp, os);
			return;
		}	
		
		// query
		
    try {				
	    PreparedStatement ps = conn.prepareStatement( "SELECT t1, t2 from tst" );
			os.println("conn ok");					
			ResultSet rs = ps.executeQuery();
      os.println("rs ok");
			while ( rs.next() ) {
				int numColumns = rs.getMetaData().getColumnCount();
				for ( int i = 1 ; i <= numColumns ; i++ ) {
					// Column numbers start at 1.
					// Also there are many methods on the result set to return
					// the column as a particular type. Refer to the Sun documentation
					// for the list of valid conversions.
					os.println( "COLUMN " + i + " = " + rs.getObject(i) );
				} 
			} 			
		} catch(Exception e) {
			showError(5, "database query error: "+e.getMessage(), resp, os);
			return;		
		} finally {
				//It's important to close the connection when you are done with it
				try { conn.close(); } catch (Throwable ignore) { 
					/* Propagate the original exception
		      instead of this one that you may want just logged */ }
		}	
	}	
	
}
