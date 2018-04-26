package parser.visitor;

import parser.MiniBaseVisitor;
import parser.MiniParser;

import java.util.List;
import java.util.ArrayList;

import ast.declaration.Struct;
import ast.declaration.Structs;

public class StructsVisitor
   extends MiniBaseVisitor<Structs>
{
   private final StructVisitor structVisitor =
      new StructVisitor();
   
   @Override
   public Structs visitStructs(MiniParser.StructsContext ctx)
   {
      List<Struct> structs = new ArrayList<>();
      
      for (MiniParser.StructContext sctx : ctx.struct())
      {
         structs.add(structVisitor.visit(sctx));
      }
      
      return new Structs(structs);
   }
   
   @Override
   protected Structs defaultResult()
   {
      return new Structs(new ArrayList<>());
   }
}
