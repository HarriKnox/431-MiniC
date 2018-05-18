package arm.operand.constant.label;


public class ARMGlobal extends ARMLabel
{
   public final String name;
   
   
   public ARMGlobal(name)
   {
      this.name = name;
   }
   
   
   public static final READ_SCRATCH = new ARMGlobal(".read_scratch");
}
