package llvm.declaration;


import java.util.List;

import common.Options;
import common.Printer;


public class LLVMStructs
{
   public final List<LLVMStruct> structs;
   
   
   public LLVMStructs(List<LLVMStruct> structs)
   {
      this.structs = structs;
   }
   
   
   public void writeLLVM(Printer printr)
   {
      for (LLVMStruct struct : this.structs)
         printr.println(struct.llvmString());
      
      if (!this.structs.isEmpty())
         printr.println();
   }
}
