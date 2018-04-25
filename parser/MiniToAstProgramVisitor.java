package parser;

import java.util.List;
import java.util.ArrayList;

import ast.*;

public class MiniToAstProgramVisitor
   extends MiniBaseVisitor<Program>
{
   private final MiniToAstTypesVisitor typesVisitor =
      new MiniToAstTypesVisitor();
   private final MiniToAstDeclarationsVisitor declarationsVisitor =
      new MiniToAstDeclarationsVisitor();
   private final MiniToAstFunctionsVisitor functionsVisitor =
      new MiniToAstFunctionsVisitor();

   @Override
   public Program visitProgram(MiniParser.ProgramContext ctx)
   {
      return new Program(
          gatherTypes(ctx.types()),
          gatherDeclarations(ctx.declarations()),
          gatherFunctions(ctx.functions()));
   }

   private Types gatherTypes(MiniParser.TypesContext ctx)
   {
      return typesVisitor.visit(ctx);
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
         typesVisitor.defaultResult(),
         declarationsVisitor.defaultResult(),
         functionsVisitor.defaultResult());
   }
}
