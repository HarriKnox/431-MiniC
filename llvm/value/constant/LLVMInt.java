package llvm.value.constant;


import llvm.type.LLVMIntType;
import llvm.type.LLVMType;


import arm.value.ARMRegister;


public class LLVMInt extends LLVMConstant
{
   public final int value;
   
   
   public LLVMInt(int value)
   {
      super(new LLVMIntType());
      
      this.value = value;
   }
   
   
   @Override
   public String llvmString()
   {
      return Integer.toString(this.value);
   }
   
   
   @Override
   public ARMRegister buildARM(ARMCFGNode node)
   {
      /* if the number is small enough to fit in one mov instruction */
      if ((this.value & ~0xffff) == 0)
      {
         ARMMovw movw = new ARMMovw(this.value);
         node.add(movw);
         
         return movw.target;
      }
      else
      {
         ARMMovw movw = new ARMMovw(this.value & 0xffff);
         ARMMovt movt = new ARMMovt(movw.target, (this.value >> 16) & 0xffff);
         
         node.add(movw).add(movt);
         
         return movw.target;
      }
   }
}
