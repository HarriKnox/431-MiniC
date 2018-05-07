package llvm.declaration;


import java.io.PrintWriter;

import java.util.List;

import common.Options;


public class LLVMStructs
{
   public final List<LLVMStruct> structs;
   
   
   public LLVMStructs(List<LLVMStruct> structs)
   {
      this.structs = structs;
   }
   
   
   public void writeLLVM(PrintWriter printer)
   {
      for (LLVMStruct struct : this.structs)
         printer.println(struct.llvmString());
      
      if (!this.structs.isEmpty())
         printer.println();
   }
}
