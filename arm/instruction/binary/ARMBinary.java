package arm.instruction.binary;


import java.util.LinkedList;
import java.util.List;

import arm.instruction.ARMInstruction;

import arm.value.operand.ARMOperand;
import arm.value.operand.ARMRegister;


import static java.util.Collections.singletonList;


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
      return singletonList(this.target);
   }
}
