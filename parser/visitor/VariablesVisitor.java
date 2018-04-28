package parser.visitor;


import java.util.LinkedList;
import java.util.List;

import org.antlr.v4.runtime.tree.TerminalNode;

import parser.MiniBaseVisitor;

import ast.declaration.Declaration;
import ast.declaration.Declarations;

import ast.type.Type;


import static parser.MiniParser.VariableContext;
import static parser.MiniParser.VariablesContext;


public class VariablesVisitor extends MiniBaseVisitor<Declarations>
{
   private final TypeVisitor typeVisitor = new TypeVisitor();


   @Override
   public Declarations visitVariables(VariablesContext ctx)
   {
      List<Declaration> decls = new LinkedList<>();


      for (VariableContext vctx : ctx.variable())
      {
         Type type = typeVisitor.visit(vctx.type());

         for (TerminalNode node : vctx.ID())
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
