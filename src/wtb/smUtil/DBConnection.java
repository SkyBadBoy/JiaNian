package wtb.smUtil;

import java.io.File;
import java.io.FileInputStream;  
import java.io.IOException;  
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.SQLException;  
import java.util.Properties;  

import com.mysql.jdbc.Driver;
import com.sun.jndi.url.corbaname.corbanameURLContextFactory;

  
@SuppressWarnings("unused")
public class DBConnection implements DBSource {  
    private Properties props;  
    private String url;  
    private String user;  
    private String password;  
    public DBConnection()throws IOException ,ClassNotFoundException{  
        this("jdbc.properties");  
    }  
    public DBConnection(String configFile)throws IOException,ClassNotFoundException{  
    	try {
        props = new Properties();  

        
        String rootPath=getClass().getResource("/").getFile().toString();
        rootPath=rootPath.replace("classes/", "")+configFile;
        
        props.load(new FileInputStream(rootPath));  
        url = props.getProperty("url");  
        user = props.getProperty("username");  
        password = props.getProperty("password");  
        System.err.println(props.getProperty("driver"));
        Class.forName(props.getProperty("driver"));  
    } catch (Exception e) {
		ErrorUtil.HandleError(null, "wtb.smUtil.DBConnection.DBConnection", e);
	}
    }  
  
    @Override  
    public void closeConnection(Connection conn) throws SQLException { 
    	try {
    		  conn.close();
		} catch (Exception e) {
			ErrorUtil.HandleError(null, "wtb.smUtil.DBConnection.closeConnection", e);
		}
        
    }  
    @Override  
    public Connection getConnection() throws SQLException {  
    	Connection Connection=null;
    	try {
    		 Connection= DriverManager.getConnection(url,user,password);
		} catch (Exception e) {
			ErrorUtil.HandleError(null, "wtb.smUtil.DBConnection.getConnection", e);
		}
    	
        return Connection;  
    }  
}  
