package llvm.value;


public class LLVMParameter
{
   public final String functionName;
   public final String identifier;
   
   
   public LLVMParameter(String funcName, String id)
   {
      this.functionName = funcName;
      this.identifier = id;
   }
   
   
   /* '%' + this.functionName + '.param.' + this.identifier */
}
