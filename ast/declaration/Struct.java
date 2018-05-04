package ast.declaration;


import java.util.LinkedList;
import java.util.List;

import org.antlr.v4.runtime.Token;

import ast.TokenedElement;

import ast.type.Type;

import llvm.declaration.LLVMStruct;

import llvm.type.LLVMType;


public class Struct extends TokenedElement
{
   public final String name;
   public final Variables fields;
   

   public Struct(Token token, String name, Variables fields)
   {
      super(token);

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
         llvmTypes.add(field.type.llvmType());
      
      
      return new LLVMStruct(this.name, llvmTypes);
   }
}
