package llvm.declaration;


import java.util.LinkedList;
import java.util.List;

import common.Options;
import common.Printer;

import llvm.value.variable.LLVMGlobal;


import arm.declaration.ARMGlobals;


public class LLVMGlobals
{
   public final List<LLVMGlobal> globals;
   
   
   public LLVMGlobals(List<LLVMGlobal> globals)
   {
      this.globals = globals;
   }
   
   
   public void writeLLVM(Printer printr)
   {
      for (LLVMGlobal global : this.globals)
         printr.println(this.llvmString(global));
      
      if (!this.globals.isEmpty())
         printr.println();
   }
   
   
   private String llvmString(LLVMGlobal global)
   {
      return new StringBuilder(global.llvmString())
            .append(" = common global ")
            .append(global.type.llvmString())
            .append(' ')
            .append(global.type.defaultValue().llvmString())
            .append(", align 4")
            .toString();
   }
   
   
   public ARMGlobals buildARM()
   {
      List<String> armGlobals = new LinkedList<>();
      
      for (LLVMGlobal global : this.globals)
         armGlobals.add(global.name);
      
      
      return new ARMGlobals(armGlobals);
   }
}
