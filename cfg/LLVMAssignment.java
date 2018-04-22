package cfg;


import ast.Type;
import ast.StructType;
import ast.IntType;
import ast.BoolType;


class LLVMAssignment
   implements LLVMInstruction
{
   LLVMValue source;
   LLVMValue target;
   Type type;
   
   
   LLVMAssignment(LLVMValue source, LLVMValue target, Type type)
   {
      this.source = source;
      this.target = target;
      this.type = type;
   }
   
   
   private String typeToString()
   {
      if (this.type instanceof IntType)
         return "i32";
      
      else if (this.type instanceof BoolType)
         return "i1";
      
      return "%struct." + ((StructType)this.type).name + "*";
   }
   
   
   public String toString()
   {
      return "store " + this.typeToString() + " " + this.source.toString() +
            ", " + this.typeToString() + "* " + this.target.toString();
   }
}
