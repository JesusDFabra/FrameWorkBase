package framework.custom.utils.databases;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

import static framework.custom.utils.databases.UtilsConstants.CONNECTION_FAILURE;
import static framework.custom.utils.databases.UtilsConstants.DATABASE_NAME_SQLServer;
import static framework.custom.utils.databases.UtilsConstants.HOSTNAME_SQLServer;
import static framework.custom.utils.databases.UtilsConstants.PASSWORD_SQLServer;
import static framework.custom.utils.databases.UtilsConstants.USER_SQLServer;

public class ConexionSQLServer {
	
	Logger logger = Logger.getLogger(ConexionSQLServer.class.getName());

    public static ConexionSQLServer util(){return new ConexionSQLServer();}

    public Connection conectar(){

        Connection connection=null;
        try {
            GestorConexiones gc = new GestorConexiones();
            gc.crearConexionSQL(HOSTNAME_SQLServer,DATABASE_NAME_SQLServer,USER_SQLServer, PASSWORD_SQLServer);
            connection = gc.getConnection();
        } catch(Exception e) {
            logger.log(Level.SEVERE,() ->CONNECTION_FAILURE + e.getMessage());
        }
        return connection;
    }
    public void desconectar( Connection connection){
        GestorConexiones.util().closeConnection(connection);
    }

}
