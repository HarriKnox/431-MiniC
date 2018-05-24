package arm;


import java.util.LinkedList;
import java.util.List;

import arm.instruction.ARMInstruction;


public class ARMCFGNode
{
   public final List<ARMInstruction> instructions = new LinkedList<>();
   public final List<ARMCFGNode> predecessors = new LinkedList<>();
   public ARMCFGNode loopback = null;
   /*public ARMLink link = null;*/
   
   private int uid = -1;
   
   private static int count = 0;
   
   
   public ARMCFGNode(int uid)
   {
      this.uid = uid;
   }
   
   
   public int getUID()
   {
      if (this.uid == -1)
         this.uid = count++;
      
      return this.uid;
   }
   
   
   public ARMCFGNode add(ARMInstruction instruction)
   {
      this.instructions.add(instruction);
      
      return this;
   }
   
   
   public String armString()
   {
      return ".N" + this.getUID();
   }
}
