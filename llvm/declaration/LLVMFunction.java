package llvm.declaration;


import java.io.PrintWriter;

import java.util.Iterator;
import java.util.List;

import common.Options;

import llvm.LLVMCFGNode;

import llvm.type.LLVMType;

import llvm.value.operand.register.LLVMParameter;

import llvm.value.variable.LLVMLocal;


public class LLVMFunction
{
   public final String name;
   public final LLVMType type;
   public final List<LLVMParameter> parameters;
   public final List<LLVMLocal> locals;
   public final List<LLVMCFGNode> nodes;
   public final LLVMLocal returnValue;
   
   
   public LLVMFunction(String name, LLVMType type,
         List<LLVMParameter> parameters, List<LLVMLocal> locals,
         List<LLVMCFGNode> nodes, LLVMLocal returnValue)
   {
      this.name = name;
      this.type = type;
      this.parameters = parameters;
      this.locals = locals;
      this.nodes = nodes;
      this.returnValue = returnValue;
   }
   
   
   public void writeLLVM(PrintWriter printer)
   {
      printer.println(this.signature());
      printer.println('{');
      
      
      for (LLVMCFGNode node : this.nodes)
         node.writeLLVM(printer);
      
      
      printer.println('}');
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
   
   
   public ARMFunction buildARM()
   {
      List<ARMCFGNode> armNodes = new ArrayList<>(this.nodes.size());
      
      for (LLVMNode node : this.nodes)
         armNodes.add(node.buildARM());
      
      
      ARMCFGNode first = armNodes.get(0);
      
      first.instructions.add(0, new ARMSub(ARMRegister.RFP, ARMRegister.RSP, this.locals.size() * 4));
      
      
      return new ARMFunction(this.name, armNodes);
   }
}
