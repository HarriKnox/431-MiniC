package arm;


import java.io.PrintWriter;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import arm.instruction.ARMInstruction;

import arm.link.ARMBranch;
import arm.link.ARMJump;
import arm.link.ARMLink;

import arm.value.operand.ARMRegister;


public class ARMCFGNode
{
   public final List<ARMInstruction> instructions = new LinkedList<>();
   public final List<ARMCFGNode> predecessors = new LinkedList<>();
   public ARMCFGNode loopback = null;
   public ARMLink link = null;
   
   public Set<ARMRegister> genSet = new HashSet<>();
   public Set<ARMRegister> killSet = new HashSet<>();
   public Set<ARMRegister> liveSet = new HashSet<>();
   
   private int uid = -1;
   
   private static int count = 0;
   
   
   
   public void setUID()
   {
      if (this.uid == -1)
         this.uid = count++;
   }
   
   
   public ARMCFGNode add(ARMInstruction instruction)
   {
      this.instructions.add(instruction);
      
      return this;
   }
   
   
   public void jump(ARMCFGNode target)
   {
      target.predecessors.add(this);
      
      this.link = new ARMJump(target, false);
   }
   
   
   public void loopback(ARMCFGNode target)
   {
      target.loopback = this;
      
      this.link = new ARMJump(target, true);
   }
   
   
   public void branch(ARMRegister guard,
         ARMCFGNode thenNode, ARMCFGNode elseNode)
   {
      thenNode.predecessors.add(this);
      elseNode.predecessors.add(this);
      
      this.link = new ARMBranch(guard, false, thenNode, elseNode);
   }
   
   
   public String armString()
   {
      return ".N" + this.uid;
   }
   
   
   public void writeARM(PrintWriter printer)
   {
      if (this.uid != -1)
      {
         printer.print(this.armString());
         printer.println(':');
      }
      
      
      for (ARMInstruction instruction : this.instructions)
      {
         printer.print("   ");
         printer.println(instruction.armString());
      }
      
      
      if (this.link != null)
         this.link.writeARM(printer);
   }
   
   
   public void makeGenKillSets()
   {
      for (ARMInstruction instruction : this.instructions)
      {
         for (ARMRegister source : instruction.getSources())
            if (!this.killSet.contains(source))
               this.genSet.add(source);
         
         for (ARMRegister target : instruction.getTargets())
            this.killSet.add(target);
      }
   }
   
   
   public boolean updateLiveSet()
   {
      Set<ARMRegister> newLiveSet = new HashSet<>();
      
      
      for (ARMCFGNode successor : this.getSuccessors())
      {
         Set<ARMRegister> successorLOSet = new HashSet<>();
         
         successorLOSet.addAll(successor.liveSet);
         
         successorLOSet.removeAll(successor.killSet);
         
         successorLOSet.addAll(successor.genSet);
         
         newLiveSet.addAll(successorLOSet);
      }
      
      
      if (this.liveSet.equals(newLiveSet))
         return false;
      
      
      this.liveSet.clear();
      this.liveSet.addAll(newLiveSet);
      
      return true;
   }
   
   
   private List<ARMCFGNode> getSuccessors()
   {
      if (this.link == null)
         return new LinkedList<>();
      
      return this.link.getSuccessors();
   }
}
