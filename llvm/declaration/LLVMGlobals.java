package llvm.declaration;


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
   
   
   public void writeLLVM(Options opts)
   {
      for (LLVMGlobal global : this.globals)
         System.out.println(new StringBuilder(global.llvmString())
            .append(" = common global ")
            .append(global.type.llvmString())
            .append(' ')
            .append(global.type.defaultValue())
            .append(", align 4")
            .toString());
   }
}
