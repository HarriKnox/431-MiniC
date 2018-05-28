package arm.instruction;


import java.util.List;

import arm.ARMCFGNode;

import arm.value.operand.ARMAddress;
import arm.value.operand.ARMRegister;


import static java.util.Collections.singletonList;


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
   
   
   @Override
   public String armString()
   {
      return "ldr " + this.target.armString() + ", ["
            + this.source.armString() + ']';
   }
   
   
   @Override
   public List<ARMRegister> getSources()
   {
      return singletonList(this.source.getRegister());
   }
   
   
   @Override
   public List<ARMRegister> getTargets()
   {
      return singletonList(this.target);
   }
}
