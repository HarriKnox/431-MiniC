package llvm.value;


import llvm.type.LLVMType;
/*
import arm.ARMCFGNode;

import arm.value.ARMRegister;
*/

public abstract class LLVMValue
{
   public final LLVMType type;
   
   
   public LLVMValue(LLVMType type)
   {
      this.type = type;
   }
   
   
   public abstract String llvmString();
   
   public abstract ARMRegister buildARM(ARMCFGNode node);
   
   
   public String llvmTypedString()
   {
      return this.type.llvmString() + ' ' + this.llvmString();
   }
}
