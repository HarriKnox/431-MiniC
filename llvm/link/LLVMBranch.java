package llvm.link;


import java.io.PrintWriter;

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
   public void writeLLVM(PrintWriter printer)
   {
      printer.print("   br i1 ");
      printer.print(guard.llvmString());
      printer.print(", label %");
      printer.print(thenNode.llvmString());
      printer.print(", label %");
      printer.println(elseNode.llvmString());
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
