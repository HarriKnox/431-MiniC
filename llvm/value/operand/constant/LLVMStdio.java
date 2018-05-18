package llvm.value.operand.constant;


import llvm.type.LLVMByteType;
import llvm.type.LLVMPointerType;


public class LLVMStdio extends LLVMConstant
{
   public final String name;
   public final String initialization;
   
   
   private LLVMStdio(String name, String initialization)
   {
      super(new LLVMPointerType(new LLVMByteType()));
      
      this.name = "@." + name;
      this.initialization = initialization;
   }
   
   
   public String llvmString()
   {
      return "getelementptr inbounds ([4 x i8]* "
            + this.name + ", i32 0, i32 0)";
   }
   
   
   public static final LLVMStdio PRINT_FORMAT
         = new LLVMStdio("print_format", "c\"%d\\0A\\00\"");
   
   public static final LLVMStdio PRINTLN_FORMAT
         = new LLVMStdio("println_format", "c\"%d \\00\"");
   
   public static final LLVMStdio SCANF_FORMAT
         = new LLVMStdio("scanf_format", "c\"%d\\00\\00\"");
}
