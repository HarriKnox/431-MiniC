package arm.value;


public class ARMRegister extends ARMValue
{
   private int number = -1;
   
   
   public ARMRegister()
   {
      ;
   }
   
   
   public ARMRegister(int r)
   {
      this.number = r;
   }
}
