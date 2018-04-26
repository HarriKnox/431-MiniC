package parser;

import java.util.List;
import java.util.ArrayList;

import ast.Program;
import ast.declaration.Declarations;
import ast.declaration.Functions;
import ast.declaration.Structs;

public class MiniToAstProgramVisitor
   extends MiniBaseVisitor<Program>
{
   private final MiniToAstStructsVisitor structsVisitor =
      new MiniToAstStructsVisitor();
   private final MiniToAstDeclarationsVisitor declarationsVisitor =
      new MiniToAstDeclarationsVisitor();
   private final MiniToAstFunctionsVisitor functionsVisitor =
      new MiniToAstFunctionsVisitor();

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
