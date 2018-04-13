package analyzer;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ast.Declaration;
import ast.StructType;
import ast.Type;
import ast.TypeDeclaration;

import common.ErrorPrinter;


public class GlobalChecker
{
   private static final Map<String, Type> globals = new HashMap<>();
   
   
   static void check(List<Declaration> decls)
   {
      for (Declaration decl : decls)
      {
         if (!StructChecker.validType(decl.type))
            ErrorPrinter.unknownStruct(decl.line, ((StructType)decl.type).name);
         
         else if (globals.containsKey(decl.name))
            ErrorPrinter.duplicate(decl.line, "global variable " + decl.name);
         
         else
            globals.put(decl.name, decl.type);
      }
   }
}
