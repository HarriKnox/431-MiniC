package arm.instruction;


import java.util.Arrays;
import java.util.Iterator;

import arm.value.operand.ARMRegister;


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
   public String armString()
   {
      StringBuilder sb = new StringBuilder("pop {");
      
      
      Iterator<ARMRegister> regs = Arrays.asList(this.registers).iterator();
      
      if (regs.hasNext())
         sb.append(regs.next().armString());
      
      while (regs.hasNext())
         sb.append(", ").append(regs.next().armString());
      
      
      return sb.append("}").toString();
   }
}
