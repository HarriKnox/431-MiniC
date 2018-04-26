package parser.visitor;

import parser.MiniBaseVisitor;
import parser.MiniParser;

import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;
import java.util.ArrayList;

import ast.declaration.Function;
import ast.declaration.Functions;


public class FunctionsVisitor
   extends MiniBaseVisitor<Functions>
{
   private final FunctionVisitor functionVisitor =
      new FunctionVisitor();
   
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
