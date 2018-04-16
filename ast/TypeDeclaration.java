package ast;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.Token;


public class TypeDeclaration
   extends LinedElement
{
   private static final Map<String, TypeDeclaration> validTypeDeclarations = new HashMap<>();
   
   private final String name;
   private final List<Declaration> fieldList;
   
   private final Map<String, Type> fieldTypeMap;
   private final List<String> errors;
   

   public TypeDeclaration(Token token, String name, List<Declaration> fields)
   {
      super(token);
      
      this.name = name;
      this.fieldList = fields;
      
      this.fieldTypeMap = new LinkedHashMap<>();
      this.errors = new LinkedList<>();
   }
   
   
   public static void check(List<TypeDeclaration> typeDecls)
   {
      for (TypeDeclaration typeDecl : typeDecls)
      {
         if (validTypeDeclarations.containsKey(typeDecl.name))
            ErrorPrinter.duplicate(typeDecl.getToken(), "struct " + typeDecl.name);
         
         else
            validTypeDeclarations.put(typeDecl.name, typeDecl);
      }
      
      for (TypeDeclaration validTypeDecl : validTypeDeclarations)
      {
         validTypeDecl.check();
      }
   }
   
   private void check()
   {
      for (Declaration field : this.fieldList)
      {
         String name = field.getName();
         Type type = field.getType();
         
         
         if ((type instanceof StructType) && !validTypeDeclarations.containsKey(name))
            ErrorPrinter.unknownStruct(field.getToken(), (StructType)type);
         
         else if (this.fieldTypeMap.containsKey(field.name))
            ErrorPrinter.duplicate(decl.line, "field " + validTypeDecl.name + "." + decl.name);
         
         else
            this.fieldTypeMap.put(field.getName(), field.type);
      }
   }
   
   
   public String getName()
   {
      return this.name;
   }
   
   
   public static boolean isValidType(String struct)
   {
      return validTypeDeclarations.containsKey(struct);
   }
   
   
   public static Type getFieldTypeOfStruct(String stuct, String field)
   {
      TypeDeclaration struct = validTypeDeclarations.get(struct);
      
      return (struct == null) ? null : struct.fields.get(field);
   }
}
