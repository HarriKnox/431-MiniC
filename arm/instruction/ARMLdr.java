package arm.instruction;


import java.util.LinkedList;
import java.util.List;

import arm.ARMCFGNode;

import arm.value.operand.ARMAddress;
import arm.value.operand.ARMRegister;


import static java.util.Collections.singletonList;


public class ARMLdr extends ARMInstruction
{
   public final ARMRegister target;
   public final ARMAddress source;
   
   
   public ARMLdr(ARMAddress source)
   {
      this(new ARMRegister(), source);
   }
   
   
   public ARMLdr(ARMRegister target, ARMAddress source)
   {
      this.target = target;
      this.source = source;
   }
   
   
   @Override
   public List<String> armStrings(boolean spilled, int localCount)
   {
      boolean sourceValid = this.source.isValid();
      boolean targetValid = this.target.isValid();
      
      if (!spilled || (sourceValid && targetValid))
         return singletonList("ldr " + this.target.armString()
               + ", [" + this.source.armString() + ']');
      
      
      String targetString = targetValid ? this.target.armString() : "r10";
      
      String sourceString = sourceValid
            ? this.source.source.armString()
            : "r9";
      
      List<String> strings = new LinkedList<>();
      
      
      if (!sourceValid)
         strings.add("ldr r9, [fp, #-"
               + this.source.source.getSpillOffset(localCount) + ']');
      
      
      strings.add("ldr " + targetString + ", ["
            + sourceString + ", #" + this.source.offset + ']');
      
      
      if (!targetValid)
         strings.add("str r10, [fp, #-"
               + this.target.getSpillOffset(localCount) + ']');
      
      
      return strings;
   }
   
   
   @Override
   public List<ARMRegister> getSources()
   {
      return singletonList(this.source.getRegister());
   }
   
   
   @Override
   public List<ARMRegister> getTargets()
   {
      return singletonList(this.target);
   }
}
