package llvm.declaration;


import java.io.BufferedWriter;
import java.io.IOException;

import java.util.List;

import common.Options;


public class LLVMStructs
{
   public final List<LLVMStruct> structs;
   
   
   public LLVMStructs(List<LLVMStruct> structs)
   {
      this.structs = structs;
   }
   
   
   public void writeLLVM(BufferedWriter llvmOut) throws IOException
   {
      for (LLVMStruct struct : this.structs)
      {
         llvmOut.write(struct.llvmString());
         llvmOut.newLine();
      }
   }
}
