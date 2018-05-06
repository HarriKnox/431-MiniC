package llvm;


import llvm.declaration.LLVMFunctions;
import llvm.declaration.LLVMGlobals;
import llvm.declaration.LLVMStructs;

import common.Options;


public class ProgramLLVM
{
   public final LLVMStructs llvmStructs;
   public final LLVMGlobals llvmGlobals;
   public final LLVMFunctions llvmFunctions;
   
   
   public ProgramLLVM(LLVMStructs structs,
         LLVMGlobals globals, LLVMFunctions functions)
   {
      this.llvmStructs = structs;
      this.llvmGlobals = globals;
      this.llvmFunctions = functions;
   }
   
   
   public void writeLLVM(Options opts)
   {
      try (BufferedWriter llvmOut = getLLVMOut(opts))
      {
         
      }
   }
   
   
   private BufferedWriter getLLVMOut(Options opts) throws IOException
   {
      if (opts.llvm
}
