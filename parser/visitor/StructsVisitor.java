package parser.visitor;


import java.util.LinkedList;
import java.util.List;

import parser.MiniBaseVisitor;

import ast.declaration.Struct;
import ast.declaration.Structs;


import static parser.MiniParser.StructContext;
import static parser.MiniParser.StructsContext;


public class StructsVisitor extends MiniBaseVisitor<Structs>
{
   private final StructVisitor structVisitor = new StructVisitor();


   @Override
   public Structs visitStructs(StructsContext ctx)
   {
      List<Struct> structs = new LinkedList<>();


      for (StructContext sctx : ctx.struct())
         structs.add(structVisitor.visit(sctx));


      return new Structs(structs);
   }
}
