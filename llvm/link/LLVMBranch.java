package llvm.link;


import llvm.LLVMCFGNode;

import llvm.value.operand.LLVMOperand;

import arm.ARMCFGNode;


public class LLVMBranch extends LLVMLink
{
   public final LLVMOperand guard;
   public final boolean loop;
   public final LLVMCFGNode thenNode;
   public final LLVMCFGNode elseNode;
   
   
   public LLVMBranch(LLVMOperand guard, boolean loop,
         LLVMCFGNode thenNode, LLVMCFGNode elseNode)
   {
      this.guard = guard;
      this.loop = loop;
      this.thenNode = thenNode;
      this.elseNode = elseNode;
   }
   
   
   @Override
   public String llvmString()
   {
      return new StringBuilder("br i1 ")
            .append(guard.llvmString())
            .append(", label %")
            .append(thenNode.llvmString())
            .append(", label %")
            .append(elseNode.llvmString())
            .toString();
   }
   
   
   @Override
   public void buildARM(ARMCFGNode node)
   {
      
   }
}
