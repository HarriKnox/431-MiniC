package parser;

import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;
import java.util.ArrayList;

import ast.*;

public class MiniToAstFunctionsVisitor
   extends MiniBaseVisitor<Functions>
{
   private final MiniToAstFunctionVisitor functionVisitor =
      new MiniToAstFunctionVisitor();
   
   @Override
   public Functions visitFunctions(MiniParser.FunctionsContext ctx)
   {
      List<Function> funcs = new ArrayList<>();
      
      for (MiniParser.FunctionContext fctx : ctx.function())
      {
         funcs.add(functionVisitor.visit(fctx));
      }
      
      return new Functions(funcs);
   }
   
   @Override
   protected Functions defaultResult()
   {
      return new Functions(new ArrayList<>());
   }
}
