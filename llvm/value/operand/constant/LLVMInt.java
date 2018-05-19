package llvm.value.operand.constant;


import llvm.type.LLVMIntType;

import arm.ARMCFGNode;

import arm.value.immediate.ARMInt;

import arm.value.operand.ARMRegister;


public class LLVMInt extends LLVMConstant
{
   public final int value;
   
   
   public LLVMInt(int value)
   {
      super(new LLVMIntType());
      
      this.value = value;
   }
   
   
   @Override
   public String llvmString()
   {
      return Integer.toString(this.value);
   }
   
   
   @Override
   public ARMRegister buildARM(ARMCFGNode node)
   {
      ARMMovw movw = new ARMMovw(new ARMInt(this.value));
      
      node.add(movw);
      
      
      if ((this.value &~ 0xffff) != 0)
      {
         ARMMovt movt = new ARMMovt(movw.target, movw.value);
         
         node.add(movt);
      }
      
      
      return movw.target;
   }
}
