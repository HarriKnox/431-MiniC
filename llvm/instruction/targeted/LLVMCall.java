package llvm.instruction.targeted;


import java.util.Iterator;
import java.util.List;

import llvm.instruction.LLVMCallVoid;

import llvm.type.LLVMType;

import llvm.value.operand.LLVMOperand;


public class LLVMCall extends LLVMTargetedInstruction
{
   private final LLVMCallVoid call;
   
   
   public LLVMCall(String name, LLVMType type, List<LLVMOperand> arguments)
   {
      super(type);
      this.call = new LLVMCallVoid(name, type, arguments);
   }
   
   
   @Override
   protected String getInstruction()
   {
      return this.call.llvmString();
   }
   
   
   @Override
   public void buildARM(ARMCFGNode node)
   {
      /* Call */
      this.call.buildARM(node);
      
      
      /* Move the return value out of r0 and into a virtual register */
      node.add(new ARMMov(this.target.buildARM(), ARMRegister.R0));
   }
}
