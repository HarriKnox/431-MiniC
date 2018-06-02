package arm.instruction.mov;


import java.util.LinkedList;
import java.util.List;

import arm.instruction.ARMInstruction;

import arm.value.operand.ARMOperand;
import arm.value.operand.ARMRegister;


import static java.util.Arrays.asList;

import static java.util.Collections.singletonList;


public class ARMMov extends ARMInstruction
{
   public final ARMRegister target;
   public final ARMOperand value;
   
   
   public ARMMov(ARMOperand value)
   {
      this(new ARMRegister(), value);
   }
   
   
   public ARMMov(ARMRegister target, ARMOperand value)
   {
      this.target = target;
      this.value = value;
   }
   
   
   protected String conditionString()
   {
      return "";
   }
   
   
   @Override
   public List<String> armStrings(boolean spilled, int localCount)
   {
      boolean targetValid = this.target.isValid();
      boolean valueValid = this.value.isValid();
      
      if (!spilled || (targetValid && valueValid))
      {
         /* Ignore movs where the target and source are the same */
         if ((this.value instanceof ARMRegister)
               && ((ARMRegister)this.value).getNumber()
                     == this.target.getNumber())
            return new LinkedList<>();
         
         return singletonList("mov" + this.conditionString() + ' '
            + this.target.armString() + ", " + this.value.armString());
      }
      
      
      List<String> strings = new LinkedList<>();
      
      String targetString = targetValid ? this.target.armString() : "r10";
      String valueString = valueValid ? this.value.armString() : "r9";
      
      
      if (!valueValid)
         strings.add("ldr r9, [fp, #-"
               + ((ARMRegister)this.value).getSpillOffset(localCount) + ']');
      
      
      /* If conditional, then target is a source */
      if (!targetValid && !this.conditionString().isEmpty())
         strings.add("ldr r10, [fp, #-"
               + this.target.getSpillOffset(localCount) + ']');
      
      
      strings.add("mov" + this.conditionString() + ' '
            + targetString + ", " + valueString);
      
      
      if (!targetValid)
         strings.add("str r10, [fp, #-"
               + this.target.getSpillOffset(localCount) + ']');
      
      
      return strings;
   }
   
   
   @Override
   public List<ARMRegister> getSources()
   {
      ARMRegister valueRegister = this.value.getRegister();
      
      
      /* Unconditional mov instruction */
      if (this.conditionString().isEmpty())
      {
         /* If the value is just a constant, there are no sources */
         if (valueRegister == null)
            return new LinkedList<>();
         
         
         /* Otherwise, the source is the value register */
         return singletonList(valueRegister);
      }
      
      
      /* Conditional mov instruction */
      
      /* If the value is just a constant, the only source is the target */
      if (valueRegister == null)
         return singletonList(this.target);
      
      /* Otherwise, both are the sources */
      return asList(new ARMRegister[]{this.target, valueRegister});
   }
   
   
   @Override
   public List<ARMRegister> getTargets()
   {
      return singletonList(this.target);
   }
}
