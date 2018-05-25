package arm.instruction.binary;


import arm.instruction.ARMInstruction;

import arm.value.operand.ARMOperand;
import arm.value.operand.ARMRegister;


public abstract class ARMBinary extends ARMInstruction
{
   public final ARMRegister target;
   public final ARMRegister left;
   public final ARMOperand right;
   
   
   public ARMBinary(ARMRegister target, ARMRegister left, ARMOperand right)
   {
      this.target = target;
      this.left = left;
      this.right = right;
   }
   
   
   public ARMBinary(ARMRegister left, ARMOperand right)
   {
      this(new ARMRegister(), left, right);
   }
   
   
   protected abstract String getOperation();
   
   
   @Override
   public String armString()
   {
      return this.getOperation() + ' ' + this.target.armString() + ", "
            + this.left.armString() + ", " + this.right.armString();
   }
}
