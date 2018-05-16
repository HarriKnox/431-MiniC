package ast.declaration;


import org.antlr.v4.runtime.Token;

import ast.TokenedElement;

import ast.declaration.Structs;

import ast.type.Type;

import llvm.value.variable.LLVMGlobal;
import llvm.value.variable.LLVMLocal;
import llvm.value.variable.LLVMParameter;
import llvm.value.variable.LLVMVariable;


public class Variable extends TokenedElement
{
   public final String name;
   public final Type type;
   public final int index;
   
   
   private LLVMGlobal llvmGlobal;
   private LLVMLocal llvmLocal;
   private LLVMParameter llvmParameter;


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
   
   
   public LLVMGlobal llvmGlobalSet()
   {
      if (this.llvmGlobal == null)
         this.llvmGlobal = new LLVMGlobal(this.name, this.type.llvmType());
      
      
      return this.llvmGlobal;
   }

   
   public LLVMLocal llvmLocalSet(String funcName, int index)
   {
      if (this.llvmLocal == null)
         this.llvmLocal = new LLVMLocal(funcName,
               this.name, this.type.llvmType(), index);
      
      return this.llvmLocal;
   }
   
   
   public LLVMParameter llvmParameterSet(String funcName, int index)
   {
      if (this.llvmParameter == null)
         this.llvmParameter = new LLVMParameter(
               funcName, this.name, this.type.llvmType());
      
      return this.llvmParameter;
   }

   
   public LLVMGlobal llvmGlobal()
   {
      return this.llvmGlobal;
   }


   public LLVMLocal llvmLocal()
   {
      return this.llvmLocal;
   }


   public LLVMParameter llvmParameter()
   {
      return this.llvmParameter;
   }
}
