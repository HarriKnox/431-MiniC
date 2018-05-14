package llvm.instruction;


import java.util.Iterator;
import java.util.List;

import llvm.type.LLVMType;

import llvm.value.LLVMValue;

import llvm.value.variable.LLVMRegister;


public class LLVMCallVoid extends LLVMInstruction
{
   public final String name;
   public final LLVMType type;
   public final List<LLVMValue> arguments;
   
   
   public LLVMCallVoid(String name, LLVMType type, List<LLVMValue> arguments)
   {
      this.name = name;
      this.type = type;
      this.arguments = arguments;
   }
   
   
   @Override
   public String llvmString()
   {
      StringBuilder sb = new StringBuilder("call ")
            .append(this.type.llvmString())
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
