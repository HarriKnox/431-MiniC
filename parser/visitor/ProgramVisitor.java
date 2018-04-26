package parser.visitor;

import parser.MiniBaseVisitor;
import parser.MiniParser;

import java.util.List;
import java.util.ArrayList;

import ast.Program;
import ast.declaration.Declarations;
import ast.declaration.Functions;
import ast.declaration.Structs;

public class ProgramVisitor
   extends MiniBaseVisitor<Program>
{
   private final StructsVisitor structsVisitor =
      new StructsVisitor();
   private final DeclarationsVisitor declarationsVisitor =
      new DeclarationsVisitor();
   private final FunctionsVisitor functionsVisitor =
      new FunctionsVisitor();

   @Override
   public Program visitProgram(MiniParser.ProgramContext ctx)
   {
      return new Program(
          gatherStructs(ctx.structs()),
          gatherDeclarations(ctx.declarations()),
          gatherFunctions(ctx.functions()));
   }

   private Structs gatherStructs(MiniParser.StructsContext ctx)
   {
      return structsVisitor.visit(ctx);
   }

   private Declarations gatherDeclarations(
      MiniParser.DeclarationsContext ctx)
   {
      return declarationsVisitor.visit(ctx);
   }

   private Functions gatherFunctions(MiniParser.FunctionsContext ctx)
   {
      return functionsVisitor.visit(ctx);
   }

   @Override
   protected Program defaultResult()
   {
      return new Program(
         structsVisitor.defaultResult(),
         declarationsVisitor.defaultResult(),
         functionsVisitor.defaultResult());
   }
}
