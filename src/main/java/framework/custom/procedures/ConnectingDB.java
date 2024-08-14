package framework.custom.procedures;

import framework.custom.procedures.fm_pattern_procedures.IPerformableProcedure;

public class ConnectingDB implements IPerformableProcedure{
   public ConnectingDB() {
       
    }

  @Override
  public void executeCustomProcedure() throws Exception {
     // Aca va el codigo de la ejecucion del procedimiento.
        System.out.println("..................................................");
        System.out.println("|     Ejecutando conexion de base de datos...    |");
        System.out.println("..................................................");
  }
}
