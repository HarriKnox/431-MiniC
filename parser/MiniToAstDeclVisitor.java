package parser;

import java.util.List;
import java.util.ArrayList;

import ast.declaration.Declaration;
import ast.declaration.Declarations;

public class MiniToAstDeclVisitor
   extends MiniBaseVisitor<Declarations>
{
   private final MiniToAstTypeVisitor typeVisitor = new MiniToAstTypeVisitor();

   @Override
   public Declarations visitNestedDecl(MiniParser.NestedDeclContext ctx)
   {
      return gatherDecls(ctx.decl());
   }
   
   @Override
   public Declarations visitParameters(MiniParser.ParametersContext ctx)
   {
      return gatherDecls(ctx.decl());
   }
   
   private Declarations gatherDecls(List<MiniParser.DeclContext> decls)
   {
      List<Declaration> fields = new ArrayList<>();

      for (MiniParser.DeclContext dctx : decls)
      {
         fields.add(new Declaration(dctx.getStart().getLine(),
            typeVisitor.visit(dctx.type()),
            dctx.ID().getText()));
      }

      return new Declarations(fields);
   }

   @Override
   protected Declarations defaultResult()
   {
      return new Declarations(new ArrayList<>());
   }
}
