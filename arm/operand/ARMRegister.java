package arm.operand;


public class ARMRegister extends ARMOperand
{
   private int number = -1;
   
   
   public ARMRegister()
   {
      ;
   }
   
   
   private ARMRegister(int r)
   {
      this.number = r;
   }
   
   
   public static final ARMRegister R0  = new ARMRegister(0);
   public static final ARMRegister R1  = new ARMRegister(1);
   public static final ARMRegister R2  = new ARMRegister(2);
   public static final ARMRegister R3  = new ARMRegister(3);
   public static final ARMRegister R4  = new ARMRegister(4);
   public static final ARMRegister R5  = new ARMRegister(5);
   public static final ARMRegister R6  = new ARMRegister(6);
   public static final ARMRegister R7  = new ARMRegister(7);
   public static final ARMRegister R8  = new ARMRegister(8);
   public static final ARMRegister R9  = new ARMRegister(9);
   public static final ARMRegister R10 = new ARMRegister(10);
   
   public static final ARMRegister RFP = new ARMRegister(11);
   public static final ARMRegister RIP = new ARMRegister(12);
   public static final ARMRegister RSP = new ARMRegister(13);
   public static final ARMRegister RLR = new ARMRegister(14);
   public static final ARMRegister RPC = new ARMRegister(15);
   
   
   public static ARMRegister getReal(int i)
   {
      switch (i)
      {
         case 0:  return R0;
         case 1:  return R1;
         case 2:  return R2;
         case 3:  return R3;
         case 4:  return R4;
         case 5:  return R5;
         case 6:  return R6;
         case 7:  return R7;
         case 8:  return R8;
         case 9:  return R9;
         case 10: return R10;
         
         case 11: return RFP;
         case 12: return RIP;
         case 13: return RSP;
         case 14: return RLR;
         case 15: return RPC;
      }
      
      return null;
   }
}