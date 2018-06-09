package llvm.value.operand.register;


import java.util.HashMap;
import java.util.Map;

import llvm.LLVMCFGNode;

import llvm.type.LLVMType;

import llvm.value.operand.LLVMOperand;

import arm.ARMCFGNode;

import arm.value.operand.ARMRegister;


public class LLVMPhi extends LLVMRegister
{
   public final Map<LLVMCFGNode, LLVMOperand> sources = new HashMap<>();
   
   
   private int uid = -1;
   
   private static int count = 0;
   
   
   public LLVMPhi(LLVMType type)
   {
      super(type);
   }
   
   
   @Override
   public String llvmString()
   {
      return "%p" + Integer.toString(this.getUID());
   }
   
   
   public int getUID()
   {
      if (this.uid == -1)
         this.uid = count++;
      
      return this.uid;
   }
   
   
   public void addSource(LLVMCFGNode node, LLVMOperand value)
   {
      this.sources.put(node, value);
   }
   
   
   @Override
   public ARMRegister buildARM(ARMCFGNode node)
   {
      return null;
   }
}
