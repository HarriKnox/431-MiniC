package llvm.link;


import llvm.LLVMCFGNode;

import llvm.value.LLVMValue;


public class LLVMBranch extends LLVMLink
{
   public final LLVMValue guard;
   public final LLVMCFGNode thenNode;
   public final LLVMCFGNode elseNode;
   
   
   public LLVMBranch(LLVMValue guard,
         LLVMCFGNode thenNode, LLVMCFGNode elseNode)
   {
      this.guard = guard;
      this.thenNode = thenNode;
      this.elseNode = elseNode;
   }
}
