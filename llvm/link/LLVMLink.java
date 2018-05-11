package llvm.link;


import llvm.LLVMCFGNode;


public abstract class LLVMLink
{
   public abstract String llvmString();
   
   public abstract void replace(LLVMCFGNode from, LLVMCFGNode to);
}
