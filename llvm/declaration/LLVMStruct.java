package llvm.declaration;


import java.util.List;


public class LLVMStruct
{
   public LLVMStruct(String name, List<LLVMType> types)
   {
      this.name = name;
      this.types = types;
   }
}
