package arm.instruction;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import arm.value.operand.ARMRegister;


import static java.util.Arrays.asList;


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
      
      
      Iterator<ARMRegister> regs = asList(this.registers).iterator();
      
      if (regs.hasNext())
         sb.append(regs.next().armString());
      
      while (regs.hasNext())
         sb.append(", ").append(regs.next().armString());
      
      
      return sb.append("}").toString();
   }
   
   
   @Override
   public List<ARMRegister> getSources()
   {
      return asList(this.registers);
   }
   
   
   @Override
   public List<ARMRegister> getTargets()
   {
      return new LinkedList<>();
   }
}
