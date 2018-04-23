package cfg;


import ast.BoolType;
import ast.Declaration;
import ast.IntType;


class LLVMGlobalDeclaration
{
   String name;
   String type;
   String defValue;
   
   
   LLVMGlobalDeclaration(Declaration decl)
   {
      this.name = decl.name;
      
      this.type = decl.type.toLLVMTypeString();
      
      
      if (decl.type instanceof BoolType)
         this.defValue = "false";
      
      else if (decl.type instanceof IntType)
         this.defValue = "0";
      
      else
         this.defValue = "null";
   }
   
   
   public String toString()
   {
      return new StringBuilder()
            .append(new LLVMGlobal(this.name).toString())
            .append(" = common global ")
            .append(this.type)
            .append(" ")
            .append(this.defValue)
            .append(", align 4")
            .toString();
   }
}
