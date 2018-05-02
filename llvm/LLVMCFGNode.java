package llvm;


import java.util.LinkedList;
import java.util.List;

import llvm.instruction.LLVMInstruction;

import llvm.instruction.link.LLVMBranch;
import llvm.instruction.link.LLVMJump;
import llvm.instruction.link.LLVMLink;
import llvm.instruction.link.LLVMRet;

import llvm.value.LLVMValue;

import llvm.value.variable.LLVMReturnValue;


public class LLVMCFGNode
{
   public final List<LLVMInstruction> instructions = new LinkedList<>();
   public final List<LLVMCFGNode> predecessors = new LinkedList<>();
   public final LLVMCFGNode loopback = null;
   public final LLVMLink link = null;
   
   
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
   
   
   public void ret(LLVMReturnValue returnValue)
   {
      this.link = new LLVMRet(returnValue);
   }
   
   
   public void recursivisit(List<LLVMCFGNode> nodes)
   {
      if (nodes.contains(this))
         return;
      
      
      for (LLVMCFGNode parent : this.predecessors)
         parent.recursivisit(nodes);
      
      
      if (!nodes.contains(this))
         nodes.add(this);
      
      
      if (this.loopback != null)
         this.loopback.recursivisit(nodes);
   }
}
