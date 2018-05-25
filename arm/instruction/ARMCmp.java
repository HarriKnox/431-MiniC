package arm.instruction;


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
}
