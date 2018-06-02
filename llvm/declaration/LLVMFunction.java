package llvm.declaration;


import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import common.Options;

import llvm.LLVMCFGNode;

import llvm.instruction.LLVMStore;

import llvm.type.LLVMType;
import llvm.type.LLVMVoidType;

import llvm.value.operand.register.LLVMParameter;

import llvm.value.variable.LLVMLocal;

import arm.ARMCFGNode;

import arm.declaration.ARMFunction;

import arm.instruction.binary.ARMSub;

import arm.value.operand.ARMConstant;


import static arm.value.operand.ARMRegister.FP;
import static arm.value.operand.ARMRegister.SP;


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
      
      
      for (LLVMLocal local : this.locals)
      {
         printer.print("   ");
         printer.print(local.llvmString());
         printer.print(" = alloca ");
         printer.println(local.type.llvmString());
      }
      
      
      for (LLVMParameter param : this.parameters)
      {
         printer.print("   ");
         printer.println(new LLVMStore(param.llvmLocal, param).llvmString());
      }
      
      
      LLVMCFGNode firstNode = nodes.get(0);
      
      if ((firstNode != null) && (firstNode.getUID() != -1))
      {
         printer.print("   br label %");
         printer.println(firstNode.llvmString());
      }
      
      
      for (LLVMCFGNode node : this.nodes)
         node.writeLLVM(printer);
      
      
      if (this.returnValue.type instanceof LLVMVoidType)
      {
         printer.println("   ret void");
      }
      
      else
      {
         printer.print("   ");
         printer.print(this.retvalRegister());
         printer.print(" = load ");
         printer.print(this.type.llvmString());
         printer.print("* ");
         printer.println(this.returnValue.llvmString());
         
         printer.print("   ret ");
         printer.print(this.type.llvmString());
         printer.print(' ');
         printer.println(this.retvalRegister());
      }
      
      
      printer.println('}');
   }
   
   
   private String retvalRegister()
   {
      return new StringBuilder()
            .append('%')
            .append(this.name)
            .append(".ret.val")
            .toString();
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
      
      
      /* First pass to create ARM nodes */
      for (LLVMCFGNode node : this.nodes)
         node.setupARMNode();
      
      
      /* Second pass to put instructions (and links) into nodes */
      for (LLVMCFGNode node : this.nodes)
         armNodes.add(node.buildARM());
      
      
      ARMFunction armFunction = new ARMFunction(this.name, armNodes,
            this.locals.size(), this.returnValue.buildARM(null));
      
      armFunction.allocateRegisters();
      
      return armFunction;
   }
}
