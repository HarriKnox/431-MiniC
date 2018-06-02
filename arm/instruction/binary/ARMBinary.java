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
   public List<String> armStrings(boolean spilled, int localCount)
   {
      boolean targetValid = this.target.isValid();
      boolean leftValid = this.left.isValid();
      boolean rightValid = this.right.isValid();
      
      if (!spilled || (targetValid && leftValid && rightValid))
         return singletonList(this.getOperation() + ' '
            + this.target.armString() + ", " + this.left.armString()
            + ", " + this.right.armString());
      
      
      String targetString = targetValid ? this.target.armString() : "r10";
      String leftString   = leftValid   ? this.left.armString()   : "r9";
      String rightString  = rightValid  ? this.right.armString()  : "r10";
      
      List<String> strings = new LinkedList<>();
      
      
      if (!leftValid)
         strings.add("ldr r9, [fp, #-"
               + this.left.getSpillOffset(localCount) + ']');
      
      if (!rightValid)
         strings.add("ldr r10, [fp, #-"
               + ((ARMRegister)this.right).getSpillOffset(localCount) + ']');
      
      
      strings.add(this.getOperation() + ' ' + targetString + ", "
            + leftString + ", " + rightString);
      
      
      if (!targetValid)
         strings.add("str r10, [fp, #-"
               + this.target.getSpillOffset(localCount) + ']');
      
      
      return strings;
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
