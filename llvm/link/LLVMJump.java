package llvm.link;


import llvm.LLVMCFGNode;

import arm.ARMCFGNode;

import common.Printer;


public class LLVMJump extends LLVMLink
{
   public final LLVMCFGNode target;
   public final boolean loop;
   
   
   public LLVMJump(LLVMCFGNode target, boolean loop)
   {
      this.target = target;
      this.loop = loop;
   }
   
   
   @Override
   public void writeLLVM(Printer printr)
   {
      printr.print("   br label %").println(this.target.llvmString());
   }
   
   
   @Override
   public void buildARM(ARMCFGNode node)
   {
      if (this.loop)
         node.loopback(this.target.armNode());
      
      else
         node.jump(this.target.armNode());
   }
}
