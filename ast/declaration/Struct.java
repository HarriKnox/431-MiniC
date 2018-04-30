package ast.declaration;


import ast.type.Type;

import llvm.type.LLVMType;


public class Struct extends Declaration<LLVMStruct>
{
   public final Variables fields;
   

   public Struct(int lineNum, String name, Variables fields)
   {
      super(lineNum, name);

      this.fields = fields;
   }
   
   
   @Override
   public boolean hasValidType(Structs structs)
   {
      return true;
   }
   
   
   @Override
   public void removeInvalids(Structs structs)
   {
      this.fields.removeInvalids(structs);
   }
   
   
   @Override
   public LLVMStruct buildLLVM(Structs structs,
         Variables globals, Functions functions)
   {
      List<LLVMType> new LinkedList<>();
      
      
   }
}
