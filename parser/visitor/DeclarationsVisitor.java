package parser.visitor;

import parser.MiniBaseVisitor;
import parser.MiniParser;

import org.antlr.v4.runtime.tree.TerminalNode;
import java.util.List;
import java.util.ArrayList;

import ast.declaration.Declaration;
import ast.declaration.Declarations;
import ast.type.Type;

public class DeclarationsVisitor
   extends MiniBaseVisitor<Declarations>
{
   private final TypeVisitor typeVisitor = new TypeVisitor();

   @Override
   public Declarations visitDeclarations(
      MiniParser.DeclarationsContext ctx)
   {
      List<Declaration> decls = new ArrayList<>();

      for (MiniParser.DeclarationContext dctx : ctx.declaration())
      {
         addDeclarationsTo(dctx, decls);
      }

      return new Declarations(decls);
   }

   private void addDeclarationsTo(MiniParser.DeclarationContext ctx,
      List<Declaration> decls)
   {
      Type type = typeVisitor.visit(ctx.type());

      for (TerminalNode node : ctx.ID())
      {
         decls.add(new Declaration(node.getSymbol().getLine(), type,
            node.getText()));
      }
   }

   @Override
   protected Declarations defaultResult()
   {
      return new Declarations(new ArrayList<>());
   }
}
