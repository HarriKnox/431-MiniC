package parser;

import java.util.List;
import java.util.ArrayList;

import ast.*;

public class MiniToAstTypesVisitor
   extends MiniBaseVisitor<Types>
{
   private final MiniToAstTypeDeclarationVisitor typeDeclarationVisitor =
      new MiniToAstTypeDeclarationVisitor();
   
   @Override
   public Types visitTypes(MiniParser.TypesContext ctx)
   {
      List<TypeDeclaration> types = new ArrayList<>();
      
      for (MiniParser.TypeDeclarationContext tctx : ctx.typeDeclaration())
      {
         types.add(typeDeclarationVisitor.visit(tctx));
      }
      
      return new Types(types);
   }
   
   @Override
   protected Types defaultResult()
   {
      return new Types(new ArrayList<>());
   }
}
