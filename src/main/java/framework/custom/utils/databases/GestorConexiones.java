package framework.custom.utils.databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import static framework.custom.utils.databases.UtilsConstants.CONNECTION_FAILURE;
import static framework.custom.utils.databases.UtilsConstants.CONNECTION_NOT_CLOSED;

public class GestorConexiones {

    Logger logger = Logger.getLogger(GestorConexiones.class.getName());
    private Connection conexion;


    public static GestorConexiones util(){return new GestorConexiones();}

    public Connection getConnection() {
        return conexion;
    }

    public void setConnection(Connection con) {
        conexion = con;
    }

    public void crearConexionDb2(String strCon, String usr, String pwd) {
        try {
            Class.forName("com.ibm.as400.access.AS400JDBCDriver");
            conexion = DriverManager.getConnection(strCon, usr, pwd);
        } catch (ClassNotFoundException | SQLException e) {
            logger.log(Level.SEVERE,() -> CONNECTION_FAILURE + e.getMessage());
        }
    }

    public void crearConexionOracle(String strCon, String usr, String pwd) throws SQLException {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conexion = DriverManager.getConnection(strCon, usr, pwd);
        } catch (ClassNotFoundException | SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage());
        }
    }
    
    public void crearConexionH2(String strCon, String usr, String pwd){
    	try {
			Properties connectionProps = new Properties();
			connectionProps.put("user", usr);
			connectionProps.put("password", pwd);
			connectionProps.put("serverTimezone","UTC");
			
			conexion = DriverManager.getConnection(strCon, connectionProps);
    		
		} catch (Exception ex) {
			logger.log(Level.SEVERE, ex.getMessage());
		}
    }

    public void crearConexionDB(String strCon, String driver) {
        try {
            Class.forName(driver);
            conexion = DriverManager.getConnection(strCon);
        } catch (ClassNotFoundException | SQLException ex) {
            logger.log(Level.SEVERE,() -> CONNECTION_FAILURE + ex.getMessage());
        }
    }

    public void crearConexionSQL(String hostName, String dbName, String user, String password) {
        try {
        	String strConnString = "jdbc:sqlserver://%s;databaseName=%s;user=%s;password=%s;";
            conexion = DriverManager.getConnection(String.format(strConnString, hostName, dbName, user, password));
        } catch (SQLException e) {
            logger.log(Level.SEVERE,() -> CONNECTION_FAILURE + e.getMessage());
        }
    }

    public void closeConnection( Connection connection){
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE,() ->CONNECTION_NOT_CLOSED + e.getMessage());
        }
    }
    
    
}
