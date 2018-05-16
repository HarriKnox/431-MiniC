package ast.declaration;


import org.antlr.v4.runtime.Token;

import ast.TokenedElement;

import ast.declaration.Structs;

import ast.type.Type;

import llvm.value.variable.LLVMGlobal;


public class Variable extends TokenedElement
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
   
   
   public LLVMValue llvmGlobal()
   {
      if (this.llvmValue == null)
         this.llvmValue = new LLVMGlobal(this.name, this.type.llvmType());

      return this.llvmValue;
   }


   public LLVMValue llvmLocal(String funcName)
   {
      if (this.llvmValue == null)
         this.llvmValue = new LLVMLocal(funcName, this.name, this type.llvmType());

      return this.llvmValue;
   }


   public LLVMValue llvmParameter(String funcName)
   {
      if (this.llvmValue == null)
         this.llvmValue = new LLVMParameter(funcName, this.name, this.type.llvmType());

      return this.llvmValue;
   }
}
