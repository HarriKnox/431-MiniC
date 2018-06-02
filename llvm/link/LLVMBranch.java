package llvm.link;


import llvm.LLVMCFGNode;

import llvm.value.operand.LLVMOperand;

import arm.ARMCFGNode;

import common.Printer;


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
   public void writeLLVM(Printer printr)
   {
      printr.print("   br i1 ")
            .print(guard.llvmString())
            .print(", label %")
            .print(thenNode.llvmString())
            .print(", label %")
            .println(elseNode.llvmString());
   }
   
   
   @Override
   public void buildARM(ARMCFGNode node)
   {
      node.branch(
            this.guard.buildARM(node),
            this.thenNode.armNode(),
            this.elseNode.armNode());
   }
}
