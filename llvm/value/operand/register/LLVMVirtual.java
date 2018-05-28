package llvm.value.operand.register;


import llvm.type.LLVMType;

import arm.ARMCFGNode;

import arm.value.operand.ARMRegister;


public class LLVMVirtual extends LLVMRegister
{
   private ARMRegister armReg = null;
   
   private int uid = -1;
   
   private static int count = 0;
   
   
   public LLVMVirtual(LLVMType type)
   {
      super(type);
   }
   
   
   @Override
   public String llvmString()
   {
      return "%v" + Integer.toString(this.getUID());
   }
   
   
   @Override
   public ARMRegister buildARM(ARMCFGNode node)
   {
      if (this.armReg == null)
         this.armReg = new ARMRegister();
      
      return this.armReg;
   }
   
   
   public void setARMRegister(ARMRegister reg)
   {
      if (this.armReg == null)
         this.armReg = reg;
   }
   
   
   public int getUID()
   {
      if (this.uid == -1)
         this.uid = count++;
      
      return this.uid;
   }
}
