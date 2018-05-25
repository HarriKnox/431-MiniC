package arm.instruction;


import java.util.Arrays;
import java.util.Iterator;

import arm.value.operand.ARMRegister;


public class ARMPush extends ARMInstruction
{
   public final ARMRegister[] registers;
   
   
   public ARMPush(ARMRegister reg)
   {
      this(new ARMRegister[]{reg});
   }
   
   
   public ARMPush(ARMRegister[] regs)
   {
      this.registers = regs;
   }
   
   
   @Override
   public String armString()
   {
      StringBuilder sb = new StringBuilder("push {");
      
      
      Iterator<ARMRegister> regs = Arrays.asList(this.registers).iterator();
      
      if (regs.hasNext())
         sb.append(regs.next().armString());
      
      while (regs.hasNext())
         sb.append(", ").append(regs.next().armString());
      
      
      return sb.append("}").toString();
   }
}
