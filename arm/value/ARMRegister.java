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
}
