package arm.instruction;


import java.util.LinkedList;
import java.util.List;

import arm.value.immediate.ARMImmediate;

import arm.value.operand.ARMRegister;


import static java.util.Arrays.asList;

import static java.util.Collections.singletonList;


public class ARMMovw extends ARMInstruction
{
   public final ARMRegister target;
   public final ARMImmediate value;
   
   
   public ARMMovw(ARMImmediate value)
   {
      this(new ARMRegister(), value);
   }
   
   
   public ARMMovw(ARMRegister target, ARMImmediate value)
   {
      this.target = target;
      this.value = value;
   }
   
   
   @Override
   public List<String> armStrings(boolean spilled, int localCount)
   {
      boolean targetValid = this.target.isValid();
      
      if (!spilled || targetValid)
         return singletonList("movw " + this.target.armString()
               + ", " + this.value.lowerARMString());
      
      
      return asList(new String[]{
            "movw r10, " + this.value.lowerARMString(),
            "ldr r10, [fp, #-" + this.target.getSpillOffset(localCount)
                  + ']'});
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
