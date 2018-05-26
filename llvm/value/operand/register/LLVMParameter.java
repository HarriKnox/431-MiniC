package llvm.value.operand.register;


import llvm.type.LLVMType;

import arm.ARMCFGNode;

import arm.instruction.ARMLdr;

import arm.value.operand.ARMAddress;
import arm.value.operand.ARMRegister;


import static arm.value.operand.ARMRegister.FP;


public class LLVMParameter extends LLVMRegister
{
   public final String function;
   public final String id;
   public final int index;
   public final LLVMLocal llvmLocal;
   
   
   public LLVMParameter(String function, String identifier,
         LLVMType type, int index, LLVMLocal llvmLocal)
   {
      super(type);
      
      this.function = function;
      this.id = identifier;
      this.index = index;
      this.llvmLocal = llvmLocal;
   }
   
   
   public LLVMParameter(String function,
         String identifier, LLVMType type, int index)
   {
      this(function, identifier, type, index,
            new LLVMLocal(function, identifier, type, index));
   }
   
   
   @Override
   public String llvmString()
   {
      return '%' + this.function + ".param." + this.id;
   }
   
   
   @Override
   public ARMRegister buildARM(ARMCFGNode node)
   {
      if (this.index <= 3)
         return ARMRegister.getReal(this.index);
      
      
      ARMLdr load = new ARMLdr(new ARMAddress(FP, (this.index - 3) * 4));
      
      node.add(load);
      
      return load.target;
   }
}
