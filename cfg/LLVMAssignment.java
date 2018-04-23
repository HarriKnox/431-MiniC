package cfg;


import ast.Type;


class LLVMAssignment
   implements LLVMInstruction
{
   LLVMValue source;
   LLVMValue target;
   Type type;
   
   
   LLVMAssignment(LLVMValue target, LLVMValue source, Type type)
   {
      this.source = source;
      this.target = target;
      this.type = type;
   }
   
   
   public String toString()
   {
      return "store " + this.type.toLLVMTypeString() + " " + this.source.toString() +
            ", " + this.type.toLLVMTypeString() + "* " + this.target.toString();
   }
}
