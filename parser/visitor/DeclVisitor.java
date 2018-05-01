package parser.visitor;


import java.util.LinkedList;
import java.util.List;

import parser.MiniBaseVisitor;

import ast.declaration.Variable;
import ast.declaration.Variables;


import static parser.MiniParser.DeclContext;
import static parser.MiniParser.FieldsContext;
import static parser.MiniParser.ParametersContext;


public class DeclVisitor extends MiniBaseVisitor<Variables>
{
   private final TypeVisitor typeVisitor = new TypeVisitor();


   @Override
   public Variables visitFields(FieldsContext ctx)
   {
      return gatherDecls(ctx.decl());
   }


   @Override
   public Variables visitParameters(ParametersContext ctx)
   {
      return gatherDecls(ctx.decl());
   }


   private Variables gatherDecls(List<DeclContext> decls)
   {
      List<Variable> variables = new LinkedList<>();
      int index = 0;


      for (DeclContext dctx : decls)
      {
         variables.add(new Variable(
               dctx.getStart().getLine(),
               dctx.ID().getText(),
               typeVisitor.visit(dctx.type()),
               index++));
      }


      return new Variables(variables);
   }
}
