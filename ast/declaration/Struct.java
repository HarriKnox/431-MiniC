package ast.declaration;


import ast.type.Type;

import llvm.type.LLVMType;


public class Struct
{
   public final String name;
   public final Variables fields;
   

   public Struct(int lineNum, String name, Variables fields)
   {
      super(lineNum);

      this.name = name;
      this.fields = fields;
   }
   
   
   public void removeInvalids(Structs structs)
   {
      this.fields.removeInvalids(structs);
   }
   
   
   public Variable getField(String name)
   {
      return this.fields.getVariable(name);
   }
   
   
   public LLVMStruct buildLLVM()
   {
      List<LLVMType> llvmTypes = new LinkedList<>();
      
      for (Variable field : this.fields.variables)
         llvmTypes.add(field.type);
      
      
      return new LLVMStruct(this.name, llvmTypes);
   }
}
