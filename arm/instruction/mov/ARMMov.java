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
   public String armString()
   {
      return "mov" + this.conditionString() + " "
            + this.target.armString() + ", " + this.value.armString();
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
