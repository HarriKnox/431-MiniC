package llvm.declaration;


import java.util.List;

import common.Options;


public class LLVMFunctions
{
   public final List<LLVMFunction> functions;
   
   
   public LLVMFunctions(List<LLVMFunction> functions)
   {
      this.functions = functions;
   }
   
   
   public void writeLLVM()
   {
      for (LLVMFunction function : this.functions)
      {
         function.writeLLVM();
         System.out.println();
      }
   }
}
