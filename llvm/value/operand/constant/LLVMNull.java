package llvm.value.operand.constant;


import llvm.type.LLVMNullType;
import llvm.type.LLVMType;


public class LLVMNull extends LLVMConstant
{
   public LLVMNull()
   {
      super(new LLVMNullType());
   }
   
   
   public LLVMNull(LLVMType type)
   {
      super(type);
   }
   
   
   @Override
   public String llvmString()
   {
      return "null";
   }
   
   
   @Override
   public ARMRegister buildARM(ARMCFGNode node)
   {
      ARMMovw movw = new ARMMovw(new ARMInt(0));
      node.add(movw);
      
      return movw.target;
   }
}
