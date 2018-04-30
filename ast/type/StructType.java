package ast.type;


import llvm.type.LLVMStructType;
import llvm.type.LLVMType;


import ast.declaration.Structs;


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
      return structs.getDeclaration(this.name) != null;
   }
   
   
   @Override
   public LLVMType getLLVMType()
   {
      return new LLVMStructType(this.name);
   }
}
