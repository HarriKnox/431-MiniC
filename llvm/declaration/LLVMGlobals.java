package llvm.declaration;


import java.io.BufferedWriter;
import java.io.IOException;

import java.util.List;

import common.Options;

import llvm.value.variable.LLVMGlobal;


public class LLVMGlobals
{
   public final List<LLVMGlobal> globals;
   
   
   public LLVMGlobals(List<LLVMGlobal> globals)
   {
      this.globals = globals;
   }
   
   
   public void writeLLVM(BufferedWriter llvmOut) throws IOException
   {
      for (LLVMGlobal global : this.globals)
      {
         llvmOut.write(this.llvmString(global));
         llvmOut.newLine();
      }
   }
   
   
   private String llvmString(LLVMGlobal global)
   {
      return new StringBuilder(global.llvmString())
            .append(" = common global ")
            .append(global.type.llvmString())
            .append(' ')
            .append(global.type.defaultValue())
            .append(", align 4")
            .toString();
   }
}
