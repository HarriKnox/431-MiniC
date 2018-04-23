package cfg;


import java.util.List;

import ast.Declaration;
import ast.Function;
import ast.Program;
import ast.TypeDeclaration;


public class ControlFlowGraphGenerator
{
   public static void buildProgramCFG(Program prog)
   {
      for (Function func : prog.funcs)
      {
         CFGFunction funcCFG = CFGFunction.buildFunctionCFG(func);
         
         funcCFG.printGraph();
         
         System.out.println();
      }
   }
}
