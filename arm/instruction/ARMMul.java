package arm.instruction;


import java.util.LinkedList;
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
   public List<String> armStrings(boolean spilled, int localCount)
   {
      boolean targetValid = this.target.isValid();
      boolean rightValid = this.right.isValid();
      boolean leftValid = this.left.isValid();
      
      if (!spilled || (targetValid && leftValid && rightValid))
         return singletonList("mul " + this.target.armString() + ", "
            + this.left.armString() + ", " + this.right.armString());
      
      
      String targetString = targetValid ? this.target.armString() : "r10";
      String rightString  = rightValid  ? this.right.armString()  : "r10";
      String leftString   = leftValid   ? this.left.armString()   : "r9";
      
      List<String> strings = new LinkedList<>();
      
      
      if (!leftValid)
         strings.add("ldr r9, [fp, #-"
               + this.left.getSpillOffset(localCount) + ']');
      
      if (!rightValid)
         strings.add("ldr r10, [fp, #-"
               + this.right.getSpillOffset(localCount) + ']');
      
      
      strings.add("mul " + targetString + ", "
            + leftString + ", " + rightString);
      
      
      if (!targetValid)
         strings.add("str r10, [fp, #-"
               + this.target.getSpillOffset(localCount) + ']');
      
      
      return strings;
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
