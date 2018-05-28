package arm.instruction;


import java.util.LinkedList;
import java.util.List;

import arm.ARMCFGNode;

import arm.value.operand.ARMOperand;
import arm.value.operand.ARMRegister;


public class ARMCmp extends ARMInstruction
{
   public final ARMRegister left;
   public final ARMOperand right;
   
   
   public ARMCmp(ARMRegister left, ARMOperand right)
   {
      this.left = left;
      this.right = right;
   }
   
   
   @Override
   public String armString()
   {
      return "cmp " + this.left.armString() + ", " + this.right.armString();
   }
   
   
   @Override
   public List<ARMRegister> getSources()
   {
      List<ARMRegister> sources = new LinkedList<>();
      
      sources.add(this.left);
      
      
      ARMRegister rightRegister = this.right.getRegister();
      
      if (rightRegister != null)
         sources.add(rightRegister);
      
      
      return sources;
   }
   
   
   @Override
   public List<ARMRegister> getTargets()
   {
      return new LinkedList<>();
   }
}
