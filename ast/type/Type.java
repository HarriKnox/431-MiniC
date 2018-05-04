package ast.type;


import ast.declaration.Structs;

import llvm.type.LLVMType;


public abstract class Type
{
   public abstract boolean equivalent(Type type);
   
   
   public boolean isValid(Structs structs)
   {
      return true;
   }
   
   
   public abstract LLVMType llvmType();
   
   public abstract String astString();
}
