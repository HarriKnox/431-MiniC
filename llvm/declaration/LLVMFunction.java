package llvm.declaration;


import java.util.Iterator;
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
   
   
   public void writeLLVM()
   {
      System.out.println(this.signature());
      System.out.println('{');
      
      
      for (LLVMCFGNode node : this.nodes)
         node.writeLLVM();
      
      
      System.out.println('}');
   }
   
   
   private String signature()
   {
      StringBuilder sb = new StringBuilder("define ")
            .append(this.type.llvmString())
            .append(" @")
            .append(this.name)
            .append('(');
      
      
      Iterator<LLVMParameter> paramerator = this.parameters.iterator();
      
      if (paramerator.hasNext())
         sb.append(paramerator.next().llvmTypedString());
      
      while (paramerator.hasNext())
         sb.append(", ").append(paramerator.next().llvmTypedString());
      
      
      return sb.append(')').toString();
   }
}
