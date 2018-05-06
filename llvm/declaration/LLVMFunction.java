package llvm.declaration;


import java.util.List;

import common.Options;

import llvm.LLVMCFGNode;

import llvm.type.LLVMType;

import llvm.value.variable.LLVMParameter;


public class LLVMFunction
{
   public final String name;
   public final LLVMType type;
   public final List<LLVMParameter> parameters;
   public final List<LLVMCFGNode> nodes;
   
   
   public LLVMFunction(String name, LLVMType type,
         List<LLVMParameter> parameters, List<LLVMCFGNode> nodes)
   {
      this.name = name;
      this.type = type;
      this.parameters = parameters;
      this.nodes = nodes;
   }
   
   
   public void writeLLVM(Options opts)
   {
      System.out.println(this.name);
      System.out.println('{');
      
      for (LLVMCFGNode node : this.nodes)
         node.writeLLVM
   }
}
