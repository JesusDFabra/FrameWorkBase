package framework.custom.procedures.fm_pattern_procedures;

import framework.custom.procedures.ConnectingDB;

public class ConnectingDBFactory extends ProcedureBase {
    public ConnectingDBFactory() {
        
    }

  @Override
  public IPerformableProcedure createProcedure() throws Exception {
    return new ConnectingDB();
  }
}
