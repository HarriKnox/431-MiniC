package parser.visitor;

import parser.MiniBaseVisitor;
import parser.MiniParser;


import parser.MiniBaseVisitor;

import ast.declaration.Struct;


import static parser.MiniParser.StructContext;


public class StructVisitor extends MiniBaseVisitor<Struct>
{
   private final DeclVisitor declVisitor = new DeclVisitor();


   @Override
   public Struct visitStruct(StructContext ctx)
   {
      return new Struct(
            ctx.getStart().getLine(),
            ctx.ID().getText(),
            declVisitor.visit(ctx.fields()));
   }
}
