package parser.visitor;


import parser.MiniBaseVisitor;

import ast.Program;

import ast.declaration.Declarations;
import ast.declaration.Functions;
import ast.declaration.Structs;


import static parser.MiniParser.DeclarationsContext;
import static parser.MiniParser.FunctionsContext;
import static parser.MiniParser.ProgramContext;
import static parser.MiniParser.StructsContext;


public class ProgramVisitor extends MiniBaseVisitor<Program>
{
   private final StructsVisitor structsVisitor = new StructsVisitor();

   private final DeclarationsVisitor declarationsVisitor =
      new DeclarationsVisitor();

   private final FunctionsVisitor functionsVisitor = new FunctionsVisitor();


   @Override
   public Program visitProgram(ProgramContext ctx)
   {
      return new Program(
            structsVisitor.visit(ctx.structs()),
            declarationsVisitor.visit(ctx.declarations()),
            functionsVisitor.visit(ctx.functions()));
   }
}
