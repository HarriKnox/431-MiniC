package arm.instruction;


import java.util.LinkedList;
import java.util.List;

import arm.ARMCFGNode;

import arm.value.operand.ARMOperand;
import arm.value.operand.ARMRegister;


import static java.util.Collections.singletonList;


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
   public List<String> armStrings(boolean spilled, int localCount)
   {
      boolean leftValid = this.left.isValid();
      boolean rightValid = this.right.isValid();
      
      if (!spilled || (leftValid && rightValid))
         return singletonList("cmp " + this.left.armString()
               + ", " + this.right.armString());
      
      
      String leftString  = leftValid  ? this.left.armString()  : "r9";
      String rightString = rightValid ? this.right.armString() : "r10";
      
      List<String> strings = new LinkedList<>();
      
      
      if (!leftValid)
         strings.add("ldr r9, [fp, #-"
               + this.left.getSpillOffset(localCount) + ']');
      
      if (!rightValid)
         strings.add("ldr r10, [fp, #-"
               + ((ARMRegister)this.right).getSpillOffset(localCount) + ']');
      
      
      strings.add("cmp " + leftString + ", " + rightString);
      
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
      return new LinkedList<>();
   }
}
