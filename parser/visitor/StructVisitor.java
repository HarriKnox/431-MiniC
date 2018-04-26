package parser.visitor;

import parser.MiniBaseVisitor;
import parser.MiniParser;

import java.util.List;
import java.util.ArrayList;

import ast.declaration.Declarations;
import ast.declaration.Struct;

public class StructVisitor
   extends MiniBaseVisitor<Struct>
{
   private final DeclVisitor declVisitor = new DeclVisitor();

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
