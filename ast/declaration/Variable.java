package ast.declaration;


import org.antlr.v4.runtime.Token;

import ast.declaration.Structs;

import ast.type.Type;

import llvm.value.variable.LLVMGlobal;


public class Variable
{
   public final String name;
   public final Type type;
   public final int index;


   public Variable(Token token, String name, Type type, int index)
   {
      super(token);

      this.name = name;
      this.type = type;
      this.index = index;
   }
   
   
   public boolean hasValidType(Structs structs)
   {
      return this.type.isValid(structs);
   }
   
   
   public LLVMGlobal buildLLVM()
   {
      return new LLVMGlobal(this.name, this.type.getLLVMType());
   }
}
