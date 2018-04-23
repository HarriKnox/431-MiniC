package cfg;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import ast.TypeDeclaration;
import ast.Declaration;


class LLVMTypeDeclaration
{
   String name;
   List<String> types;
   
   
   LLVMTypeDeclaration(TypeDeclaration typeDecl)
   {
      this.name = typeDecl.name;
      
      this.types = new LinkedList<>();
      
      for (Declaration decl : typeDecl.fields)
         this.types.add(decl.type.toLLVMTypeString());
   }
   
   
   public String toString()
   {
      StringBuilder sb = new StringBuilder();
      
      sb.append("%struct.").append(this.name).append(" = type {");
      
      
      Iterator<String> fielditer = this.types.iterator();
      
      if (fielditer.hasNext())
         sb.append(fielditer.next());
      
      while (fielditer.hasNext())
         sb.append(", ").append(fielditer.next());
      
      return sb.append("}").toString();
   }
}
