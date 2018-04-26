package parser;

import java.util.List;
import java.util.ArrayList;

import ast.declaration.Declarations;
import ast.declaration.Struct;

public class MiniToAstStructVisitor
   extends MiniBaseVisitor<Struct>
{
   private final MiniToAstDeclVisitor declVisitor = new MiniToAstDeclVisitor();

   @Override
   public Struct visitStruct(
      MiniParser.StructContext ctx)
   {
      return new Struct(
         ctx.getStart().getLine(),
         ctx.ID().getText(),
         declVisitor.visit(ctx.nestedDecl()));
   }
   
   @Override
   protected Struct defaultResult()
   {
      return new Struct(-1, "invalid", new Declarations(new ArrayList<>()));
   }
}
