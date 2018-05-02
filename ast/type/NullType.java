package ast.type;


import llvm.type.LLVMNullType;
import llvm.type.LLVMType;


public class NullType extends Type
{
   @Override
   public boolean equivalent(Type t)
   {
      return (t instanceof NullType) || (t instanceof StructType);
   }
   
   
   @Override
   public LLVMType getLLVMType()
   {
      return new LLVMNullType();
   }
   
   
   @Override
   public String astString()
   {
      return "null";
   }
}
