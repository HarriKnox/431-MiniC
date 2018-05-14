package arm.instruction;


public class ARMPush extends ARMInstruction;
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
