package ast.declaration;


import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import ast.type.StructType;
import ast.type.Type;


public class Structs
{
   public final List<Struct> structs;


   public Structs(List<Struct> structs)
   {
      this.structs = structs;
   }
   
   
   public boolean isValid(String structName)
   {
      return findStruct(structName) != null;
   }
   
   
   public boolean isField(String structName, String fieldName)
   {
      Struct s = findStruct(structName);
      
      return (s == null) ? false : s.isField(fieldName);
   }
   
   
   public Type getFieldType(String structName, String fieldName)
   {
      Struct s = findStruct(structName);
      
      return (s == null) ? null : s.getFieldType(fieldName);
   }
   
   
   private Struct findStruct(String structName)
   {
      for (Struct struct : this.structs)
         if (struct.name.equals(structName))
            return struct;
      
      
      return null;
   }
   
   
   public void validate()
   {
      Iterator<Struct> structerator = this.structs.iterator();
      Set<String> structNames = new HashSet<>();
      
      
      /* Get all unique struct names */
      while (structerator.hasNext())
      {
         Struct structName = structerator.next().name;
         
         
         if (structNames.contains(structName))
         {
            structerator.remove();
            System.err.println("Already got it");
         }
         else
         {
            structNames.add(structName);
         }
      }
      
      
      /* Validate all uniquely named structs */
      for (Struct struct : this.structs)
         struct.validate(this);
   }
}
