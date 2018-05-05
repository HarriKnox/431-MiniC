package llvm.instruction.targeted;


import java.util.Iterator;
import java.util.List;

import llvm.type.LLVMType;

import llvm.value.LLVMValue;

import llvm.value.variable.LLVMRegister;


public class LLVMCall extends LLVMTargetedInstruction
{
   public final String name;
   public final List<LLVMValue> arguments;
   
   
   public LLVMCall(String name, LLVMType type, List<LLVMValue> arguments)
   {
      super(type);
      this.name = name;
      this.arguments = arguments;
   }
   
   
   @Override
   protected String getInstruction()
   {
      StringBuilder sb = new StringBuilder("call ")
            .append(this.target.type.llvmString())
            .append(" @")
            .append(this.name)
            .append('(');
      
      
      Iterator<LLVMValue> argerator = this.arguments.iterator();
      
      if (argerator.hasNext())
         sb.append(argerator.next().llvmTypedString());
      
      while (argerator.hasNext())
         sb.append(", ").append(argerator.next().llvmTypedString());
      
      
      return sb.append(')').toString();
   }
}
