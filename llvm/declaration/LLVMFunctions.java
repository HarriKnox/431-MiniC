package llvm.declaration;


import java.io.BufferedWriter;
import java.io.IOException;

import java.util.List;

import common.Options;


public class LLVMFunctions
{
   public final List<LLVMFunction> functions;
   
   
   public LLVMFunctions(List<LLVMFunction> functions)
   {
      this.functions = functions;
   }
   
   
   public void writeLLVM(BufferedWriter llvmOut) throws IOException
   {
      for (LLVMFunction function : this.functions)
         function.writeLLVM(llvmOut);
   }
}
