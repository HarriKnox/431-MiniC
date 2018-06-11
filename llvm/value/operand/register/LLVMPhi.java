package llvm.value.operand.register;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import llvm.LLVMCFGNode;

import llvm.value.variable.LLVMLocal;

import llvm.value.operand.LLVMOperand;

import arm.ARMCFGNode;

import arm.value.operand.ARMRegister;

import common.Printer;
import common.Options;


public class LLVMPhi extends LLVMRegister
{
   public final Map<LLVMCFGNode, LLVMOperand> sources = new HashMap<>();
   
   public final LLVMLocal variable;
   
   
   private String name = null;
   
   private ARMRegister armReg = null;
   private ARMRegister armRegTemp = null;
   
   
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
   
   
   public void writeLLVM(Printer printr, Options opts)
   {
      printr.print("   ")
            .print(this.regLLVMString())
            .print(" = phi ")
            .print(this.type.llvmString())
            .print(' ');
      
      
      Iterator<Map.Entry<LLVMCFGNode, LLVMOperand>> sourcerator
            = this.sources.entrySet().iterator();
      
      
      if (sourcerator.hasNext())
         printSource(printr, sourcerator.next());
      
      
      while (sourcerator.hasNext())
      {
         printr.print(", ");
         
         printSource(printr, sourcerator.next());
      }
      
      
      printr.println();
   }
   
   
   private static void printSource(Printer printr,
         Map.Entry<LLVMCFGNode, LLVMOperand> source)
   {
      printr.print('[')
            .print(source.getValue().llvmString())
            .print(", %")
            .print(source.getKey().llvmString())
            .print(']');
   }
   
   
   public void addSource(LLVMCFGNode node, LLVMOperand value)
   {
      this.sources.put(node, value);
   }
   
   
   public LLVMOperand getSource(LLVMCFGNode node)
   {
      return this.sources.get(node);
   }
   
   
   public boolean handleTrivial()
   {
      Set<LLVMOperand> values = new HashSet<>();
      
      
      /* Get a set of all unique source values for this phi */
      for (LLVMOperand sourceValue : this.sources.values())
         values.add(sourceValue);
      
      
      /* If this is in the list (loop), remove it */
      if (values.contains(this))
         values.remove(this);
      
      
      /* If there's one source value left, this is trivial and is that value */
      if (values.size() == 1)
      {
         this.bind(values.toArray(new LLVMOperand[0])[0]);
         return true;
      }
      
      
      return false;
   }
   
   
   @Override
   public ARMRegister regBuildARM(ARMCFGNode node)
   {
      if (this.armReg == null)
         this.armReg = new ARMRegister();
      
      return this.armReg;
   }
   
   
   public ARMRegister getTempARM()
   {
      if (this.armRegTemp == null)
         this.armRegTemp = new ARMRegister();
      
      return this.armRegTemp;
   }
}
