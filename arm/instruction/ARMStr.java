package arm.instruction;


import java.util.LinkedList;
import java.util.List;

import arm.ARMCFGNode;

import arm.value.operand.ARMAddress;
import arm.value.operand.ARMRegister;


import static java.util.Arrays.asList;

import static java.util.Collections.singletonList;


public class ARMStr extends ARMInstruction
{
   public final ARMRegister source;
   public final ARMAddress target;
   
   
   public ARMStr(ARMRegister source, ARMAddress target)
   {
      this.source = source;
      this.target = target;
   }
   
   
   @Override
   public List<String> armStrings(boolean spilled, int localCount)
   {
      boolean sourceValid = this.source.isValid();
      boolean targetValid = this.target.isValid();
      
      if (!spilled || (targetValid && sourceValid))
         return singletonList("str " + this.source.armString() + ", ["
            + this.target.armString() + "]");
      
      
      String sourceString = sourceValid ? this.source.armString() : "r9";
      
      String targetString = targetValid
            ? this.target.source.armString()
            : "r10";
      
      List<String> strings = new LinkedList<>();
      
      
      if (!sourceValid)
         strings.add("ldr r9, [fp, #-"
               + this.source.getSpillOffset(localCount) + ']');
      
      if (!targetValid)
         strings.add("ldr r10, [fp, #-"
               + this.target.source.getSpillOffset(localCount) + ']');
      
      strings.add("str " + sourceString + ", [" + targetString + ", #"
            + this.target.offset + ']');
      
      
      return strings;
   }
   
   
   @Override
   public List<ARMRegister> getSources()
   {
      return asList(new ARMRegister[]{this.source, this.target.getRegister()});
   }
   
   
   @Override
   public List<ARMRegister> getTargets()
   {
      return new LinkedList<>();
   }
}
