package llvm.value.variable;


import llvm.type.LLVMType;

import llvm.value.operand.register.LLVMVirtual;

import arm.ARMCFGNode;

import arm.value.operand.ARMAddress;


public class LLVMField extends LLVMVariable
{
   public final LLVMVirtual base;
   public final int index;
   
   
   private int uid = -1;
   
   private static int count = 0;
   
   
   public LLVMField(LLVMType type, LLVMVirtual base, int index)
   {
      super(type);
      
      this.base = base;
      this.index = index;
   }
   
   
   @Override
   public String llvmString()
   {
      return "%f" + Integer.toString(this.getUID());
   }
   
   
   @Override
   public ARMAddress buildARM(ARMCFGNode node)
   {
      return new ARMAddress(this.base.buildARM(node), this.index * 4);
   }
   
   
   public int getUID()
   {
      if (this.uid == -1)
         this.uid = count++;
      
      return this.uid;
   }
}
