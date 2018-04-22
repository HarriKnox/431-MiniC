package cfg;


import ast.BoolType;
import ast.IntType;
import ast.StructType;
import ast.Type;


class LLVMReturn
   implements LLVMInstruction
{
   LLVMValue returnValue;
   String retType;
   
   
   LLVMReturn()
   {
      this.retType = "void";
      this.returnValue = null;
   }
   
   
   LLVMReturn(LLVMValue value, Type type)
   {
      this.returnValue = value;
      
      if (type instanceof IntType)
         this.retType = "i32";
      
      else if (type instanceof BoolType)
         this.retType = "i1";
      
      else
         this.retType = "%struct." + ((StructType)type).name + "*";
   }
   
   
   public String toString()
   {
      if (this.retType.equals("void"))
         return "ret void";
      
      return "ret " + this.retType + " " + this.returnValue.toString();
   }
}
