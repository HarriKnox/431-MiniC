package cfg;


import ast.BoolType;
import ast.IntType;
import ast.StructType;
import ast.Type;
import ast.VoidType;


class LLVMReturn
   implements LLVMInstruction
{
   LLVMValue returnValue;
   String retType;
   
   
   LLVMReturn(Type type, LLVMValue value)
   {
      if (type instanceof VoidType)
         this.returnValue = null;
      
      else
         this.returnValue = value;
      
      this.retType = type.toLLVMTypeString();
   }
   
   
   public String toString()
   {
      if (this.retType.equals("void"))
         return "ret void";
      
      return "ret " + this.retType + " " + this.returnValue.toString();
   }
}
