package llvm.link;


import llvm.LLVMCFGNode;


public class LLVMJump extends LLVMLink
{
   public final LLVMCFGNode target;
   
   
   public LLVMJump(LLVMCFGNode target)
   {
      this.target = target;
   }
   
   
   @Override
   public String llvmString()
   {
      return "br label %" + this.target.llvmString();
   }
   
   
   @Override
   public void replace(LLVMCFGNode from, LLVMCFGNode to)
   {
      if (this.target.equals(from))
         this.target = to;
   }
}
