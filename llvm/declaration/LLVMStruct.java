package llvm.declaration;


import java.util.Iterator;
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
   
   
   public String llvmString()
   {
      StringBuilder sb = new StringBuilder("%struct.")
            .append(this.name)
            .append(" = type {");
      
      
      Iterator<LLVMType> typerator = this.types.iterator();
      
      
      if (typerator.hasNext())
         sb.append(typerator.next().llvmString());
      
      while (typerator.hasNext())
         sb.append(", ").append(typerator.next().llvmString());
      
      
      return sb.append('}').toString();
   }
}
