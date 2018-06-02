package llvm.declaration;


import java.util.LinkedList;
import java.util.List;

import common.Options;
import common.Printer;

import arm.declaration.ARMFunction;
import arm.declaration.ARMFunctions;


public class LLVMFunctions
{
   public final List<LLVMFunction> functions;
   
   
   public LLVMFunctions(List<LLVMFunction> functions)
   {
      this.functions = functions;
   }
   
   
   public void writeLLVM(Printer printr)
   {
      for (LLVMFunction function : this.functions)
      {
         function.writeLLVM(printr);
         printr.println();
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
