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
   public static final Map<String, Map<String, Type>> types = new HashMap<>();
   public static final Map<String, TypeDeclaration> validTypeDecls = new LinkedHashMap<>();
   public static final Map<String, Type> globals = new HashMap<>();
   public static final Map<String, Function> functions = new HashMap<>();
   
   public static boolean staticTypeCheck(Program program, Options opts)
   {
      boolean ok = true;
      
      
      ok &= checkStructs(program.types);
      
      ok &= checkGlobals(program.decls);
      
      ok &= checkFunctions(program.funcs);
      
      
      return ok;
   }
   
   
   static boolean contains(Map<String, ?> map, String key, int line, String type)
   {
      boolean contains = map.containsKey(key);
      
      if (contains)
         System.err.println("line " + line + " " + type + " '" + key + "' already declared");
      
      return contains;
   }
   
   static boolean validType(Type type, int line)
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
   
   
   private static boolean checkStructs(List <TypeDeclaration> typeDecls)
   {
      boolean ok = true;
      
      
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
            if (!validType(decl.type, decl.line) || contains(fields, decl.name, decl.line, "field"))
               ok = false;
            
            else
               fields.put(decl.name, decl.type);
         }
      }
      
      
      return ok;
   }
   
   
   
   private static boolean checkGlobals(List<Declaration> decls)
   {
      boolean ok = true;
      
      
      for (Declaration decl : decls)
      {
         if (!validType(decl.type, decl.line) || contains(globals, decl.name, decl.line, "global variable"))
            ok = false;
         
         else
            globals.put(decl.name, decl.type);
      }
      
      
      return ok;
   }
   
   
   
   private static boolean checkFunctions(List<Function> funcs)
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
