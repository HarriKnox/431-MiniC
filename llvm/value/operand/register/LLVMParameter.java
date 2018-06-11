package llvm.value.operand.register;


import llvm.type.LLVMType;

import llvm.value.variable.LLVMLocal;

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
   
   
   private ARMRegister armReg = null;
   
   
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
   public String regLLVMString()
   {
      return '%' + this.function + ".param." + this.id;
   }
   
   
   @Override
   public ARMRegister regBuildARM(ARMCFGNode node)
   {
      if (this.armReg != null)
         return armReg;
      
      
      if (this.index <= 3)
         return ARMRegister.getReal(this.index);
      
      
      ARMLdr load = new ARMLdr(new ARMAddress(FP, (this.index - 3) * 4));
      
      node.add(load);
      
      return this.armReg = load.target;
   }
   
   
   public void setARMRegister(ARMRegister reg)
   {
      if (this.armReg == null)
         this.armReg = reg;
   }
}
