package arm.instruction;


import java.util.LinkedList;
import java.util.List;

import arm.value.operand.ARMRegister;


import static java.util.Arrays.asList;

import static arm.value.operand.ARMRegister.R0;
import static arm.value.operand.ARMRegister.R1;
import static arm.value.operand.ARMRegister.R2;
import static arm.value.operand.ARMRegister.R3;
import static arm.value.operand.ARMRegister.IP;


public class ARMBl extends ARMInstruction
{
   public final String name;
   public final int argLen;
   
   
   public ARMBl(String name, int argLen)
   {
      this.name = name;
      this.argLen = argLen;
   }
   
   
   @Override
   public String armString()
   {
      return "bl " + this.name;
   }
   
   
   @Override
   public List<ARMRegister> getSources()
   {
      List<ARMRegister> sources = new LinkedList<>();
      
      
      if (argLen >= 1)
         sources.add(R0);
      
      if (argLen >= 2)
         sources.add(R1);
      
      if (argLen >= 3)
         sources.add(R2);
      
      if (argLen >= 4)
         sources.add(R3);
      
      
      return sources;
   }
   
   
   @Override
   public List<ARMRegister> getTargets()
   {
      return asList(new ARMRegister[]{R0, R1, R2, R3, IP});
   }
}
