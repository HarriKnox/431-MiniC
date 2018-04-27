package parser.visitor;


import java.util.LinkedList;
import java.util.List;

import org.antlr.v4.runtime.tree.TerminalNode;

import parser.MiniBaseVisitor;

import ast.declaration.Declaration;
import ast.declaration.Declarations;

import ast.type.Type;


import static parser.MiniParser.DeclarationContext;
import static parser.MiniParser.DeclarationsContext;


public class DeclarationsVisitor extends MiniBaseVisitor<Declarations>
{
   private final TypeVisitor typeVisitor = new TypeVisitor();


   @Override
   public Declarations visitDeclarations(DeclarationsContext ctx)
   {
      List<Declaration> decls = new LinkedList<>();


      for (DeclarationContext dctx : ctx.declaration())
      {
         Type type = typeVisitor.visit(dctx.type());

         for (TerminalNode node : dctx.ID())
         {
            decls.add(new Declaration(
                  node.getSymbol().getLine(),
                  type,
                  node.getText()));
         }
      }


      return new Declarations(decls);
   }
}
