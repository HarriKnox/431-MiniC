package llvm.instruction;


import java.util.Iterator;
import java.util.List;

import llvm.type.LLVMType;

import llvm.value.LLVMValue;


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
   
   
   @Override
   public void buildARM(ARMCFGNode node)
   {
      int arglen = this.arguments.size();
      
      /* Push extra arguments to the stack */
      for (int i = arglen; i >= 4; i--)
         node.add(new ARMPush(this.arguments.get(i).buildARM(node)));
      
      
      /* Move the first four into r0-r3 */
      for (i = 0; i < arglen && i < 4; i++)
         node.add(new ARMMov(
               ARMRegister.getReal(i),
               this.arguments.get(i).buildARM(node)));
      
      
      /* branch-link to the function */
      node.add(new ARMBl(this.name));
      
      
      /* pop the extra arguments off the stack all at once */
      if (arglen >= 4)
         node.add(new ARMAdd(
               ARMRegister.SP,
               ARMRegister.SP,
               (arglen - 4) * 4));
   }
}
