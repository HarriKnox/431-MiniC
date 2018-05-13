package arm.value.global;


public class ARMGlobal extends ARMValue
{
   public final String name;
   
   
   public ARMGlobal(name)
   {
      this.name = name;
   }
   
   
   public static final READ_SCRATCH = new ARMGlobal(".read_scratch");
}
