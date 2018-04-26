package parser;

import java.util.List;
import java.util.ArrayList;

import ast.declaration.Declarations;
import ast.declaration.TypeDeclaration;

public class MiniToAstTypeDeclarationVisitor
   extends MiniBaseVisitor<TypeDeclaration>
{
   private final MiniToAstDeclVisitor declVisitor = new MiniToAstDeclVisitor();

   @Override
   public TypeDeclaration visitTypeDeclaration(
      MiniParser.TypeDeclarationContext ctx)
   {
      return new TypeDeclaration(
         ctx.getStart().getLine(),
         ctx.ID().getText(),
         declVisitor.visit(ctx.nestedDecl()));
   }
   
   @Override
   protected TypeDeclaration defaultResult()
   {
      return new TypeDeclaration(-1, "invalid", new Declarations(new ArrayList<>()));
   }
}
