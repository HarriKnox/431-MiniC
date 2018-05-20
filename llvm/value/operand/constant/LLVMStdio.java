package llvm.value.operand.constant;


import llvm.type.LLVMByteType;
import llvm.type.LLVMPointerType;

import arm.ARMCFGNode;

import arm.value.integer.ARMGlobal;

import arm.value.operand.ARMRegister;


public class LLVMStdio extends LLVMConstant
{
   public final String name;
   public final String initialization;
   
   
   private LLVMStdio(String name, String initialization)
   {
      super(new LLVMPointerType(new LLVMByteType()));
      
      this.name = '@' + name;
      this.initialization = initialization;
   }
   
   
   @Override
   public String llvmString()
   {
      return "getelementptr inbounds ([4 x i8]* "
            + this.name + ", i32 0, i32 0)";
   }
   
   
   public static final LLVMStdio PRINT_FORMAT
         = new LLVMStdio(".print_format", "c\"%d\\0A\\00\"");
   
   public static final LLVMStdio PRINTLN_FORMAT
         = new LLVMStdio(".println_format", "c\"%d \\00\"");
   
   public static final LLVMStdio SCANF_FORMAT
         = new LLVMStdio(".scanf_format", "c\"%d\\00\\00\"");
   
   
   @Override
   public ARMRegister buildARM(ARMCFGNode node)
   {
      ARMMovw movw = new ARMMovw(new ARMGlobal(this.name));
      ARMMovt movt = new ARMMovt(movw.target, movw.value);
      
      node.add(movw).add(movt);
      
      return movw.target;
   }
}
