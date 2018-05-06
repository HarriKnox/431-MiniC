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
}
