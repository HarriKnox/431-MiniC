package analyzer;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import ast.Declaration;
import ast.Function;
import ast.IntType;
import ast.Program;
import ast.StructType;
import ast.Type;
import ast.TypeDeclaration;

import main.Options;


public class TypeChecker
{
   public static boolean staticTypeCheck(Program program, Options opts)
   {
      boolean ok = true;
      
      
      Map<String, Map<String, Type>> types = new HashMap<>();
      
      ok &= checkStructs(types, program.types);
      
      
      HashMap<String, Type> globals = new HashMap<>();
      
      ok &= checkGlobals(types, globals, program.decls);
      
      
      
      HashMap<String, HashMap<String, Type>> functions = new HashMap<>();
      
      ok &= checkFunctions(types, globals, functions, program.funcs);
      
      
      return ok;
   }
   
   
   static boolean contains(Map<String, ?> map, String key, int line, String type)
   {
      boolean contains = map.containsKey(key);
      
      if (contains)
         System.err.println("line " + line + " " + type + " '" + key + "' already declared");
      
      return contains;
   }
   
   static boolean validType(Map<String, ?> types, Type type, int line)
   {
      if (type instanceof StructType)
      {
         StructType s = (StructType)type;
         
         if (!types.containsKey(s.name))
         {
            System.err.println("line " + line + " unknown type 'struct " + s.name + "'");
            return false;
         }
      }
      
      return true;
   }
   
   
   private static boolean checkStructs(Map<String, Map<String, Type>> types, List <TypeDeclaration> typeDecls)
   {
      boolean ok = true;
      
      Map<String, TypeDeclaration> validTypeDecls = new LinkedHashMap<>();
      
      
      for (TypeDeclaration typeDecl : typeDecls)
      {
         if (contains(validTypeDecls, typeDecl.name, typeDecl.line, "struct"))
            ok = false;
         
         else
            validTypeDecls.put(typeDecl.name, typeDecl);
      }
      
      
      for (TypeDeclaration validTypeDecl : validTypeDecls.values())
      {
         Map<String, Type> fields = new HashMap<>();
         
         types.put(validTypeDecl.name, fields);
         
         
         for (Declaration decl : validTypeDecl.fields)
         {
            if (!validType(types, decl.type, decl.line) || contains(fields, decl.name, decl.line, "field"))
               ok = false;
            
            else
               fields.put(decl.name, decl.type);
         }
      }
      
      
      return ok;
   }
   
   
   
   private static boolean checkGlobals(Map<String, ?> types, Map<String, Type> globals, List<Declaration> decls)
   {
      boolean ok = true;
      
      
      for (Declaration decl : decls)
      {
         if (!validType(types, decl.type, decl.line) || contains(globals, decl.name, decl.line, "global variable"))
            ok = false;
         
         else
            globals.put(decl.name, decl.type);
      }
      
      
      return ok;
   }
   
   
   
   private static boolean checkFunctions(
         Map<String, Map<String, Type>> types,
         Map<String, Type> globals,
         Map<String, ?> functions,
         List<Function> funcs)
   {
      boolean ok = true;
      
      
      Map<String, Function> validFunctionDecls = new LinkedHashMap<>();
      
      
      for (Function func : funcs)
      {
         if (contains(validFunctionDecls, func.name, func.line, "function"))
            ok = false;
         
         else
            validFunctionDecls.put(func.name, func);
      }
      
      
      FunctionChecker.setScope(types, globals, validFunctionDecls);
      
      for (Function func : validFunctionDecls.values())
         ok &= FunctionChecker.validFunction(func);
      
      
      if (!validFunctionDecls.containsKey("main"))
      {
         System.err.println("ERROR: no 'main' function declared");
         ok = false;
      }
      else
      {
         Function main = validFunctionDecls.get("main");
         
         if (main.params.size() != 0)
         {
            System.err.println("line " + main.line + " 'main' requires exactly zero parameters");
            ok = false;
         }
         else if (!(main.retType instanceof IntType))
         {
            System.err.println("line " + main.line + " 'main' must return an int");
            ok = false;
         }
      }
      
      
      return ok;
   }
}
