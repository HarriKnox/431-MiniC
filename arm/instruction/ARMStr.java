package arm.instruction;


import arm.ARMCFGNode;

import arm.value.operand.ARMAddress;
import arm.value.operand.ARMRegister;


public class ARMStr extends ARMInstruction
{
   public final ARMRegister source;
   public final ARMAddress target;
   
   
   public ARMStr(ARMRegister source, ARMAddress target)
   {
      this.source = source;
      this.target = target;
   }
   
   
   @Override
   public String armString()
   {
      return "str " + this.source.armString() + ", ["
            + this.target.armString() + "]";
   }
}
