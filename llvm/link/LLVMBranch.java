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
   public void replace(LLVMCFGNode from, LLVMCFGNode to)
   {
      if (this.thenNode.equals(from))
         this.thenNode = to;
      
      if (this.elseNode.equals(from))
         this.elseNode = to;
   }
}
