package arm.instruction;


import arm.ARMCFGNode;

import arm.value.operand.ARMAddress;
import arm.value.operand.ARMRegister;


public class ARMLdr extends ARMInstruction
{
   public final ARMRegister target;
   public final ARMAddress source;
   
   
   public ARMLdr(ARMAddress source)
   {
      this(new ARMRegister(), source);
   }
   
   
   public ARMLdr(ARMRegister target, ARMAddress source)
   {
      this.target = target;
      this.source = source;
   }
}
