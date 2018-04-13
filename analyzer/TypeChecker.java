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
   public static void staticTypeCheck(Program program, Options opts)
   {
      StructChecker.check(program.types);
      GlobalChecker.check(program.decls);
      FunctionChecker.check(program.funcs);
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
