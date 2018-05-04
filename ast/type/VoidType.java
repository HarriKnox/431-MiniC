package ast.type;


import llvm.type.LLVMType;
import llvm.type.LLVMVoidType;


public class VoidType extends Type
{
   @Override
   public boolean equivalent(Type t)
   {
      return t instanceof VoidType;
   }
   
   
   @Override
   public LLVMType llvmType()
   {
      return new LLVMVoidType();
   }
   
   
   @Override
   public String astString()
   {
      return "void";
   }
}
