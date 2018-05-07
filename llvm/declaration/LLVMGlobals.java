package llvm.declaration;


import java.io.PrintWriter;

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
   
   
   public void writeLLVM(PrintWriter printer)
   {
      for (LLVMGlobal global : this.globals)
         printer.println(this.llvmString(global));
      
      if (!this.globals.isEmpty())
         printer.println();
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
