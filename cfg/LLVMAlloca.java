package cfg;


import ast.Type;


class LLVMAlloca
   implements LLVMInstruction
{
   LLVMValue result;
   String type;
   
   
   LLVMAlloca(LLVMValue result, Type type)
   {
      this.result = result;
      this.type = type.toLLVMTypeString();
   }
   
   
   public String toString()
   {
      return this.result.toString() + " = alloca " + this.type;
   }
}
