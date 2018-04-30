package ast.declaration;


import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import llvm.declaration.LLVMStruct;
import llvm.declaration.LLVMStructs;


public class Structs
{
   public final List<Struct> structs;
   
   
   public Structs(List<Struct> structs)
   {
      this.structs = structs;
   }
   
   
   public Struct getStruct(String name)
   {
      for (Struct struct : this.structs)
         if (struct.name.equals(name))
            return struct;
      
      
      return null;
   }
   
   
   public boolean isValid(String name)
   {
      return this.getStruct(name) != null;
   }
   
   
   public void removeInvalids()
   {
      Iterator<Struct> structerator = this.structs.iterator();
      Set<String> names = new HashSet<>();
      
      
      /* Get all unique function names */
      while (structerator.hasNext())
      {
         Struct struct = structerator.next();
         
         
         if (names.contains(struct.name))
         {
            structerator.remove();
            System.err.println("Already got it");
         }
         else
         {
            names.add(struct.name);
            
            struct.removeInvalids(this);
         }
      }
   }
   
   
   private LLVMStructs buildLLVM()
   {
      this.removeInvalids();
      
      
      List<LLVMStruct> llvmStructs = new LinkedList<>()
      
      for (Struct struct : this.structs)
         llvmStructs.add(struct.buildLLVM(this));
      
      return new LLVMStructs(llvmStructs);
   }
}
