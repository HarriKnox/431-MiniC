package llvm.declaration;


import java.util.List;


public class LLVMGlobals
{
   public final List<LLVMGlobal> globals;
   
   
   public LLVMGlobals(List<LLVMGlobal> globals)
   {
      this.globals = globals;
   }
}
