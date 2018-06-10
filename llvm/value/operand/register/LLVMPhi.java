package llvm.value.operand.register;


import java.util.HashMap;
import java.util.Map;

import llvm.LLVMCFGNode;

import llvm.value.variable.LLVMLocal;

import llvm.value.operand.LLVMOperand;

import arm.ARMCFGNode;

import arm.value.operand.ARMRegister;


public class LLVMPhi extends LLVMRegister
{
   public final Map<LLVMCFGNode, LLVMOperand> sources = new HashMap<>();
   
   public final LLVMLocal variable;
   
   
   private String name = null;
   
   
   private int uid = -1;
   
   private static int count = 0;
   
   
   public LLVMPhi(LLVMLocal variable)
   {
      super(variable.type);
      
      this.variable = variable;
   }
   
   
   public LLVMPhi(LLVMLocal variable, String name)
   {
      this(variable);
      
      this.name = name;
   }
   
   
   @Override
   public String regLLVMString()
   {
      return '%' + ((this.name == null)
            ? ('p' + Integer.toString(this.getUID()))
            : this.name);
   }
   
   
   public int getUID()
   {
      if (this.uid == -1)
         this.uid = count++;
      
      return this.uid;
   }
   
   
   public void addSource(LLVMCFGNode node, LLVMOperand value)
   {
      this.sources.put(node, value);
   }
   
   
   public LLVMOperand getSource(LLVMCFGNode node)
   {
      return this.sources.get(node);
   }
   
   
   @Override
   public ARMRegister regBuildARM(ARMCFGNode node)
   {
      return null;
   }
}
