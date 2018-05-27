package arm.value.operand;


public class ARMRegister extends ARMOperand
{
   private boolean virtual;
   private int number;
   
   private static int count = 0;
   
   
   public ARMRegister()
   {
      this(-1, true);
   }
   
   
   public ARMRegister(int r)
   {
      this(r, false);
   }
   
   
   private ARMRegister(int r, boolean v)
   {
      this.number = r;
      this.virtual = v;
   }
   
   
   public static final ARMRegister FP = new ARMRegister(11);
   public static final ARMRegister IP = new ARMRegister(12);
   public static final ARMRegister SP = new ARMRegister(13);
   public static final ARMRegister LR = new ARMRegister(14);
   public static final ARMRegister PC = new ARMRegister(15);
   
   
   private int getNumber()
   {
      if (this.number == -1)
         this.number = count++;
      
      return this.number;
   }
   
   
   @Override
   public String armString()
   {
      if (this.virtual)
         return "%v" + this.getNumber();
      
      
      switch (this.number)
      {
         case 11: return "fp";
         case 12: return "ip";
         case 13: return "sp";
         case 14: return "lr";
         case 15: return "pc";
      }
      
      return 'r' + Integer.toString(this.number);
   }
}
