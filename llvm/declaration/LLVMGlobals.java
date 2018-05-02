package llvm.declaration;


import java.util.List;

import llvm.value.variable.LLVMGlobal;


public class LLVMGlobals
{
   public final List<LLVMGlobal> globals;
   
   
   public LLVMGlobals(List<LLVMGlobal> globals)
   {
      this.globals = globals;
   }
}
