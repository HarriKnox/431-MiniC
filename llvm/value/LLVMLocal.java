package llvm.value;


public class LLVMLocal
{
   public final String functionName;
   public final String identifier;
   
   
   public LLVMLocal(String funcName, String id)
   {
      this.functionName = funcName;
      this.identifier = id;
   }
   
   
   /* '%' + this.functionName + '.' + this.identifier */
}
