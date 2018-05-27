package llvm.link;


import java.io.PrintWriter;

import llvm.LLVMCFGNode;

import arm.ARMCFGNode;


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
   public void writeLLVM(PrintWriter printer)
   {
      printer.print("   br label %");
      printer.println(this.target.llvmString());
   }
   
   
   @Override
   public void buildARM(ARMCFGNode node)
   {
      
   }
}
