package arm;


import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import arm.instruction.ARMInstruction;

import arm.instruction.mov.ARMMov;

import arm.link.ARMBranch;
import arm.link.ARMJump;
import arm.link.ARMLink;

import arm.value.operand.ARMRegister;

import common.Printer;

public class ARMCFGNode
{
   public final List<ARMInstruction> instructions = new LinkedList<>();
   public final List<ARMCFGNode> predecessors = new LinkedList<>();
   public ARMCFGNode loopback = null;
   public ARMLink link = null;
   
   public Set<ARMRegister> genSet = new HashSet<>();
   public Set<ARMRegister> killSet = new HashSet<>();
   public Set<ARMRegister> liveSet = new LinkedHashSet<>();
   
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
   
   
   public void writeARM(Printer printr, boolean spilled, int localCount)
   {
      if (this.uid != -1)
      {
         printr.print(this.armString()).println(':');
      }
      
      
      for (ARMInstruction instruction : this.instructions)
         for (String str : instruction.armStrings(spilled, localCount))
            printr.print("   ").println(str);
      
      
      if (this.link != null)
         this.link.writeARM(printr, spilled, localCount);
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
         
         
         /*                    s.live           */
         successorLOSet.addAll(successor.liveSet);
         
         
         /*                   (s.live - s.kill) */
         successorLOSet.removeAll(successor.killSet);
         
         
         /*           s.gen U (s.live - s.kill) */
         successorLOSet.addAll(successor.genSet);
         
         
         /* b.live U= s.gen U (s.live - s.kill) */
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
   
   
   public List<ARMInterferenceEdge> getInterferences()
   {
      /* Reverse instructions to visit backwards */
      List<ARMInstruction> revInstructions = this.reverseInstructionList();
      
      
      /* Create a copy of the live-out set */
      Set<ARMRegister> liveSetCopy = new LinkedHashSet<>();
      
      for (ARMRegister live : this.liveSet)
         liveSetCopy.add(live);
      
      
      /* List of interference edges */
      List<ARMInterferenceEdge> interferences = new LinkedList<>();
      
      
      for (ARMInstruction instruction : revInstructions)
      {
         List<ARMRegister> targets = instruction.getTargets();
         List<ARMRegister> sources = instruction.getSources();
         
         
         /* Remove targets from live set */
         for (ARMRegister target : targets)
            liveSetCopy.remove(target);
         
         
         /* Add edges from each target to everything in live-set */
         for (ARMRegister target : targets)
            for (ARMRegister live : liveSetCopy)
               interferences.add(new ARMInterferenceEdge(target, live));
         
         
         /* Add each source to live set */
         for (ARMRegister source : sources)
            liveSetCopy.add(source);
      }
      
      
      return interferences;
   }
   
   
   private List<ARMInstruction> reverseInstructionList()
   {
      List<ARMInstruction> newInstructions = new LinkedList<>();
      
      
      for (ARMInstruction instruction : this.instructions)
         newInstructions.add(0, instruction);
      
      
      return newInstructions;
   }
}
