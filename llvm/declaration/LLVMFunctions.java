package llvm.declaration;


import java.io.PrintWriter;

import java.util.LinkedList;
import java.util.List;

import common.Options;

import arm.declaration.ARMFunction;
import arm.declaration.ARMFunctions;


public class LLVMFunctions
{
   public final List<LLVMFunction> functions;
   
   
   public LLVMFunctions(List<LLVMFunction> functions)
   {
      this.functions = functions;
   }
   
   
   public void writeLLVM(PrintWriter printer)
   {
      for (LLVMFunction function : this.functions)
      {
         function.writeLLVM(printer);
         printer.println();
      }
   }
   
   
   public ARMFunctions buildARM()
   {
      List<ARMFunction> armFunctions = new LinkedList<>();
      
      for (LLVMFunction function : this.functions)
         armFunctions.add(function.buildARM());
      
      
      return new ARMFunctions(armFunctions);
   }
}
