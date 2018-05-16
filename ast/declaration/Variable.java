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
   
   
   private LLVMValue llvmGlobal;
   private LLVMValue llvmLocal;
   private LLVMValue llvmParameter;


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
      if (this.llvmGlobal == null)
         this.llvmGlobal = new LLVMGlobal(this.name, this.type.llvmType());

      return this.llvmGlobal;
   }


   public LLVMValue llvmLocal(String funcName)
   {
      if (this.llvmLocal == null)
         this.llvmLocal = new LLVMLocal(funcName,
               this.name, this type.llvmType());

      return this.llvmLocal;
   }


   public LLVMValue llvmParameter(String funcName)
   {
      if (this.llvmParameter == null)
         this.llvmParameter = new LLVMParameter(
               funcName, this.name, this.type.llvmType());

      return this.llvmParameter;
   }
}
