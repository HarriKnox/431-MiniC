package ast.type;


import llvm.type.LLVMBoolType;
import llvm.type.LLVMType;


public class BoolType extends Type
{
   @Override
   public boolean equivalent(Type t)
   {
      return t instanceof BoolType;
   }
   
   
   @Override
   public LLVMType getLLVMType()
   {
      return new LLVMBoolType();
   }
   
   
   @Override
   public String astString()
   {
      return "bool";
   }
}
