package llvm.declaration;


import java.util.List;

import common.Options;


public class LLVMStructs
{
   public final List<LLVMStruct> structs;
   
   
   public LLVMStructs(List<LLVMStruct> structs)
   {
      this.structs = structs;
   }
   
   
   public void writeLLVM(Options opts)
   {
      for (LLVMStruct struct : this.structs)
         System.out.println(struct.llvmString());
   }
}
