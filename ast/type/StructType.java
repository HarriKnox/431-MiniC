package ast.type;


import ast.declaration.Structs;

import llvm.type.LLVMStructType;
import llvm.type.LLVMType;


public class StructType extends Type
{
   public final String name;


   public StructType(String name)
   {
      this.name = name;
   }


   @Override
   public boolean equivalent(Type t)
   {
      return (t instanceof NullType)
            || ((t instanceof StructType)
                  && this.name.equals(((StructType)t).name));
   }
   
   
   @Override
   public boolean isValid(Structs structs)
   {
      return structs.getStruct(this.name) != null;
   }
   
   
   @Override
   public LLVMType llvmType()
   {
      return new LLVMStructType(this.name);
   }
   
   
   @Override
   public String astString()
   {
      return "struct " + this.name;
   }
}
