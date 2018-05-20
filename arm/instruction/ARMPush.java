package arm.instruction;


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
}
