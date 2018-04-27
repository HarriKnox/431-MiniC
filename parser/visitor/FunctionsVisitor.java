package parser.visitor;


import java.util.LinkedList;
import java.util.List;

import parser.MiniBaseVisitor;

import ast.declaration.Function;
import ast.declaration.Functions;


import static parser.MiniParser.FunctionContext;
import static parser.MiniParser.FunctionsContext;


public class FunctionsVisitor extends MiniBaseVisitor<Functions>
{
   private final FunctionVisitor functionVisitor = new FunctionVisitor();


   @Override
   public Functions visitFunctions(FunctionsContext ctx)
   {
      List<Function> funcs = new LinkedList<>();


      for (FunctionContext fctx : ctx.function())
         funcs.add(functionVisitor.visit(fctx));


      return new Functions(funcs);
   }
}
