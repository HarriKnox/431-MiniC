package analyzer;

import ast.Program;


public class TypeChecker
{
   public static void staticTypeCheck(Program program)
   {
      StructChecker.check(program.types);
      GlobalChecker.check(program.decls);
      FunctionChecker.check(program.funcs);
   }
}
