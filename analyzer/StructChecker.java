package analyzer;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import ast.Declaration;
import ast.StructType;
import ast.Type;
import ast.TypeDeclaration;


public class StructChecker
{
   private static final Map<String, Map<String, Type>> structs = new HashMap<>();
   
   
   static boolean validType(Type type)
   {
      return (type instanceof StructType) ? structs.containsKey(((StructType)type).name) : true;
   }
   
   
   private static void check(List<TypeDeclaration> typeDecls)
   {
      Map<String, TypeDeclaration> validTypes = new LinkedHashMap<>();
      
      
      for (TypeDeclaration typeDecl : typeDecls)
      {
         if (validTypes.contains(typeDecl.name))
            ErrorPrinter.duplicate(typeDecl.line, "struct " + typeDecl.name)
         
         else
            validTypes.put(typeDecl.name, typeDecl);
      }
      
      
      for (TypeDeclaration validTypeDecl : validTypes.values())
      {
         Map<String, Type> fields = new HashMap<>();
         
         structs.put(validTypeDecl.name, fields);
         
         
         for (Declaration decl : validTypeDecl.fields)
         {
            if (!validType(decl.type))
               ErrorPrinter.unknownStruct(decl.line, ((StructType)decl.type).name);
            
            else if (fields.containsKey(decl.name))
               ErrorPrinter.duplicate(decl.line, "field " + validTypeDecl.name + "." + decl.name);
            
            else
               fields.put(decl.name, decl.type);
         }
      }
   }
}

