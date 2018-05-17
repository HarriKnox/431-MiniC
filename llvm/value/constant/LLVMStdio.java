package llvm.value.constant;


import llvm.type.LLVMByteType;
import llvm.type.LLVMPointerType;


public class LLVMStdio extends LLVMConstant
{
   public final String name;
   
   
   private LLVMStdio(String name)
   {
      super(new LLVMPointerType(new LLVMByteType()));
      
      this.name = "@." + name;
   }
   
   
   public String llvmString()
   {
      return "getelementptr inbounds ([4 x i8]* "
            + this.name + ", i32 0, i32 0)";
   }
   
   
   public static final LLVMStdio PRINT_FORMAT
         = new LLVMStdio("print_format");
   
   public static final LLVMStdio PRINTLN_FORMAT
         = new LLVMStdio("println_format");
   
   public static final LLVMStdio SCANF_FORMAT
         = new LLVMStdio("scanf_format");
}
