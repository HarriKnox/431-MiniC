package arm.instruction;


import java.util.List;

import arm.value.operand.ARMRegister;


public abstract class ARMInstruction
{
   public abstract List<String> armStrings(boolean spilled, int localCount);
   
   public abstract List<ARMRegister> getSources();
   
   public abstract List<ARMRegister> getTargets();
}
