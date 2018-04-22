package cfg;


import analyzer.TypeChecker;


class LLVMMalloc
   implements LLVMInstruction
{
   int size;
   LLVMRegister result;
   
   
   LLVMMalloc(String id, LLVMRegister result)
   {
      this.size = TypeChecker.types.get(id).size();
      this.result = result;
   }
   
   
   public String toString()
   {
      return this.result.toString() + " = call i8* @malloc(i32 " +
         Integer.toString(this.size * 4) + ")";
   }
}
