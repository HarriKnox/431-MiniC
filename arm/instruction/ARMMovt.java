package arm.instruction;


import java.util.LinkedList;
import java.util.List;

import arm.value.immediate.ARMImmediate;

import arm.value.operand.ARMRegister;


import static java.util.Collections.singletonList;


public class ARMMovt extends ARMInstruction
{
   public final ARMRegister target;
   public final ARMImmediate value;
   
   
   public ARMMovt(ARMImmediate value)
   {
      this(new ARMRegister(), value);
   }
   
   
   public ARMMovt(ARMRegister target, ARMImmediate value)
   {
      this.target = target;
      this.value = value;
   }
   
   
   @Override
   public String armString()
   {
      return "movt " + this.target.armString()
            + ", " + this.value.upperARMString();
   }
   
   
   @Override
   public List<ARMRegister> getSources()
   {
      return new LinkedList<>();
   }
   
   
   @Override
   public List<ARMRegister> getTargets()
   {
      return singletonList(this.target);
   }
}
