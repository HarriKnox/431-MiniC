package llvm.instruction;


import java.util.Iterator;
import java.util.List;

import llvm.type.LLVMType;

import llvm.value.operand.LLVMOperand;

import arm.ARMCFGNode;

import arm.instruction.ARMBl;
import arm.instruction.ARMPush;

import arm.instruction.binary.ARMAdd;

import arm.instruction.mov.ARMMov;

import arm.value.operand.ARMConstant;
import arm.value.operand.ARMRegister;


import static arm.value.operand.ARMRegister.SP;


public class LLVMCallVoid extends LLVMInstruction
{
   public final String name;
   public final LLVMType type;
   public final List<LLVMOperand> arguments;
   
   
   public LLVMCallVoid(String name, LLVMType type, List<LLVMOperand> arguments)
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
      
      
      Iterator<LLVMOperand> argerator = this.arguments.iterator();
      
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
      for (int i = 0; i < arglen && i < 4; i++)
         node.add(new ARMMov(
               new ARMRegister(i),
               this.arguments.get(i).buildARM(node)));
      
      
      /* branch-link to the function */
      node.add(new ARMBl(this.name));
      
      
      /* pop the extra arguments off the stack all at once */
      if (arglen >= 4)
         node.add(new ARMAdd(
               SP,
               SP,
               new ARMConstant((arglen - 4) * 4)));
   }
}
