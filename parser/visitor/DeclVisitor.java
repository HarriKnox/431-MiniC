package parser.visitor;


import java.util.LinkedList;
import java.util.List;

import parser.MiniBaseVisitor;

import ast.declaration.Declaration;
import ast.declaration.Declarations;


import static parser.MiniParser.DeclContext;
import static parser.MiniParser.NestedDeclContext;
import static parser.MiniParser.ParametersContext;


public class DeclVisitor extends MiniBaseVisitor<Declarations>
{
   private final TypeVisitor typeVisitor = new TypeVisitor();


   @Override
   public Declarations visitNestedDecl(NestedDeclContext ctx)
   {
      return gatherDecls(ctx.decl());
   }
   
   
   @Override
   public Declarations visitParameters(ParametersContext ctx)
   {
      return gatherDecls(ctx.decl());
   }
   
   
   private Declarations gatherDecls(List<DeclContext> decls)
   {
      List<Declaration> fields = new LinkedList<>();


      for (DeclContext dctx : decls)
      {
         fields.add(new Declaration(
               dctx.getStart().getLine(),
               typeVisitor.visit(dctx.type()),
               dctx.ID().getText()));
      }


      return new Declarations(fields);
   }
}
