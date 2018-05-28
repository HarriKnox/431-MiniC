package arm.instruction;


import java.util.LinkedList;
import java.util.List;

import arm.ARMCFGNode;

import arm.value.operand.ARMAddress;
import arm.value.operand.ARMRegister;


import static java.util.Arrays.asList;


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
   
   
   @Override
   public List<ARMRegister> getSources()
   {
      return asList(new ARMRegister[]{this.source, this.target.getRegister()});
   }
   
   
   @Override
   public List<ARMRegister> getTargets()
   {
      return new LinkedList<>();
   }
}
