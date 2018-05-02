package llvm.declaration;


import java.util.List;

import llvm.type.LLVMType;


public class LLVMStruct
{
   public final String name;
   public final List<LLVMType> types;
   
   
   public LLVMStruct(String name, List<LLVMType> types)
   {
      this.name = name;
      this.types = types;
   }
}
