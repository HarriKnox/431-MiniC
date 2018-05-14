package arm.value;


public class ARMRegister extends ARMValue
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
   public static final ARMRegister R11 = new ARMRegister(11);
   public static final ARMRegister R12 = new ARMRegister(12);
   public static final ARMRegister R13 = new ARMRegister(13);
   public static final ARMRegister R14 = new ARMRegister(14);
   public static final ARMRegister R15 = new ARMRegister(15);
   
   
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
         case 11: return R11;
         case 12: return R12;
         case 13: return R13;
         case 14: return R14;
         case 15: return R15;
      }
      
      return null;
   }
}
