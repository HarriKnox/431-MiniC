package arm.instruction;


import java.util.LinkedList;
import java.util.List;

import arm.value.operand.ARMRegister;


public class ARMBl extends ARMInstruction
{
   public final String name;
   
   
   public ARMBl(String name)
   {
      this.name = name;
   }
   
   
   @Override
   public String armString()
   {
      return "bl " + this.name;
   }
   
   
   @Override
   public List<ARMRegister> getSources()
   {
      return new LinkedList<>();
   }
   
   
   @Override
   public List<ARMRegister> getTargets()
   {
      return new LinkedList<>();
   }
}
