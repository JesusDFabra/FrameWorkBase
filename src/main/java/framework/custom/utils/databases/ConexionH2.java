package framework.custom.utils.databases;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

import static framework.custom.utils.databases.UtilsConstants.CONNECTION_FAILURE;
import static framework.custom.utils.databases.UtilsConstants.CONN_STR_H2;
import static framework.custom.utils.databases.UtilsConstants.PASSWORD_H2;
import static framework.custom.utils.databases.UtilsConstants.USER_H2;


public class ConexionH2 {

	Logger logger=Logger.getLogger(ConexionH2.class.getName());

    public static ConexionH2 util(){return new ConexionH2();}

    public Connection conectar(){

        Connection connection = null;
        try {
            GestorConexiones gc = new GestorConexiones();
            gc.crearConexionH2(CONN_STR_H2,USER_H2,PASSWORD_H2);
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
