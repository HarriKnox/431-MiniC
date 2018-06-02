package arm.instruction;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import arm.value.operand.ARMRegister;


import static java.util.Arrays.asList;

import static java.util.Collections.singletonList;


public class ARMPop extends ARMInstruction
{
   public final ARMRegister[] registers;
   
   
   public ARMPop(ARMRegister reg)
   {
      this(new ARMRegister[]{reg});
   }
   
   
   public ARMPop(ARMRegister[] regs)
   {
      this.registers = regs;
   }
   
   
   @Override
   public List<String> armStrings(boolean spilled, int localCount)
   {
      if (spilled && this.registers.length == 1
            && !this.registers[0].isValid())
         return asList(new String[]{
               "pop {r9}",
               "str r9, [fp, #-" + this.registers[0].getSpillOffset(localCount)
                     + ']'});
      
      
      StringBuilder sb = new StringBuilder("pop {");
      
      
      Iterator<ARMRegister> regs = asList(this.registers).iterator();
      
      if (regs.hasNext())
         sb.append(regs.next().armString());
      
      while (regs.hasNext())
         sb.append(", ").append(regs.next().armString());
      
      
      return singletonList(sb.append("}").toString());
   }
   
   
   @Override
   public List<ARMRegister> getSources()
   {
      return new LinkedList<>();
   }
   
   
   @Override
   public List<ARMRegister> getTargets()
   {
      return asList(this.registers);
   }
}
