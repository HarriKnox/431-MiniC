package arm.instruction;


import java.util.List;

import arm.value.operand.ARMRegister;


import static java.util.Arrays.asList;

import static java.util.Collections.singletonList;


public class ARMMul extends ARMInstruction
{
   public final ARMRegister target;
   public final ARMRegister left;
   public final ARMRegister right;
   
   
   public ARMMul(ARMRegister left, ARMRegister right)
   {
      this(new ARMRegister(), left, right);
   }
   
   public ARMMul(ARMRegister target, ARMRegister left, ARMRegister right)
   {
      this.target = target;
      this.left = left;
      this.right = right;
   }
   
   
   @Override
   public String armString()
   {
      return "mul " + this.target.armString() + ", "
            + this.left.armString() + ", " + this.right.armString();
   }
   
   
   @Override
   public List<ARMRegister> getSources()
   {
      return asList(new ARMRegister[]{this.left, this.right});
   }
   
   
   @Override
   public List<ARMRegister> getTargets()
   {
      return singletonList(this.target);
   }
}
