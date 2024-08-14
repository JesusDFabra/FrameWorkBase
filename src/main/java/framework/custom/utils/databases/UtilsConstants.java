package framework.custom.utils.databases;

public class UtilsConstants {

    private UtilsConstants(){}

    //Mensajes error
    public static final String CONNECTION_FAILURE = "Error: no se estableci� la conexion - mensaje: ";
    public static final String CONNECTION_NOT_CLOSED = "Error: no se cerr� la conexion - mensaje: ";
    public static final String DRIVER_NOT_FOUND = "Error: no encontr� el driver - mensaje: ";
    
    //Base de datos H2
    public static final String CONN_STR_H2 = AppProperties.getConnectionStringH2();
    public static final String USER_H2 = AppProperties.getUserH2();
    public static final String PASSWORD_H2 = AppProperties.getPasswordH2();
    
    //Base de datos SQL Server
    public static final String HOSTNAME_SQLServer = AppProperties.getHostNameSQLServer();
    public static final String DATABASE_NAME_SQLServer = AppProperties.getDatabaseNameSQLServer();
    public static final String USER_SQLServer = AppProperties.getUserSQLServer();
    public static final String PASSWORD_SQLServer = AppProperties.getPasswordSQLServer();
}
