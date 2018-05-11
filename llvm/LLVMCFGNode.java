package llvm;


import java.io.PrintWriter;

import java.util.LinkedList;
import java.util.List;

import llvm.instruction.LLVMInstruction;

import llvm.link.LLVMBranch;
import llvm.link.LLVMJump;
import llvm.link.LLVMLink;
import llvm.link.LLVMRet;

import llvm.value.LLVMValue;

import llvm.value.variable.LLVMReturnValue;


public class LLVMCFGNode
{
   public final List<LLVMInstruction> instructions = new LinkedList<>();
   public final List<LLVMCFGNode> predecessors = new LinkedList<>();
   public LLVMCFGNode loopback = null;
   public LLVMLink link = null;
   public final boolean returned;
   
   
   private int uid = -1;
   
   private static int count = 0;
   
   
   public LLVMCFGNode(boolean returned)
   {
      this.returned = returned;
   }
   
   
   public LLVMCFGNode add(LLVMInstruction instruction)
   {
      this.instructions.add(instruction);
      
      return this;
   }
   
   
   public void jump(LLVMCFGNode target)
   {
      target.predecessors.add(this);
      
      this.link = new LLVMJump(target);
   }
   
   
   public void loopback(LLVMCFGNode target)
   {
      target.loopback = this;
      
      this.link = new LLVMJump(target);
   }
   
   
   public void branch(LLVMValue guard,
         LLVMCFGNode thenNode, LLVMCFGNode elseNode)
   {
      thenNode.predecessors.add(this);
      elseNode.predecessors.add(this);
      
      this.link = new LLVMBranch(guard, thenNode, elseNode);
   }
   
   
   public void ret(LLVMValue value)
   {
      this.link = new LLVMRet(value);
   }
   
   
   public void recursivisit(List<LLVMCFGNode> nodes)
   {
      if (nodes.contains(this))
         return;
      
      
      for (LLVMCFGNode parent : this.predecessors)
         parent.recursivisit(nodes);
      
      
      if (!nodes.contains(this))
      {
         nodes.add(this);
         this.getUID();
      }
      
      
      if (this.loopback != null)
         this.loopback.recursivisit(nodes);
   }
   
   
   public int getUID()
   {
      if (this.uid == -1)
         this.uid = count++;
      
      return this.uid;
   }
   
   
   public String llvmString()
   {
      return 'N' + Integer.toString(this.getUID());
   }
   
   
   public void writeLLVM(PrintWriter printer)
   {
      printer.println(this.llvmString() + ':');
      
      for (LLVMInstruction instruction : this.instructions)
      {
         printer.print("   ");
         printer.println(instruction.llvmString());
      }
      
      if (this.link != null)
      {
         printer.print("   ");
         printer.println(this.link.llvmString());
      }
   }
}
