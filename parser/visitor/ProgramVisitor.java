package parser.visitor;


import parser.MiniBaseVisitor;

import ast.ProgramAST;

import ast.declaration.Functions;
import ast.declaration.Structs;
import ast.declaration.Variables;


import static parser.MiniParser.VariablesContext;
import static parser.MiniParser.FunctionsContext;
import static parser.MiniParser.ProgramContext;
import static parser.MiniParser.StructsContext;


public class ProgramVisitor extends MiniBaseVisitor<ProgramAST>
{
   private final StructsVisitor structsVisitor = new StructsVisitor();
   private final VariablesVisitor variablesVisitor = new VariablesVisitor();
   private final FunctionsVisitor functionsVisitor = new FunctionsVisitor();


   @Override
   public ProgramAST visitProgram(ProgramContext ctx)
   {
      return new ProgramAST(
            structsVisitor.visit(ctx.structs()),
            variablesVisitor.visit(ctx.variables()),
            functionsVisitor.visit(ctx.functions()));
   }
}
