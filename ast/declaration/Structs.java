package ast.declaration;


import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import common.ErrorPrinter;

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
            ErrorPrinter.duplicate(struct.token, "struct", struct.name);
         }
         else
         {
            names.add(struct.name);
            
            struct.removeInvalids(this);
         }
      }
   }
   
   
   public LLVMStructs buildLLVM()
   {
      this.removeInvalids();
      
      
      List<LLVMStruct> llvmStructs = new LinkedList<>();
      
      for (Struct struct : this.structs)
         llvmStructs.add(struct.buildLLVM());
      
      return new LLVMStructs(llvmStructs);
   }
}
