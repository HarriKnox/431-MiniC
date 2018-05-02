package ast.type;


import llvm.type.LLVMIntType;
import llvm.type.LLVMType;


public class IntType extends Type
{
   @Override
   public boolean equivalent(Type t)
   {
      return t instanceof IntType;
   }
   
   
   @Override
   public LLVMType getLLVMType()
   {
      return new LLVMIntType();
   }
   
   
   @Override
   public String astString()
   {
      return "int";
   }
}
