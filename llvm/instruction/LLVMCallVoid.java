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
   
   /*
   @Override
   public void buildARM(ARMCFGNode node)
   {
      int arglen = this.arguments.size();
      
      for (int i = arglen; i >= 4; i--)
      {
         LLVMValue arg = this.arguments.get(i);
         
         ARMRegister reg = arg.buildARM(node);
         
         ARMPush push = new ARMPush(reg);
         
         node.add(push);
      }
      
      
      for (i = 0; i < arglen && i < 4; i++)
      {
         LLVMValue arg = this.arguments.get(i);
         
         ARMRegister reg = arg.buildARM(node);
         
         ARMMov mov = new ARMMov(ARMRegister.getReal(i), reg);
         
         node.add(mov);
      }
      
      
      ARMBl bl = new ARMBl(this.name);
      
      node.add(bl);
      
      
      if (arglen >= 4)
      {
         ARMAdd spAdd = new ARMAdd(ARMRegister.RSP,
               ARMRegister.RSP, (arglen - 4) * 4);
         
         node.add(spAdd);
      }
   }*/
}
