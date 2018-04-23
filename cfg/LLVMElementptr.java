package cfg;


import java.util.List;

import analyzer.TypeChecker;

import ast.StructType;
import ast.Declaration;


class LLVMElementptr
   implements LLVMInstruction
{
   LLVMRegister result;
   LLVMValue source;
   StructType type;
   int index;
   
   
   LLVMElementptr(LLVMValue left, LLVMRegister result, StructType type, String id)
   {
      this.source = left;
      this.result = result;
      this.type = type;
      
      
      List<Declaration> fields = TypeChecker.validTypeDecls.get(type.name).fields;
      
      for (int i = 0, len = fields.size(); i < len; i++)
      {
         if (fields.get(i).name.equals(id))
         {
            this.index = i;
            break;
         }
      }
   }
   
   
   public String toString()
   {
      return this.result.toString() + " = getelementptr " + this.type.toLLVMTypeString()
            + " " + this.source.toString() + ", i1 0, i32 " + Integer.toString(this.index);
   }
}
