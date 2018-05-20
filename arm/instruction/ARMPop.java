package arm.instruction;


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
}
