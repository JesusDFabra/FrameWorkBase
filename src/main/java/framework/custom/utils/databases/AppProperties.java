package framework.custom.utils.databases;

import framework.dataProviders.ConfigFileReader;

public class AppProperties {

    private AppProperties(){}

    static ConfigFileReader reader = new ConfigFileReader("configs/config.properties");
    
    //Base de datos H2
    public static String getConnectionStringH2() {
    	return reader.getPropertyByKey("connectionStringH2");
    }
    
    public static String getUserH2() {
    	return reader.getPropertyByKey("userH2");
    }
    
    public static String getPasswordH2() {
    	return reader.getPropertyByKey("passwordH2");
    }
    
  //Base de datos SQL Server
    
    public static String getHostNameSQLServer() {
    	return reader.getPropertyByKey("hostNameSQLServer");
    }
    public static String getDatabaseNameSQLServer() {
    	return reader.getPropertyByKey("dataBaseNameSQLServer");
    }
    
    public static String getUserSQLServer() {
    	return reader.getPropertyByKey("userSQLServer");
    }
    
    public static String getPasswordSQLServer() {
    	return reader.getPropertyByKey("passwordSQLServer");
    }

}
